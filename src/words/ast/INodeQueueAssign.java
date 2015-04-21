package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueAssign extends INode {
	public INodeQueueAssign(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue referenceObject = children.get(0).eval(environment);
		AST propertyList = children.get(1);
		ASTValue doNow = children.get(2) != null ? children.get(2).eval(environment) : null;
		
		PropertyAssignAction action = new PropertyAssignAction(environment.getCurrentScope(), propertyList);
		WordsObject object = referenceObject.objValue;
		
		if (doNow == null) {
			object.enqueueAction(action);
		} else {
			object.enqueueActionAtFront(action);
		}
		
		return null;
	}
}