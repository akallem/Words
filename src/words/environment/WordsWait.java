package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.ASTValue.ValueType;
import words.ast.LNodeNum;
import words.exceptions.WordsFunctionArgsException;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class WordsWait extends WordsAction {
	private AST lengthExpression;

	public WordsWait() {
		this.lengthExpression = null;
	}

	public WordsWait(AST lengthExpression) {
		this.lengthExpression = lengthExpression;
	}

	@Override
	public boolean isExecutable() {
		return (lengthExpression == null);
	}

	@Override
	public void doExecute(WordsObject object, WordsEnvironment environment) throws WordsProgramException {
		// Nothing to do, this will simply cause the object to wait 1 frame
	}

	@Override
	public LinkedList<WordsAction> doExpand(WordsObject object, WordsEnvironment environment) throws WordsProgramException {
		assert lengthExpression != null : "Length expression for Wait is null";

		ASTValue value;
		try {
			value = lengthExpression.eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(lengthExpression, e);
		}

		if (value.type != ASTValue.ValueType.NUM) {
			throw new WordsProgramException(lengthExpression, new WordsInvalidTypeException(value.type.toString(), ASTValue.ValueType.NUM.toString()));
		}

		int lengthValue = (int) Math.round(value.numValue);

		// Throw an appropriate WordsException if lengthValue is zero or negative
		if (lengthValue < 1) {
			throw new WordsProgramException(lengthExpression, new WordsFunctionArgsException("wait", "a positive number", String.format("%d", lengthValue)));
		}

		// Decompose into executable 1-frame waits
		LinkedList<WordsAction> list = new LinkedList<WordsAction>();
		for (int i = 0; i < lengthValue; i++)
			list.add(new WordsWait());

		return list;
	}
}