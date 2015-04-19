package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeIdentifierList extends INode {
	public INodeIdentifierList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		// TODO
		throw new AssertionError("Not yet implemented");
	}
}