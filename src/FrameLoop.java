import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;


public class FrameLoop extends Thread {
	
	private LinkedBlockingDeque<AST> ASTQueue;
	private WordsEnvironment environment;
	private WordsUI GUI;
	private static int TIME_TO_WAIT = 1000;
	
	public FrameLoop(WordsUI GUI) {
		environment = new WordsEnvironment();
		ASTQueue = new LinkedBlockingDeque<AST>();
		this.GUI = GUI;
	}
	
	public void enqueueAST(AST ast) {
		ASTQueue.add(ast);
	}

	
	public void run() {
		int counter = 1;
		while(true) {
			long timeToSleep = TIME_TO_WAIT;
		    long start, end, slept;
			while(timeToSleep > 0){
		        start=System.currentTimeMillis();
		        try{
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
			while (!ASTQueue.isEmpty()) {
				AST ast = ASTQueue.pop();
				try {
					ast.eval(environment);
				} catch (WordsException e) {
					// Note: this should never actually be caught here; it should be caught earlier at the statement level. 
					System.err.println(e.toString());
				}
			}
			for (WordsObject object : environment.getObjects()) {
				object.executeNextAction(environment);
			}
			
			for (WordsEventListener eventListener : environment.getEventListeners()) {
				eventListener.execute();
			}
			
			GUI.clear();
			for (WordsObject object : environment.getObjects()) {
				GUI.add(object.getCurrentCell(), object.getClassName(), object.getObjectName(), object.getCurrentMessage());
			}
			GUI.render();
			counter++;
		}
	}

}
