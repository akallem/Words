package words.environment;
import java.util.HashMap;

public class WordsClass {
	private HashMap<String, WordsProperty> properties;
	private HashMap<String, WordsCustomActionDefinition> functions;
	private WordsClass parent;
	private String className;
	
	public WordsClass(String className, WordsClass parent) {
		properties = new HashMap<String, WordsProperty>();
		functions = new HashMap<String, WordsCustomActionDefinition>();
		this.parent = parent;
		this.className = className;
	}
		
	public String getClassName() {
		return className;
	}
	
	/**
	 * Retrieves a property of a class by looking only at the class itself, ignoring its class chain.
	 * A missing property returns null.
	 */
	private WordsProperty getOwnProperty(String propertyName) {
		if (properties.containsKey(propertyName))
			return properties.get(propertyName);
		else
			return null;
	}
	
	/**
	 * Retrieves a property on a class by looking at the class itself and its class chain.
	 * A missing property returns a WordsProperty of type NOTHING
	 */
	public WordsProperty getProperty(String propertyName) {
		WordsClass lookupClass = this;
		
		while (lookupClass != null) {
			WordsProperty property = lookupClass.getOwnProperty(propertyName);
			
			if (property != null)
				return property;
			
			lookupClass = lookupClass.parent;
		}
		
		return new WordsProperty(WordsProperty.PropertyType.NOTHING);
	}
	
	/**
	 * Sets a property on this class for a given name, overwriting any existing property for that name.
	 * Does not set anything if the provided property is NOTHING.
	 */
	public void setProperty(String propertyName, WordsProperty property) {
		if (property.type != WordsProperty.PropertyType.NOTHING) {
			properties.put(propertyName, property);
		}
	}
	
	public WordsCustomActionDefinition getCustomActionDefinition(String customActionName) {
		// TODO
		return null;
	}
	
	public void addCustomAction(String customActionName, WordsCustomActionDefinition customActionDefinition) {
		// TODO
	}
}