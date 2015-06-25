package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueWait extends INode {
	public INodeQueueWait(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		AST referenceObject = children.get(0);
		AST identifier = children.get(1);
		AST lengthExpression = children.get(2);
		ASTValue doNow = children.get(3) != null ? children.get(3).eval(environment) : null;

		ASTValue property = lookupProperty(environment, referenceObject, identifier);
		if (property.type != ASTValue.Type.OBJ) {
			throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), property.type.toString());
		}
		WordsObject object = property.objValue;

		WaitAction action = new WaitAction(environment.getCurrentScope(), lengthExpression);

		if (doNow == null) {
			object.enqueueAction(action);
		} else {
			object.enqueueActionAtFront(action);
		}

		return null;
	}
}