package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeNot extends INode {
	public INodeNot(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue predicate = children.get(0).eval(environment);
		
		assert predicate.type == ASTValue.Type.BOOLEAN : "Predicate has type " + predicate.type.toString();
		
		return new ASTValue(!predicate.booleanValue);
	}
}