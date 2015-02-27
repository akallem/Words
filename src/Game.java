import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Game {
	
	private HashMap<String, WordsClass> classes;
	private HashMap<String, WordsObject> objects;
	private ArrayList<WordsEventListener> eventListeners;
	private LinkedList<Command> commandQueue;
	private WordsUI GUI;
	
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

}
