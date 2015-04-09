package words.environment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import words.exceptions.WordsClassNotFoundException;
import words.exceptions.WordsObjectAlreadyExistsException;
import words.exceptions.WordsObjectNotFoundException;
import words.exceptions.WordsRuntimeException;

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
	
	/**
	 * Creates the base class thing.
	 */
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
	
	/**
	 * Retrieves a class by name.  Returns null if no such class exists.
	 * @throws WordsClassNotFoundException 
	 */
	public WordsClass getClass(String className) throws WordsClassNotFoundException {
		WordsClass wordsClass = classes.get(className);
		if (wordsClass == null) {
			throw new WordsClassNotFoundException(className);
		}
		
		return wordsClass;
	}

	/**
	 * Returns a collection of all classes.
	 */
	public Collection<WordsClass> getClasses() {
		// TODO
		return null;
	}
	
	/**
	 * Creates a new object in the environment and returns it.  Throws an exception if the object could not be created.
	 */
	public WordsObject createObject(String objectName, String className, WordsPosition position) throws WordsRuntimeException {
		try {
			getObject(objectName);
			throw new WordsObjectAlreadyExistsException(objectName);
		} catch (WordsObjectNotFoundException e){
		
			WordsClass wordsClass = getClass(className);
			
			WordsObject newObject = new WordsObject(objectName, wordsClass, position);
			objects.put(objectName, newObject);
			
			// TODO: decide if this is appropriate (given that it could figure listeners)
			newObject.enqueueAction(new WordsWait(1));
			
			return newObject;
		}
	}
	
	/**
	 * 
	 * @param objectName
	 * @return the words object
	 * @throws WordsRuntimeException if object not found
	 */
	public WordsObject getObject(String objectName) throws WordsRuntimeException {
		WordsObject wordsObj = objects.get(objectName);
		if (wordsObj == null) {
			throw new WordsObjectNotFoundException(objectName);
		}
		
		return wordsObj;
	}
	
	/**
	 * Returns a collection of all objects.
	 */
	public Collection<WordsObject> getObjects() {
		return objects.values();
	}
	
	/**
	 * Returns a collection of all event listeners.
	 */
	public Collection<WordsEventListener> getEventListeners() {
		return eventListeners;
	}
}