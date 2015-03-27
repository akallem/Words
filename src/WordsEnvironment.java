import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class WordsEnvironment {
	private HashMap<String, WordsClass> classes;
	private HashMap<String, WordsObject> objects;
	private ArrayList<WordsEventListener> eventListeners;
	
	public WordsEnvironment() {
		classes = new HashMap<String, WordsClass>();
		objects = new HashMap<String, WordsObject>();
		eventListeners = new ArrayList<WordsEventListener>();
		setupWorld();
	}
	
	private void setupWorld() {
		WordsClass thing = new WordsClass("thing", null);
		classes.put("thing", thing);
	}
	
	public Collection<WordsObject> getObjects() {
		return objects.values();
	}
	
	public ArrayList<WordsEventListener> getEventListeners() {
		return eventListeners;
	}
}
