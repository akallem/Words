package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeNegate extends INode {
	public INodeNegate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue value = children.get(0).eval(environment).tryCoerceTo(ASTValue.Type.NUM);

		if (value.type != ASTValue.Type.NUM) {
			throw new InvalidTypeException(ASTValue.Type.NUM.toString(), value.type.toString());
		}

		return new ASTValue(value.numValue * -1);
	}
}