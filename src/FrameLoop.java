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
				ast.eval(environment);
				/*try {
					ast.eval(currentEnvironment);
				} catch (WordsObjectNotFoundException e) {
					System.out.printf("Error: object %s is not defined.\n", e.getObjectName());
				} catch (WordsClassNotFoundException e) {
					System.out.printf("Error: class %s is not defined.\n", e.getClassName());
				} catch (WordsFunctionNotFoundException e) {
					System.out.printf("Error: function %s is not defined for class %s.\n", e.getFunctionName(), e.getClassName());
				} catch (WordsFunctionArgException e) {
					System.out.printf("Error: function %s expected argument %s, received \"%s\".\n", e.getFunctionName(), e.getExpectedArg(), e.getReceivedArg());
				} catch (WordsObjectAlreadyExistsException e) {
					System.out.printf("Error: object %s already exists, please choose another name.\n", e.getObjectName());
				}*/
			}
			for (WordsObject object : environment.getObjects()) {
				object.doAction();
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
