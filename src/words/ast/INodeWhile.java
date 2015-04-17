package words.ast;

import words.ast.ASTValue.ValueType;
import words.environment.WordsEnvironment;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsRuntimeException;

public class INodeWhile extends INode {
	public INodeWhile(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue conditional = children.get(0).eval(environment);
		AST statementList = children.get(1);
		
		assert conditional.type == ASTValue.ValueType.BOOLEAN;

		while (conditional.booleanValue == true) {
			environment.enterNewLocalScope();
			statementList.eval(environment);
			environment.exitLocalScope();
			conditional = children.get(0).eval(environment);
		}
		
		return null;
	}
}