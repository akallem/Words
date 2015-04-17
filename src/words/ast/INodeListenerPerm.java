package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeListenerPerm extends INode {
	public INodeListenerPerm(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		AST predicate = children.get(0);
		AST statementList = children.get(1);
		
		environment.createListener(predicate, statementList, false);
		
		return null;
	}
}