package words.ast;

import words.exceptions.*;

public abstract class INodeRelOp extends INode {
	public INodeRelOp(Object... children) {
		super(children);
	}

	/**
	 * Checks that the arguments to a relational operator <, <=, >, >= are appropriate and throws an appropriate
	 * exception if not.
	 */
	protected void checkRelOpArgTypes(ASTValue lhs, ASTValue rhs) throws WordsRuntimeException {
		if ((lhs.type != ASTValue.Type.NUM && lhs.type != ASTValue.Type.STRING) ||
			(lhs.type != rhs.type)) {
			throw new OperatorTypeMismatchException(lhs.type.toString(), rhs.type.toString());
		}
	}
}