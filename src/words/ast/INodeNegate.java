package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.*;

public class INodeNegate extends INode {
	public INodeNegate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue value = children.get(0).eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);

		if (value.type != ASTValue.ValueType.NUM) {
			throw new WordsInvalidTypeException("NUM", value.type.toString());
		}

		return new ASTValue(value.numValue * -1);
	}
}