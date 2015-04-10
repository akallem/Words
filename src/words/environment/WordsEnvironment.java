package words.environment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import words.exceptions.WordsClassNotFoundException;
import words.exceptions.WordsObjectAlreadyExistsException;
import words.exceptions.WordsObjectNotFoundException;
import words.exceptions.WordsRuntimeException;

public class WordsEnvironment {
	private HashMap<String, WordsClass> classes;
	/* gettableObjects is a list of locally scoped symbol tables with the most local 
	 * scope first in the list, and global scope always being last in the list.
	 */
	private LinkedList<HashMap<String, WordsObject>> gettableObjects;
	/* ungettableObjects contains the objects that have gone out of scope, but should still appear
	 * on the board, and are still used by action listeners */
	private HashSet<WordsObject> ungettableObjects;
	private ArrayList<WordsEventListener> eventListeners;
	
	public WordsEnvironment() {
		classes = new HashMap<String, WordsClass>();
		gettableObjects = new LinkedList<HashMap<String, WordsObject>>();
		gettableObjects.push(new HashMap<String, WordsObject>());
		eventListeners = new ArrayList<WordsEventListener>();
		ungettableObjects = new HashSet<WordsObject>();
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
	 * Enters a new local scope by pushing a new scope onto the gettable objects stack
	 */
	public void enterNewLocalScope() {
		gettableObjects.push(new HashMap<String, WordsObject>());
	}
	
	/**
	 * Exits the current local scope. Moves all objects in the local scope into the ungettable
	 * objects collection, where they will continue to live, but will not be callable by name
	 */
	public void exitLocalScope() {
		HashMap<String, WordsObject> localScope = gettableObjects.pop();
		ungettableObjects.addAll(localScope.values());
		assert gettableObjects.size() > 0 : "You just popped the global scope off the objects table";
	}
	
	/**
	 * Creates a new object in the environment and returns it.  Throws an exception if the object could not be created.
	 * A new object is always added to the most local scope in use
	 */
	public WordsObject createObject(String objectName, String className, WordsPosition position) throws WordsRuntimeException {
		try {
			getObject(objectName);
			throw new WordsObjectAlreadyExistsException(objectName);
		} catch (WordsObjectNotFoundException e){
		
			WordsClass wordsClass = getClass(className);
			
			WordsObject newObject = new WordsObject(objectName, wordsClass, position);
			gettableObjects.getFirst().put(objectName, newObject);
			
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
		WordsObject wordsObj = null;
		for (int i = 0; i < gettableObjects.size() && wordsObj == null; i++) {
			wordsObj = gettableObjects.get(i).get(objectName);
		}
		if (wordsObj == null) {
			throw new WordsObjectNotFoundException(objectName);
		}
		
		return wordsObj;
	}
	
	/**
	 * Returns a collection of all objects.
	 */
	public Collection<WordsObject> getObjects() {
		Collection<WordsObject> allObjects = new HashSet<WordsObject>();
		for (HashMap<String,WordsObject> scopeObjects : gettableObjects) {
			allObjects.addAll(scopeObjects.values());
		}
		allObjects.addAll(ungettableObjects);
		return allObjects;
	}
	
	/**
	 * Returns a collection of all event listeners.
	 */
	public Collection<WordsEventListener> getEventListeners() {
		return eventListeners;
	}
}