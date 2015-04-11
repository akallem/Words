package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.ASTValue.ValueType;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class WordsSay extends WordsAction {
	private AST messageExpression;
	private String messageValue;

	public WordsSay(AST messageExpression) {
		this.messageExpression = messageExpression;
	}

	public WordsSay(String messageValue) {
		this.messageValue = messageValue;
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) throws WordsProgramException {
		if (messageExpression != null) {
			ASTValue value;
			try {
				value = messageExpression.eval(environment).tryCoerceTo(ASTValue.ValueType.STRING);
			} catch (WordsRuntimeException e) {
				throw new WordsProgramException(messageExpression, e);
			}

			if (value.type != ASTValue.ValueType.STRING) {
				throw new WordsProgramException(messageExpression, new WordsInvalidTypeException(value.type.toString(), ASTValue.ValueType.STRING.toString()));
			}

			messageValue = value.stringValue;
			messageExpression = null;					// Not necessary, but including for clarity since once the expression is evaluated, it is no longer needed
		}

		object.setMessage(messageValue);
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object, WordsEnvironment environment) { return null; }
}