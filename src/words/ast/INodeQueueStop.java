package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueStop extends INode {
	public INodeQueueStop(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue referenceObject = children.get(0).eval(environment);
		ASTValue identifier = children.get(1).eval(environment);
		
		WordsObject object;
		if (referenceObject.type.equals(ASTValue.Type.OBJ)){
			object = referenceObject.objValue;
			Property property = object.getProperty(identifier.stringValue);
			if (property.type != Property.PropertyType.OBJECT) {
				throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), property.type.toString());
			} else {
				object = property.objProperty;
			}
		} else {
			throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), referenceObject.type.toString());
		}
		
		object.clearQueue();
		
		return null;
	}
}