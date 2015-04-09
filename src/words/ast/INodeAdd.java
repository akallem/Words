package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.*;

public class INodeAdd extends INode {
	public INodeAdd(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue lhs = children.get(0).eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);
		ASTValue rhs = children.get(1).eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);
		
		if (((lhs.type == ASTValue.ValueType.STRING) && (rhs.type == ASTValue.ValueType.NUM)) || 
				((lhs.type == ASTValue.ValueType.NUM) && (rhs.type == ASTValue.ValueType.STRING)) ||
				((lhs.type == ASTValue.ValueType.STRING) && (rhs.type == ASTValue.ValueType.STRING))) {
			lhs.tryCoerceTo(ASTValue.ValueType.STRING);
			rhs.tryCoerceTo(ASTValue.ValueType.STRING);
			return new ASTValue(lhs.stringValue+rhs.stringValue);
		} else if ((lhs.type == ASTValue.ValueType.NUM) && (rhs.type == ASTValue.ValueType.NUM)) {
			return new ASTValue(lhs.numValue+rhs.numValue); 
		} else {
			throw new WordsArithmeticException(lhs.type.toString(), rhs.type.toString());
		}
	}
}