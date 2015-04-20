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
		
		if (refList.type == ASTValue.Type.NOTHING) {
			ASTValue id = children.get(1).eval(environment);
			obj = environment.getObject(id.stringValue);
		} else {
			obj = refList.objValue;
		}
		
		assert obj != null : "Obj was null when it shouldn't have been.";
		environment.removeObject(obj);
		
		return null;
	}
}