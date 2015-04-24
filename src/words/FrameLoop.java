package words;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Iterator;

import words.ast.AST;
import words.environment.*;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class FrameLoop extends Thread {
	private static int numFrames = 1;

	private LinkedBlockingDeque<AST> ASTQueue;
	private Environment environment;
	private GUI GUI;

	/**
	 * Initialize the Frame Loop with a GUI -- generally used for a real run of the program
	 */
	public FrameLoop(GUI GUI) {
		this.environment = new Environment();
		this.ASTQueue = new LinkedBlockingDeque<AST>();
		this.GUI = GUI;
	}

	/**
	 * Initialize the frame loop with a specific environment -- generally used for testing
	 */
	public FrameLoop(Environment environment) {
		this.environment = environment;
		this.ASTQueue = new LinkedBlockingDeque<AST>();
		this.GUI = null;
	}

	public void enqueueAST(AST ast) {
		ASTQueue.add(ast);
	}


	public void run() {
		boolean finished = false;
		while (!finished) {
			if (Options.TIME_TO_WAIT > 0) {
				long timeToSleep = Options.TIME_TO_WAIT;
				long start, end, slept;
				while (timeToSleep > 0) {
					start = System.currentTimeMillis();
					try {
						Thread.sleep(timeToSleep);
						break;
					} catch (InterruptedException e) {
						//work out how much more time to sleep for
						end = System.currentTimeMillis();
						slept = end-start;
						timeToSleep -= slept;
			        	}
				}
			}
			finished = executeSingleFrame();
		}
	}

	private boolean executeSingleFrame() {
		boolean finished = false;

		// Phase 1: Statement Translation and Execution
		while (!ASTQueue.isEmpty()) {
			AST ast = ASTQueue.pop();
			try {
				ast.eval(environment);
			} catch (WordsRuntimeException e) {
				// Note: this should only be caught in junit tests; 
				// otherwise, it should be caught earlier at the statement level.
				System.err.println();
				System.err.println(e.toString());
				System.out.println("> ");
			}
		}

		// Phase 2: Action Queue Processing
		for (WordsObject object : environment.getObjects()) {
			try {
				object.executeNextAction(environment);
			} catch (WordsProgramException e) {
				System.err.println("Error executing action on object " + object.getObjectName() + ": \n" + e.toString());
				System.err.println("Action will not be performed");
				System.out.println("> ");
			}
		}

		// Phase 3: Listener Evaluation
		for (Iterator<WordsEventListener> iterator = environment.getEventListeners().iterator(); iterator.hasNext();) {
			try {
				WordsEventListener eventListener = iterator.next();
				boolean delete = !eventListener.execute(environment);
				if (delete) {
					iterator.remove();
				}
			} catch (WordsProgramException e) {
				System.err.println(e.toString());
				System.out.println("> ");
			}
		}

		if (GUI != null) {
			GUI.clear();
			for (WordsObject object : environment.getObjects()) {
				GUI.add(object.getCurrentPosition(), object.getClassName(), object.getObjectName(), object.getCurrentMessage());
			}
			GUI.render();
		} else {
			System.out.println("frame #: " + numFrames);
			FrameLog log = new FrameLog();
			for (WordsObject object : environment.getObjects()) {
				log.add(object.getCurrentPosition(), object.getClassName(), object.getObjectName(), object.getCurrentMessage());
			}
			log.log();
			if (Options.FRAME_LIMIT_ENABLED && numFrames >= Options.MAX_FRAMES)
				finished = true;
		}

		++numFrames;
		return finished;
	}

	public void fastForwardEnvironment(int numOfFrames) {
		for (int i = 0; i < numOfFrames; i++) {
			executeSingleFrame();
		}
	}
}
