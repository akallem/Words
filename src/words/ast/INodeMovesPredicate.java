package words.ast;

import java.util.HashSet;

import words.environment.WordsAction;
import words.environment.WordsEnvironment;
import words.environment.WordsMove;
import words.environment.WordsObject;
import words.exceptions.WordsRuntimeException;

public class INodeMovesPredicate extends INode {
	public INodeMovesPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue className = children.get(0).eval(environment);
		ASTValue objectAlias = children.get(1).eval(environment);
		ASTValue moveDirection = children.get(2) == null ? null : children.get(2).eval(environment);
		
		HashSet<WordsObject> objectsOfClass = environment.getObjectsByClass(className.stringValue);

		for (WordsObject object : objectsOfClass) {
			WordsAction lastAction = object.getLastAction();
			if (lastAction instanceof WordsMove) {
				WordsMove lastMove = (WordsMove) lastAction;
				if (moveDirection == null || moveDirection.directionValue.equals(lastMove.getDirection())) {
					return new ASTValue(true);
					//TODO: Aliasing
				}
			}
		}
		return new ASTValue(false);
	}
}