import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;


public class Game extends Thread {
	
	private HashMap<String, WordsClass> classes;
	private HashMap<String, WordsObject> objects;
	private ArrayList<WordsEventListener> eventListeners;
	private LinkedBlockingDeque<Command> commandQueue;
	private WordsUI GUI;
	private static int TIME_TO_WAIT = 1000;
	
	public Game(WordsUI GUI) {
		classes = new HashMap<String, WordsClass>();
		objects = new HashMap<String, WordsObject>();
		eventListeners = new ArrayList<WordsEventListener>();
		commandQueue = new LinkedBlockingDeque<Command>();
		this.GUI = GUI;
	}
	
	public void addCommandToQueue(Object command) {
		commandQueue.add((Command) command);
	}

	
	public void run() {
		int counter = 0;
		while(true) {
			long timeToSleep = 1000;
		    long start, end, slept;
		    boolean interrupted;
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
		            interrupted=true;
		        }
		    }
			while (!commandQueue.isEmpty()) {
				Command command = commandQueue.pop();
				processCommand(command);
			}
			for (WordsObject object : objects.values()) {
				object.doActions();
			}
			
			GUI.clear();
			for (WordsObject object : objects.values()) {
				GUI.add(object.getCurrentCell(), object.getClassName(), object.getObjectName(), object.getCurrentMessage());
			}
			GUI.render();
			executeEventListeners();
			counter++;
		}
	}
	
	private void processCommand(Command command) {
		//TODO
	}
	
	private void executeEventListeners() {
		//TODO
	}

}
