package words.ast;

import words.environment.*;
import words.exceptions.*;
import words.ast.*;


public class INodeWhile extends INode {
	public INodeWhile(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue conditional = children.get(0).eval(environment);
		AST statementList = children.get(1);
		
		assert conditional.type == ASTValue.Type.BOOLEAN;

		while (conditional.booleanValue == true) {
			environment.enterNewLocalScope();
			statementList.eval(environment);
			environment.exitLocalScope();
			conditional = children.get(0).eval(environment);
		}
		
		return null;
	}
}