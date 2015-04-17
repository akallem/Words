package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.ASTValue.Type;
import words.exceptions.InvalidTypeException;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class SayAction extends Action {
	private AST message;

	public SayAction(AST message) {
		this.message = message;
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, Environment environment) throws WordsProgramException {
		assert message != null : "Say message is null";

		ASTValue value;
		try {
			value = message.eval(environment).tryCoerceTo(ASTValue.Type.STRING);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(message, e);
		}

		if (value.type != ASTValue.Type.STRING) {
			throw new WordsProgramException(message, new InvalidTypeException(ASTValue.Type.STRING.toString(), value.type.toString()));
		}

		object.setMessage(value.stringValue);
	}

	@Override
	protected LinkedList<Action> doExpand(WordsObject object, Environment environment) { return null; }
}