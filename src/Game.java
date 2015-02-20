import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;


public class Game {
	
	private HashMap<String, WordsClass> classes;
	private HashMap<String, WordsObject> objects;
	private ArrayList<WordsEventListener> eventListeners;
	private Queue<Command> commandQueue;

	public static void main(String[] args) {
		System.out.println("Hello World");
	}

}
