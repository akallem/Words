package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeQueueAssignProperty extends INode {
	public INodeQueueAssignProperty(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		throw new AssertionError("Evaluated by partent");
	}
}