package words.ast;

import words.environment.Variable;
import words.environment.*;
import words.exceptions.*;

public class INodeCreateLocalVariable extends INode {
	public INodeCreateLocalVariable(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue varName = children.get(0).eval(environment);
		ASTValue rawValue = children.get(1).eval(environment);
		Variable value = null;
		
		if (rawValue.type == ASTValue.Type.NUM) {
			value = new Variable(rawValue.numValue);
		} else if (rawValue.type == ASTValue.Type.STRING) {
			value = new Variable(rawValue.stringValue);
		}
		
		environment.createLocalVariable(varName.stringValue, value);
		
		return null;
	}
}