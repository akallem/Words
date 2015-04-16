package words.ast;

import java.util.HashSet;

import words.environment.WordsAction;
import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.environment.WordsSay;
import words.exceptions.WordsRuntimeException;

public class INodeSaysPredicate extends INode {
	public INodeSaysPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue className = children.get(0).eval(environment);
		ASTValue objectAlias = children.get(1).eval(environment);
		ASTValue sayStatement = children.get(2).eval(environment);
		
		HashSet<WordsObject> objectsOfClass = environment.getObjectsByClass(className.stringValue);
		
		for (WordsObject object : objectsOfClass) {
			WordsAction lastAction = object.getLastAction();
			if (lastAction instanceof WordsSay && object.getCurrentMessage().equals(sayStatement.stringValue)) {
				return new ASTValue(true);
				//TODO: Aliasing
			}
		}
		return new ASTValue(false);
	}
}