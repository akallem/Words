package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.ASTValue.ValueType;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class WordsSay extends WordsAction {
	private AST message;

	public WordsSay(AST message) {
		this.message = message;
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) throws WordsProgramException {
		assert message != null : "Say message is null";

		ASTValue value;
		try {
			value = message.eval(environment).tryCoerceTo(ASTValue.ValueType.STRING);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(message, e);
		}

		if (value.type != ASTValue.ValueType.STRING) {
			throw new WordsProgramException(message, new WordsInvalidTypeException(ASTValue.ValueType.STRING.toString(), value.type.toString()));
		}

		object.setMessage(value.stringValue);
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object, WordsEnvironment environment) { return null; }
}