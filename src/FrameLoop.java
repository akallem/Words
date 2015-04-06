import java.util.concurrent.LinkedBlockingDeque;


public class FrameLoop extends Thread {
	
	private LinkedBlockingDeque<AST> ASTQueue;
	private WordsEnvironment environment;
	private WordsUI GUI;
	private static int TIME_TO_WAIT = 1000;
	
	/**
	 * Initialize the Frame Loop with a GUI -- generally used for a real run of the program
	 */
	public FrameLoop(WordsUI GUI) {
		environment = new WordsEnvironment();
		ASTQueue = new LinkedBlockingDeque<AST>();
		this.GUI = GUI;
	}
	
	/**
	 * Initialize the frame loop with a specific environment -- generally used for testing
	 */
	public FrameLoop(WordsEnvironment environment) {
		this.environment = environment;
		this.ASTQueue = new LinkedBlockingDeque<AST>();
		this.GUI = null;
	}
	
	public void enqueueAST(AST ast) {
		ASTQueue.add(ast);
	}

	
	public void run() {
		while(true) {
			long timeToSleep = TIME_TO_WAIT;
		    long start, end, slept;
			while(timeToSleep > 0){
		        start=System.currentTimeMillis();
		        try {
		            Thread.sleep(timeToSleep);
		            break;
		        }
		        catch(InterruptedException e){
		            //work out how much more time to sleep for
		            end=System.currentTimeMillis();
		            slept=end-start;
		            timeToSleep-=slept;
		        }
		    }
			executeSingleFrame();
		}
	}
	
	private void executeSingleFrame() {
		while (!ASTQueue.isEmpty()) {
			AST ast = ASTQueue.pop();
			try {
				ast.eval(environment);
			} catch (WordsRuntimeException e) {
				// Note: this should never actually be caught here; it should be caught earlier at the statement level. 
				System.err.println();
				System.err.println(e.toString());
				System.out.println("> ");
			}
		}
		for (WordsObject object : environment.getObjects()) {
			try {
				object.executeNextAction(environment);
			} catch (WordsProgramException e) {
				System.err.println("Error executing action on object " + object.getObjectName() + ": \n" + e.toString());
				System.err.println("Action will not be performed");
				System.out.println("> ");
			}
		}
		
		for (WordsEventListener eventListener : environment.getEventListeners()) {
			eventListener.execute();
		}
		
		if (GUI != null) {
			GUI.clear();
			for (WordsObject object : environment.getObjects()) {
				GUI.add(object.getCurrentCell(), object.getClassName(), object.getObjectName(), object.getCurrentMessage());
			}
			GUI.render();
		}
	}
	
	public void fastForwardEnvironment(int numOfFrames) {
		for (int i = 0; i < numOfFrames; i++) {
			executeSingleFrame();
		}
	}
}
