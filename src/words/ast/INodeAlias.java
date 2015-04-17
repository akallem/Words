package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeAlias extends INode {
	public INodeAlias(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue identifier = children.get(0) == null ? null : children.get(0).eval(environment);
		if (identifier != null) {
			return identifier;
		} else {
			return new ASTValue(ASTValue.ValueType.NOTHING);
		}
	}
}