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
	private Scope globalScope;
	private LinkedList<Scope> stack;		// Stack of scopes which is the control link
	private HashMap<WordsClass, HashSet<WordsObject>> objectsByClass;
	private ArrayList<WordsEventListener> eventListeners;
	private static final String BASE_SUPERCLASS = "thing";
	
	public Environment() {
		classes = new HashMap<String, WordsClass>();
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
		stack = new LinkedList<Scope>();
		globalScope = new Scope(null);
		stack.push(globalScope);
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
	
	public Scope getCurrentScope() {
		return stack.getFirst();
	}
	
	public Scope getGlobalScope() {
		return globalScope;
	}
	
	/**
	 * Creates and enters a new local scope with the current scope as the parent.
	 */
	public void pushNewScope() {
		pushNewScope(getCurrentScope());
	}
	
	/**
	 * Creates and enters a new local scope with the given scope as the parent.
	 */
	public void pushNewScope(Scope parent) {
		stack.push(new Scope(parent));
	}
	
	/**
	 * Enters an existing scope.
	 */
	public void pushExistingScope(Scope scope) {
		stack.push(scope);
	}
	
	/**
	 * Exits the current local scope.
	 */
	public void popScope() {
		stack.pop();
		assert stack.size() > 0 : "Popped the global scope";
	}
	
	/**
	 * Get the current scope depth.  Includes the global scope.
	 */
	public int getScopeDepth() {
		return stack.size();
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
			getCurrentScope().variables.put(objectName, new Property(newObject));
			if (objectsByClass.containsKey(wordsClass)) {
				objectsByClass.get(wordsClass).add(newObject);
			} else {
				HashSet<WordsObject> newSet = new HashSet<WordsObject>();
				newSet.add(newObject);
				objectsByClass.put(wordsClass, newSet);
			}
			
			// TODO: decide if this is appropriate (given that it could figure listeners)
			newObject.enqueueAction(new WaitAction(getCurrentScope(), new LNodeNum(1)));
			
			return newObject;
		} else {
			throw new ObjectAlreadyExistsException(objectName);
		}
	}
	
	/**
	 * Adds a named object to the most local scope in use. 
	 */
	public void addToCurrentScope(String objectName, Property variable) {
		getCurrentScope().variables.put(objectName, variable);
	}
	
	/**
	 * Looks up a variable and returns it.  If not found, returns NOTHING.
	 */
	public Property getVariable(String variableName) {
		Property prop = null;
		Scope scope = getCurrentScope();
		
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