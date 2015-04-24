package words.environment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import words.exceptions.*;
import words.ast.*;

public class Environment {
	private HashMap<String, WordsClass> classes;
	/* objectsByName is a list of locally scoped symbol tables with the most local 
	 * scope first in the list, and global scope always being last in the list.
	 */
	private LinkedList<HashMap<String, WordsObject>> objectsByName;
	/*
	 * Objects always persist in the map where they can be gotten by class
	 */
	private HashMap<WordsClass, HashSet<WordsObject>> objectsByClass;
	private ArrayList<WordsEventListener> eventListeners;
	private static final String BASE_SUPERCLASS = "thing";
	
	public Environment() {
		classes = new HashMap<String, WordsClass>();
		objectsByName = new LinkedList<HashMap<String, WordsObject>>();
		eventListeners = new ArrayList<WordsEventListener>();
		objectsByClass = new HashMap<WordsClass, HashSet<WordsObject>>();
		setupEnvironment();
	}
	
	/**
	 * Creates the base class thing.
	 */
	private void setupEnvironment() {
		WordsClass thing = new WordsClass(BASE_SUPERCLASS, null);
		classes.put(BASE_SUPERCLASS, thing);
		objectsByClass.put(thing, new HashSet<WordsObject>());
		objectsByName.push(new HashMap<String, WordsObject>());
	}
	
	/**
	 * Resets the environment.
	 */
	public void resetEnvironment() {
		classes.clear();
		objectsByName.clear();
		objectsByClass.clear();
		eventListeners.clear();
		setupEnvironment();
	}

	/**
	 * Creates a new class in the environment and returns it.  Throws an exception if the class could not be created.
	 * @throws ClassAlreadyExistsException 
	 * @throws WordsClassNotFoundException 
	 */
	public WordsClass createClass(String className, String parent) throws WordsRuntimeException {
		
		try {
			getClass(className);
			throw new ClassAlreadyExistsException(className);
		} catch (WordsClassNotFoundException e){
			WordsClass parentClass = getClass(parent);
			WordsClass wordsClass = new WordsClass(className, parentClass);
			classes.put(className, wordsClass);
			objectsByClass.put(wordsClass, new HashSet<WordsObject>());
			
			return wordsClass;
		}
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
		objectsByName.push(new HashMap<String, WordsObject>());
	}
	
	/**
	 * Exits the current local scope. Moves all objects in the local scope into the ungettable
	 * objects collection, where they will continue to live, but will not be callable by name
	 */
	public void exitLocalScope() {
		objectsByName.pop();
		assert objectsByName.size() > 0 : "You just popped the global scope off the objects table";
	}
	
	/**
	 * Get the number of different object scopes at the moment. Includes the global scope.
	 */
	public int getNumberOfScopes() {
		return objectsByName.size();
	}
	
	/**
	 * Creates a new object in the environment and returns it.  Throws an exception if the object could not be created.
	 * A new object is always added to the most local scope in use
	 */
	public WordsObject createObject(String objectName, String className, Position position) throws WordsRuntimeException {
		try {
			getObject(objectName);
			throw new ObjectAlreadyExistsException(objectName);
		} catch (ObjectNotFoundException e){
		
			WordsClass wordsClass = getClass(className);
			
			WordsObject newObject = new WordsObject(objectName, wordsClass, position);
			objectsByName.getFirst().put(objectName, newObject);
			if (objectsByClass.containsKey(wordsClass)) {
				objectsByClass.get(wordsClass).add(newObject);
			} else {
				HashSet<WordsObject> newSet = new HashSet<WordsObject>();
				newSet.add(newObject);
				objectsByClass.put(wordsClass, newSet);
			}
			
			// TODO: decide if this is appropriate (given that it could figure listeners)
			newObject.enqueueAction(new WaitAction(new LNodeNum(1)));
			
			return newObject;
		}
	}
	
	public void removeObject(WordsObject object) {
		object.clearReferers();
		
		for (HashMap<String, WordsObject> scope : objectsByName) {
			if (scope.remove(object.getObjectName()) != null) {
				break;
			}
		}
		
		objectsByClass.get(object.getWordsClass()).remove(object);
	}
	
	/**
	 * Adds a named object to the most local scope in use. 
	 */
	public void addObjectToCurrentNameScope(String objectName, WordsObject object) {
		objectsByName.getFirst().put(objectName, object);
	}
	
	/**
	 * 
	 * @param objectName
	 * @return the words object
	 * @throws WordsRuntimeException if object not found
	 */
	public WordsObject getObject(String objectName) throws WordsRuntimeException {
		WordsObject wordsObj = null;
		for (int i = 0; i < objectsByName.size() && wordsObj == null; i++) {
			wordsObj = objectsByName.get(i).get(objectName);
		}
		if (wordsObj == null) {
			throw new ObjectNotFoundException(objectName);
		}
		
		return wordsObj;
	}
	
	/**
	 * Returns a collection of all objects.
	 */
	public Collection<WordsObject> getObjects() {
		try {
			return getObjectsByClass(BASE_SUPERCLASS);
		} catch (WordsClassNotFoundException e) {
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Return a collection of all objects of a certain class, as well as all the objects
	 * that exist in children classes of the certain class.
	 * Returns an empty collection if there are no objects of that class in the environment
	 * @throws a WordsClassNotFoundException if the class does not exist.
	 */
	public HashSet<WordsObject> getObjectsByClass(String className) throws WordsClassNotFoundException {
		WordsClass wc = getClass(className);
		HashSet<WordsObject> objectsToReturn = objectsByClass.get(wc);
		for (WordsClass childClass : wc.getChildren()) {
			objectsToReturn.addAll(getObjectsByClass(childClass.getClassName()));
		}
		return objectsToReturn;
	}
	
	/**
	 * Create a new event listener.
	 */
	public void createListener(AST predicate, AST statementList, boolean temporary) {
		eventListeners.add(new WordsEventListener(predicate, statementList, temporary));
	}

	/**
	 * Returns a collection of all event listeners.
	 */
	public Collection<WordsEventListener> getEventListeners() {
		return eventListeners;
	}
}