package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.ASTValue.Type;
import words.ast.LNodeNum;
import words.exceptions.FunctionArgsException;
import words.exceptions.InvalidTypeException;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class WaitAction extends Action {
	private AST lengthExpression;

	public WaitAction() {
		this.lengthExpression = null;
	}

	public WaitAction(AST lengthExpression) {
		this.lengthExpression = lengthExpression;
	}

	@Override
	public boolean isExecutable() {
		return (lengthExpression == null);
	}

	@Override
	public void doExecute(WordsObject object, Environment environment) throws WordsProgramException {
		// Nothing to do, this will simply cause the object to wait 1 frame
	}

	@Override
	public LinkedList<Action> doExpand(WordsObject object, Environment environment) throws WordsProgramException {
		assert lengthExpression != null : "Length expression for Wait is null";

		ASTValue value;
		try {
			value = lengthExpression.eval(environment).tryCoerceTo(ASTValue.Type.NUM);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(lengthExpression, e);
		}

		if (value.type != ASTValue.Type.NUM) {
			throw new WordsProgramException(lengthExpression, new InvalidTypeException(value.type.toString(), ASTValue.Type.NUM.toString()));
		}

		int lengthValue = (int) Math.round(value.numValue);

		// Throw an appropriate WordsException if lengthValue is zero or negative
		if (lengthValue < 1) {
			throw new WordsProgramException(lengthExpression, new FunctionArgsException("wait", "a positive number", String.format("%d", lengthValue)));
		}

		// Decompose into executable 1-frame waits
		LinkedList<Action> list = new LinkedList<Action>();
		for (int i = 0; i < lengthValue; i++)
			list.add(new WaitAction());

		return list;
	}
}