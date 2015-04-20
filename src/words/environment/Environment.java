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
	//private LinkedList<HashMap<String, Property>> variables;
	private Scope currentScope;
	private HashMap<WordsClass, HashSet<WordsObject>> objectsByClass;
	private ArrayList<WordsEventListener> eventListeners;
	private static final String BASE_SUPERCLASS = "thing";
	
	public Environment() {
		classes = new HashMap<String, WordsClass>();
		//variables = new LinkedList<HashMap<String, Property>>();
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
		currentScope = new Scope(null);
	}
	
	/**
	 * Resets the environment.
	 */
	public void resetEnvironment() {
		classes.clear();
		//variables.clear();
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
	public void pushScope() {
		Scope newScope = new Scope(currentScope);
		currentScope = newScope;
	}
	
	/**
	 * Exits the current local scope. Moves all objects in the local scope into the ungettable
	 * objects collection, where they will continue to live, but will not be callable by name
	 */
	public void popScope() {
		assert currentScope.parent != null : "Tried to pop the global scope";
		currentScope = currentScope.parent;
	}
	
	/**
	 * Get the current scope depth.  Includes the global scope.
	 */
	public int getScopeDepth() {
		return currentScope.getDepth();
	}
	
	/**
	 * Creates a new object in the environment and returns it.  Throws an exception if the object could not be created.
	 * A new object is always added to the most local scope in use
	 */
	public WordsObject createObject(String objectName, String className, Position position) throws WordsRuntimeException {
		Property property = getVariable(objectName);
		
		if (property.type == Property.PropertyType.NOTHING) {
			WordsClass wordsClass = getClass(className);
			
			WordsObject newObject = new WordsObject(objectName, wordsClass, position);
			currentScope.variables.put(objectName, new Property(newObject));
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
		} else {
			throw new ObjectAlreadyExistsException(objectName);
		}
	}
	
	/**
	 * Adds a named object to the most local scope in use. 
	 */
	public void addVariableToCurrentNameScope(String objectName, Property variable) {
		currentScope.variables.put(objectName, variable);
	}
	
	/**
	 * Looks up a variable and returns it.  If not found, returns NOTHING.
	 */
	public Property getVariable(String variableName) {
		Property prop = null;
		Scope scope = currentScope;
		
		while (scope != null) {
			prop = scope.variables.get(variableName);
			if (prop != null)
				break;
			scope = scope.parent;
		}
		
		if (prop == null) {
			return new Property(Property.PropertyType.NOTHING);
		} else {
			return prop;	
		}
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