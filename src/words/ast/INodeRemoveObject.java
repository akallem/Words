package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeRemoveObject extends INode {
	public INodeRemoveObject(Object... children) {
		super(children);
	}	

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		WordsObject obj = null;
		ASTValue refList = children.get(0).eval(environment);
		ASTValue id = children.get(1).eval(environment);
		
		Variable property;
		if (refList.type == ASTValue.Type.NOTHING) {
			property = environment.getVariable(id.stringValue);
			if (property.type != Variable.VariableType.OBJECT) {
				throw new ObjectNotFoundException(id.stringValue);
			}
		} else {
			property = refList.objValue.getProperty(id.stringValue);
			if (property.type != Variable.VariableType.OBJECT) {
				throw new ReferenceException(id.stringValue, property.type);
			}
		}
		
		obj = property.objProperty;
		obj.flagForRemoval();
		
		return null;
	}
}