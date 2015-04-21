package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeRepeat extends INode {
	public INodeRepeat(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue times = children.get(0).eval(environment).tryCoerceTo(ASTValue.Type.NUM);
		AST statementList = children.get(1);
		
		if (times.type != ASTValue.Type.NUM) {
			throw new InvalidTypeException(ASTValue.Type.NUM.toString(), times.type.toString());
		}
		
		for (int i = 0; i < times.numValue; i++) {
			environment.pushNewScope();
			statementList.eval(environment);
			environment.popScope();
		}
		
		return null;
	}
}