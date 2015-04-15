package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeQueueAssignPropertyList extends INode {
	public INodeQueueAssignPropertyList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		throw new AssertionError("Evaluated by parent");
	}
}