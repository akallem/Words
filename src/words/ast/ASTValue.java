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
	public Variable varValue;
	
	public ASTValue(Variable var) {
		this.type = Type.VARIABLE;
		this.varValue = var;
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
				if (type == Type.VARIABLE) {
					if (this.varValue.type == Variable.VariableType.STRING) {
						try {  
							double val = Double.parseDouble(stringValue);
							this.numValue = val;
							this.type = newType;
						} catch (NumberFormatException nfe) {}
					} else if (this.varValue.type == Variable.VariableType.NUM) {
						this.numValue = this.varValue.numProperty;
						this.type = newType;
					}
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
				if (type == Type.VARIABLE) {
					if (this.varValue.type == Variable.VariableType.STRING) {
						this.stringValue = this.varValue.stringProperty;
						this.type = newType;
					} else if (this.varValue.type == Variable.VariableType.NUM) {
						this.stringValue = String.format("%f", this.varValue.numProperty);
						this.type = newType;
					}
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
	public Variable toWordsProperty() {
		switch (this.type) {
			case VARIABLE:
				return this.varValue;
			case NUM:
				return new Variable(this.numValue);
			case STRING:
				return new Variable(this.stringValue);
			case OBJ:
				return new Variable(this.objValue);
			case NOTHING:
				return new Variable(Variable.VariableType.NOTHING);
			default:
				throw new AssertionError("Cannot convert ASTValue of type " + this.type.toString() + "to WordsProperty");
		}
	}
}
