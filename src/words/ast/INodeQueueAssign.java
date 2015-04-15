package words.ast;

import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.environment.WordsPropertyAssignment;
import words.exceptions.WordsRuntimeException;

public class INodeQueueAssign extends INode {
	public INodeQueueAssign(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue referenceObject = children.get(0).eval(environment);
		AST propertyList = children.get(1);
		ASTValue doNow = children.get(2) != null ? children.get(2).eval(environment) : null;
		
		WordsPropertyAssignment action = new WordsPropertyAssignment(propertyList);
		WordsObject object = referenceObject.objValue;
		
		if (doNow == null) {
			object.enqueueAction(action);
		} else {
			object.enqueueActionAtFront(action);
		}
		
		return null;
	}
}