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
		assert false : "Cannot eval INodeWaitsPredicate without inherited Statement List";
		return null;
	}

	@Override
	public ASTValue eval(WordsEnvironment environment, Object inheritedStmts) throws WordsRuntimeException {
		ASTValue subject = children.get(0).eval(environment);
		ASTValue objectAlias = children.get(1).eval(environment);
		
		INodeStatementList stmtList = (INodeStatementList) inheritedStmts;
		
		HashSet<WordsObject> objectsToCheck = new HashSet<WordsObject>();
		ASTValue returnVal = new ASTValue(false);
		
		// If subject is a string, then we are looking at a class name
		if (subject.type.equals(ASTValue.ValueType.STRING)) {
			objectsToCheck = environment.getObjectsByClass(subject.stringValue);
		} else if (subject.type.equals(ASTValue.ValueType.OBJ)) {
			objectsToCheck.add(subject.objValue);
		}

		for (WordsObject object : objectsToCheck) {
			WordsAction lastAction = object.getLastAction();
			if (lastAction instanceof WordsWait) {
				returnVal.booleanValue = true;
				environment.enterNewLocalScope();
				if (objectAlias.type.equals(ASTValue.ValueType.STRING)) {
					environment.addObjectToCurrentNameScope(objectAlias.stringValue, object);
				}
				stmtList.eval(environment);
				environment.exitLocalScope();
			}
		}
		return returnVal;
	}
}