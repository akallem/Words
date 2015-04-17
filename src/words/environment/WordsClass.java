package words.environment;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsClass {
	private HashMap<String, Property> properties;
	private HashMap<String, CustomAction> functions;
	private WordsClass parent;
	private String className;
	private ArrayList<WordsClass> children;
	
	public WordsClass(String className, WordsClass parent) {
		properties = new HashMap<String, Property>();
		functions = new HashMap<String, CustomAction>();
		children = new ArrayList<WordsClass>();
		this.parent = parent;
		this.className = className;
		if (parent != null) {
			// everything except the base superclass should have a parent. 
			parent.registerChild(this);
		}
	}
		
	public String getClassName() {
		return className;
	}
	
	/**
	 * Register a child to its parent
	 * 
	 */
	public void registerChild(WordsClass childClass) {
		children.add(childClass);
	}
	
	/**
	 * Get all classes that inherit from this one
	 */
	public ArrayList<WordsClass> getChildren() {
		return children;
	}
	
	/**
	 * Retrieves a property of a class by looking only at the class itself, ignoring its class chain.
	 * A missing property returns null.
	 */
	private Property getOwnProperty(String propertyName) {
		if (properties.containsKey(propertyName))
			return properties.get(propertyName);
		else
			return null;
	}
	
	/**
	 * Retrieves a property on a class by looking at the class itself and its class chain.
	 * A missing property returns a WordsProperty of type NOTHING
	 */
	public Property getProperty(String propertyName) {
		WordsClass lookupClass = this;
		
		while (lookupClass != null) {
			Property property = lookupClass.getOwnProperty(propertyName);
			
			if (property != null)
				return property;
			
			lookupClass = lookupClass.parent;
		}
		
		return new Property(Property.PropertyType.NOTHING);
	}
	
	/**
	 * Sets a property on this class for a given name, overwriting any existing property for that name.
	 * Does not set anything if the provided property is NOTHING.
	 */
	public void setProperty(String propertyName, Property property) {
		if (property.type != Property.PropertyType.NOTHING) {
			properties.put(propertyName, property);
		}
	}
	
	
	public void addCustomAction(String customActionName, CustomAction customAction) {
		// TODO
	}
}