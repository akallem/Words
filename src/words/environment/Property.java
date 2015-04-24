package words.environment;

/**
 * A dynamically typed value which can represent a property assigned to a WordsObject or a variable, such as a parameter inside a custom method.
 */
public class Property {
	public enum PropertyType {
		NOTHING, NUM, STRING, OBJECT
	}
	
	public PropertyType type;
	
	public double numProperty;
	public String stringProperty;
	public WordsObject objProperty;
	
	public Property(int n) {
		this.type = PropertyType.NUM;
		this.numProperty = (double) n;
	}
	
	public Property(double n) {
		this.type = PropertyType.NUM;
		this.numProperty = n;
	}

	public Property(String s) {
		this.type = PropertyType.STRING;
		this.stringProperty = s;
	}
	
	public Property(WordsObject o) {
		this.type = PropertyType.OBJECT;
		this.objProperty = o;
	}
	
	public Property(PropertyType type) {
		this.type = type;
	}
}