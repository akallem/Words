package words.ast;

import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.environment.WordsProperty;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsRuntimeException;

public class INodeSubject extends INode {
	public INodeSubject(Object... children) {
		super(children);
	}

	/**
	 * Can return either a string, which is a class name
	 * or an object, which is an object. 
	 */
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue className = children.get(0) == null ? null : children.get(0).eval(environment);
		ASTValue referenceObject = children.get(1) == null ? null : children.get(1).eval(environment);
		ASTValue identifier = children.get(2) == null ? null : children.get(2).eval(environment);
		
		if (className != null) {
			return new ASTValue(className.stringValue);
		} else {
			WordsObject object;
				if (referenceObject.type.equals(ASTValue.ValueType.OBJ)){
					WordsProperty property = referenceObject.objValue.getProperty(identifier.stringValue);
					if (property.type != WordsProperty.PropertyType.OBJECT) {
						throw new WordsInvalidTypeException(ASTValue.ValueType.OBJ.toString(), property.type.toString());
					}
					object = property.objProperty;
				} else {
					object = environment.getObject(identifier.stringValue);
				}
			return new ASTValue(object);
		}
	}
}