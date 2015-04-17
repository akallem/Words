package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeListenerPerm extends INode {
	public INodeListenerPerm(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		AST predicate = children.get(0);
		AST statementList = children.get(1);
		
		environment.createListener(predicate, statementList, false);
		
		return null;
	}
}