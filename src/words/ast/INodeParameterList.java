package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeParameterList extends INode {
	public INodeParameterList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		// TODO
		throw new AssertionError("Not yet implemented");
	}
}