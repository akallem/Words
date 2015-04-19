package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeSubject extends INode {
	public INodeSubject(Object... children) {
		super(children);
	}

	/**
	 * Can return either a string, which is a class name
	 * or an object, which is an object. 
	 */
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue className = children.get(0) == null ? null : children.get(0).eval(environment);
		ASTValue referenceObject = children.get(1) == null ? null : children.get(1).eval(environment);
		ASTValue identifier = children.get(2) == null ? null : children.get(2).eval(environment);
		
		if (className != null) {
			return new ASTValue(className.stringValue);
		} else {
			WordsObject object;
				if (referenceObject.type.equals(ASTValue.Type.OBJ)){
					Property property = referenceObject.objValue.getProperty(identifier.stringValue);
					if (property.type != Property.PropertyType.OBJECT) {
						throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), property.type.toString());
					}
					object = property.objProperty;
				} else {
					object = environment.getObject(identifier.stringValue);
				}
			return new ASTValue(object);
		}
	}
}