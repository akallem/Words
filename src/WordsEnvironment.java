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
		setupEnvironment();
	}
	
	private void setupEnvironment() {
		WordsClass thing = new WordsClass("thing", null);
		classes.put("thing", thing);
	}

	/**
	 * Creates a new class in the environment.  Throws an exception if the class could not be created.
	 */
	public void createClass(String className, String parent) {
		// TODO
	}
	
	public Collection<WordsClass> getClasses() {
		// TODO
		return null;
	}
	
	/**
	 * Creates a new object in the environment.  Throws an exception if the object could not be created.
	 */
	public void createObject(String objectName, String className, WordsPosition position) {
		// TODO
	}
	
	public WordsObject getObject(String objectName) {
		// TODO
		return null;
	}
	
	public Collection<WordsObject> getObjects() {
		return objects.values();
	}
	
	public Collection<WordsEventListener> getEventListeners() {
		return eventListeners;
	}
}