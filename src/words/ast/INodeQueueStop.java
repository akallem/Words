package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeQueueStop extends INode {
	public INodeQueueStop(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		// TODO
		throw new AssertionError("Not yet implemented");
	}
}