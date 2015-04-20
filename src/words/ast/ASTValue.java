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
		NOTHING
	}
	public Type type;
	
	public boolean booleanValue;
	public double numValue;
	public String stringValue;
	public WordsObject objValue;
	public Direction directionValue;
	public Position positionValue;
	
	public ASTValue(Property property) {
		switch (property.type) {
			case NOTHING:
				this.type = ASTValue.Type.NOTHING;
				break;
			case NUM:
				this.type = Type.NUM;
				this.numValue = property.numProperty;
				break;
			case OBJECT:
				this.type = Type.OBJ;
				this.objValue = property.objProperty;
				break;
			case STRING:
				this.type = Type.STRING;
				this.stringValue = property.stringProperty;
				break;
			default:
				break;
		}
	}
	
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
				break;
			default:
				break;
		}
		
		return this;
	}
	
	/**
	 * Returns a WordsProperty object of type NUM, STRING, OBJ, or NOTHING
	 * corresponding to this ASTValue.  Calling on an ASTValue with a type
	 * other than those listed above is prohibited.
	 */
	public Property toWordsProperty() {
		switch (this.type) {
			case NUM:
				return new Property(this.numValue);
			case STRING:
				return new Property(this.stringValue);
			case OBJ:
				return new Property(this.objValue);
			case NOTHING:
				return new Property(Property.PropertyType.NOTHING);
			default:
				throw new AssertionError("Cannot convert ASTValue of type " + this.type.toString() + "to WordsProperty");
		}
	}
}
