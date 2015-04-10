package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.*;

public class INodeMultiply extends INode {
	public INodeMultiply(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue lhs = children.get(0).eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);
		ASTValue rhs = children.get(1).eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);

		if ((lhs.type != ASTValue.ValueType.NUM) || (rhs.type != ASTValue.ValueType.NUM)) {
			throw new WordsArithmeticException(lhs.type.toString(), rhs.type.toString());
		}

		return new ASTValue(lhs.numValue*rhs.numValue);
	}
}