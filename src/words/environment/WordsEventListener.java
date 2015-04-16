package words.environment;

import words.ast.AST;
import words.ast.ASTValue;
import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class WordsEventListener {
	private AST predicate;
	private AST statementList;
	private boolean temporary;

	public WordsEventListener(AST predicate, AST statementList, boolean temporary) {
		this.predicate = predicate;
		this.statementList = statementList;
		this.temporary = temporary;
	}
	
	public boolean execute(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue predicateValue = predicate.eval(environment);

		// currently only restricted to boolean predicate
		assert predicateValue.type == ASTValue.ValueType.BOOLEAN : "Predicate has type " + predicateValue.type.toString();

		if (predicateValue.booleanValue == true) {
			statementList.eval(environment);
		} else {
			if (temporary)
				return false;
		}

		return true;
	}
	
}
