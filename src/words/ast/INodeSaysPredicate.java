package words.ast;

import java.util.HashSet;

import words.environment.*;
import words.exceptions.*;

public class INodeSaysPredicate extends INodeBasicActionPredicate {
	public INodeSaysPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false : "Cannot eval INodeSaysPredicate without inherited Statement List";
		return null;
	}

	@Override
	public ASTValue eval(Environment environment, Object inheritedStmts) throws WordsRuntimeException {
		ASTValue subject = children.get(0).eval(environment);
		ASTValue objectAlias = children.get(1).eval(environment);
		ASTValue sayStatement = children.get(2).eval(environment);
		
		INodeStatementList stmtList = (INodeStatementList) inheritedStmts;
		
		HashSet<WordsObject> objectsToCheck = getObjectsToCheck(subject, environment);
		ASTValue returnVal = new ASTValue(false);
		
		for (WordsObject object : objectsToCheck) {
			Action lastAction = object.getLastAction();
			if (lastAction instanceof SayAction && object.getCurrentMessage().equals(sayStatement.stringValue)) {
				returnVal.booleanValue = true;
				environment.enterNewLocalScope();
				if (objectAlias.type.equals(ASTValue.Type.STRING)) {
					environment.addVariableToCurrentNameScope(objectAlias.stringValue, new Property(object));
				}
				stmtList.eval(environment);
				environment.exitLocalScope();
			}
		}
		return returnVal;
	}
}