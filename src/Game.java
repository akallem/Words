import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Game implements Runnable {
	
	private HashMap<String, WordsClass> classes;
	private HashMap<String, WordsObject> objects;
	private ArrayList<WordsEventListener> eventListeners;
	//TODO: make commandQueue threadsafe
	private LinkedList<Command> commandQueue;
	private WordsUI GUI;
	private static int TIME_TO_WAIT = 1000;
	
	public Game(WordsUI GUI) {
		classes = new HashMap<String, WordsClass>();
		objects = new HashMap<String, WordsObject>();
		eventListeners = new ArrayList<WordsEventListener>();
		commandQueue = new LinkedList<Command>();
		this.GUI = GUI;
	}
	
	public void addCommandToQueue(Object command) {
		commandQueue.add((Command) command);
		System.out.println(command.toString()); // temporary debug. 
	}

	
	public void run() {
		try {
			wait(TIME_TO_WAIT);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!commandQueue.isEmpty()) {
			Command command = commandQueue.pop();
			processCommand(command);
		}
		for (WordsObject object : objects.values()) {
			object.doActions();
		}
		for (WordsObject object : objects.values()) {
			GUI.add(object);
		}
		
	}
	
	private void processCommand(Command command) {
		//TODO
	}
	
	private void executeEventListeners() {
		//TODO
	}

}
