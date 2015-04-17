package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeAlias extends INode {
	public INodeAlias(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue identifier = children.size() == 0 ? null : children.get(0).eval(environment);
		if (identifier != null) {
			return identifier;
		} else {
			return new ASTValue(ASTValue.Type.NOTHING);
		}
	}
}