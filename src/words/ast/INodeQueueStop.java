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
			Property property = referenceObject.objValue.getProperty(identifier.stringValue);
			if (property.type != Property.PropertyType.OBJECT) {
				throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), property.type.toString());
			}
			object = property.objProperty;
		} else {
			object = environment.getObject(identifier.stringValue);
		}
		
		object.clearActionQueue();
		
		return null;
	}
}