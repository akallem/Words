package words.ast;

import words.environment.*;

/**
 * A dynamically-typed value that an AST node can return as its result.
 */
public class ASTValue {
	public enum Type {
		BOOLEAN,
		NUM,
		STRING,
		OBJ,
		DIRECTION,
		POSITION,
		NOW,
		VARIABLE,
		NOTHING
	}
	public Type type;
	
	public boolean booleanValue;
	public double numValue;
	public String stringValue;
	public WordsObject objValue;
	public Direction directionValue;
	public Position positionValue;
	
	public ASTValue(boolean b) {
		this.type = Type.BOOLEAN;
		this.booleanValue = b;
	}
	
	public ASTValue(double num) {
		this.type = Type.NUM;
		this.numValue = num;
	}	

	public ASTValue(String s) {
		this.type = Type.STRING;
		this.stringValue = s;
	}
	
	public ASTValue(WordsObject obj) {
		this.type = Type.OBJ;
		this.objValue = obj;
	}
	
	public ASTValue(Direction d) {
		this.type = Type.DIRECTION;
		this.directionValue = d;
	}

	public ASTValue(Position p) {
		this.type = Type.POSITION;
		this.positionValue = p;
	}
	
	public ASTValue(Type type) {
		this.type = type;
	}
	
	/**
	 * Attempts to coerce this ASTValue to the given type, if possible.  Regardless of whether or not it succeeds,
	 * returns this ASTValue.  The caller should then check the type to determine if the result of the coercion resulted
	 * in a suitable type for its purposes.
	 * 
	 * @return self
	 */
	public ASTValue tryCoerceTo(Type newType) {
		switch(newType) {
			case NUM:
				if (type == Type.STRING) {
					try {  
						double val = Double.parseDouble(stringValue);
						this.numValue = val;
						this.type = newType;
					} catch (NumberFormatException nfe) {}  
				}
				break;
			case STRING:
				if (type == Type.NUM) {
					this.stringValue = String.format("%f", numValue);
					this.type = newType;
				}
				if (type == Type.OBJ) {
					this.stringValue = objValue.getObjectName();
					this.type = newType;
				}
				break;
			default:
				break;
		}
		return this;
	}
	
	public void copyOtherVariable(ASTValue other) {
		this.type = other.type;
		this.booleanValue = other.booleanValue;
		this.directionValue = other.directionValue;
		this.numValue = other.numValue;
		this.objValue = other.objValue;
		this.positionValue = other.positionValue;
		this.stringValue = other.stringValue;
	}
}
