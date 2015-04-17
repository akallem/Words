package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeQueueAssignPropertyList extends INode {
	public INodeQueueAssignPropertyList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		assert false : "Cannot eval INodeQueueAssignPropertyList without inherited WordsObject";
		return null;
	}
	
	@Override
	public ASTValue eval(WordsEnvironment environment, Object inherited) throws WordsRuntimeException {
		for (AST ast : children) {
			ast.eval(environment, inherited);
		}
		
		return null;
	}
}