package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeAdd extends INode {
	public INodeAdd(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue lhs = children.get(0).eval(environment).tryCoerceTo(ASTValue.Type.NUM);
		ASTValue rhs = children.get(1).eval(environment).tryCoerceTo(ASTValue.Type.NUM);
		
		// check for objects
		if (lhs.type == ASTValue.Type.OBJ) {
			lhs = lhs.tryCoerceTo(ASTValue.Type.STRING);
		}
		if (rhs.type == ASTValue.Type.OBJ) {
			rhs = rhs.tryCoerceTo(ASTValue.Type.STRING);
		}
		
		if (((lhs.type == ASTValue.Type.STRING) && (rhs.type == ASTValue.Type.NUM)) || 
				((lhs.type == ASTValue.Type.NUM) && (rhs.type == ASTValue.Type.STRING)) ||
				((lhs.type == ASTValue.Type.STRING) && (rhs.type == ASTValue.Type.STRING))) {
			lhs.tryCoerceTo(ASTValue.Type.STRING);
			rhs.tryCoerceTo(ASTValue.Type.STRING);
			return new ASTValue(lhs.stringValue+rhs.stringValue);
		} else if ((lhs.type == ASTValue.Type.NUM) && (rhs.type == ASTValue.Type.NUM)) {
			return new ASTValue(lhs.numValue+rhs.numValue); 
		} else {
			throw new WordsArithmeticException(lhs.type.toString(), rhs.type.toString());
		}
	}
}