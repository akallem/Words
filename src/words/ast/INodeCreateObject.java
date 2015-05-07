package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeCreateObject extends INode {
	public INodeCreateObject(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue objName = children.get(0).eval(environment);
		ASTValue className = children.get(1).eval(environment);
		ASTValue position = children.get(2).eval(environment);

		environment.createObject(objName.stringValue, className.stringValue, position.positionValue);
		
		return null;
	}
}