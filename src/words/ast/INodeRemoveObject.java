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
		
		if (refList.type == ASTValue.Type.NOTHING) {
			obj = environment.getVariable(id.stringValue).objProperty;
		} else {
			Property property = refList.objValue.getProperty(id.stringValue);
			obj = property.objProperty;
			if (obj == null) {
				throw new ReferenceException(id.stringValue, property.type);
			}
		}
		
		obj.prepareForRemoval();
		
		return null;
	}
}