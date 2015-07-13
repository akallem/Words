package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueStop extends INode {
	public INodeQueueStop(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		AST referenceObject = children.get(0);
		AST identifier = children.get(1);
		
		Variable property = lookupProperty(environment, referenceObject, identifier);
		if (property.type != Variable.VariableType.OBJECT) {
			throw new InvalidTypeException(ASTValue.Type.OBJ.toString(), property.type.toString());
		}
		WordsObject object = property.objProperty;
		
		object.clearActionQueue();
		
		return null;
	}
}