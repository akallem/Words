package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeIf extends INode {
	public INodeIf(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue predicate = children.get(0).eval(environment);
		AST statementList = children.get(1);
		
		assert predicate.type == ASTValue.Type.BOOLEAN : "Predicate has type " + predicate.type.toString();
		
		if (predicate.booleanValue == true) {
			environment.pushNewScope();
			statementList.eval(environment);
			environment.popScope();
		}
		
		return null;
	}
}