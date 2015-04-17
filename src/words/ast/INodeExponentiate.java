package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeExponentiate extends INode {
	public INodeExponentiate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue lhs = children.get(0).eval(environment).tryCoerceTo(ASTValue.Type.NUM);
		ASTValue rhs = children.get(1).eval(environment).tryCoerceTo(ASTValue.Type.NUM);

		if ((lhs.type != ASTValue.Type.NUM) || (rhs.type != ASTValue.Type.NUM)) {
			throw new WordsArithmeticException(lhs.type.toString(), rhs.type.toString());
		}

		return new ASTValue(Math.pow(lhs.numValue, rhs.numValue));	
	}
}