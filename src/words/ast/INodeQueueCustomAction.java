package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueCustomAction extends INode {
	public INodeQueueCustomAction(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		AST referenceObject = children.get(0);
		AST identifier = children.get(1);
		ASTValue actionName = children.get(2).eval(environment);
		AST argumentList = children.get(3);
		ASTValue doNow = children.get(4) != null ? children.get(4).eval(environment) : null;
		
		Variable property = lookupProperty(environment, referenceObject, identifier);
		if (property.type != Variable.VariableType.OBJECT) {
			throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), property.type.toString());
		}
		WordsObject object = property.objProperty;
				
		CustomActionDefinition actionDefinition = object.getWordsClass().getCustomActionDefinition(actionName.stringValue);
		
		if (doNow == null) {
			object.enqueueAction(new CustomAction(environment.getCurrentScope(), actionDefinition, argumentList));
		} else {
			object.enqueueActionAtFront(new CustomAction(environment.getCurrentScope(), actionDefinition, argumentList));
		}
		
		return null;
	}
}