package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeListener extends INode {
	public INodeListener(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		AST predicate = children.get(0);
		AST statementList = children.get(1);
		ASTValue makeTemporary = children.get(2).eval(environment);
		
		environment.createListener(predicate, statementList, makeTemporary.booleanValue);
		
		return null;
	}
}