package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueMove extends INode {
	public INodeQueueMove(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue referenceObject = children.get(0).eval(environment);
		ASTValue identifier = children.get(1).eval(environment);
		ASTValue direction = children.get(2).eval(environment);
		AST distance = children.get(3);
		ASTValue doNow = children.get(4) != null ? children.get(4).eval(environment) : null;
		
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
		
		assert(direction.type == ASTValue.Type.DIRECTION) : "Expected direction";
		
		MoveAction action = new MoveAction(direction.directionValue, distance);
		
		if (doNow == null) {
			object.enqueueAction(action);
		} else {
			object.enqueueActionAtFront(action);
		}
		
		return null;
	}
}