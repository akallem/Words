package words.ast;

import java.util.HashSet;

import words.environment.WordsAction;
import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.environment.WordsWait;
import words.exceptions.WordsRuntimeException;

public class INodeWaitsPredicate extends INode {
	public INodeWaitsPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue className = children.get(0).eval(environment);
		ASTValue objectAlias = children.get(1).eval(environment);
		
		HashSet<WordsObject> objectsOfClass = environment.getObjectsByClass(className.stringValue);

		for (WordsObject object : objectsOfClass) {
			WordsAction lastAction = object.getLastAction();
			if (lastAction instanceof WordsWait) {
				return new ASTValue(true);
				//TODO: Aliasing
			}
		}
		return new ASTValue(false);
	}
}