package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeTouchesPredicate extends INode {
	public INodeTouchesPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		// TODO
		throw new AssertionError("Not yet implemented");
	}
}