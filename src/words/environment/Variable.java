package words.environment;

/**
 * A dynamically typed value which can represent a property assigned to a WordsObject or a variable, such as a parameter inside a custom method.
 */
public class Variable {
	public enum VariableType {
		NOTHING, NUM, STRING, OBJECT
	}
	
	public VariableType type;
	
	public double numProperty;
	public String stringProperty;
	public WordsObject objProperty;
	
	public Variable(int n) {
		this.type = VariableType.NUM;
		this.numProperty = (double) n;
	}
	
	public Variable(double n) {
		this.type = VariableType.NUM;
		this.numProperty = n;
	}

	public Variable(String s) {
		this.type = VariableType.STRING;
		this.stringProperty = s;
	}
	
	public Variable(WordsObject o) {
		this.type = VariableType.OBJECT;
		this.objProperty = o;
	}
	
	public Variable(VariableType type) {
		this.type = type;
	}
	
	public void copyOtherVariable(Variable other) {
		this.type = other.type;
		this.numProperty = other.numProperty;
		this.objProperty = other.objProperty;
		this.stringProperty = other.stringProperty;
	}
}