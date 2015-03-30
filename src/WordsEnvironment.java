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
	 * Creates a new class in the environment and returns it.  Throws an exception if the class could not be created.
	 */
	public WordsClass createClass(String className, String parent) {
		// TODO
		return null;
	}
	
	public WordsClass getClass(String className) {
		return classes.get(className);
	}
	
	public Collection<WordsClass> getClasses() {
		// TODO
		return null;
	}
	
	/**
	 * Creates a new object in the environment and returns it.  Throws an exception if the object could not be created.
	 */
	public WordsObject createObject(String objectName, String className, WordsPosition position) {
		if (getObject(objectName) != null) {
			// TODO
			// Throw exception
		}
		
		WordsClass wordsClass = getClass(className);
		
		if (wordsClass == null) {
			// TODO
			// Throw exception
		}
		
		WordsObject newObject = new WordsObject(objectName, wordsClass, position);
		objects.put(objectName, newObject);
		
		// TODO: decide if this is appropriate (given that it could figure listeners)
		newObject.enqueueAction(new WordsWait(1));
		
		return newObject;
	}
	
	public WordsObject getObject(String objectName) {
		return objects.get(objectName);
	}
	
	public Collection<WordsObject> getObjects() {
		return objects.values();
	}
	
	public Collection<WordsEventListener> getEventListeners() {
		return eventListeners;
	}
}