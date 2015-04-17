package words.ast;

import java.util.HashSet;

import words.environment.*;
import words.exceptions.*;

public class INodeTouchesPredicate extends INodeBasicActionPredicate {
	public INodeTouchesPredicate(Object... children) {
		super(children);
	}
	
	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false : "Cannot eval INodeTouchesPredicate without inherited Statement List";
		return null;
	}

	@Override
	public ASTValue eval(Environment environment, Object inheritedStmts) throws WordsRuntimeException {
		ASTValue subject1 = children.get(0).eval(environment);
		ASTValue objectAlias1 = children.get(1).eval(environment);
		ASTValue subject2 = children.get(2).eval(environment);
		ASTValue objectAlias2 = children.get(3).eval(environment);
		
		INodeStatementList stmtList = (INodeStatementList) inheritedStmts;
		
		if (objectAlias1.type.equals(ASTValue.Type.STRING) 
				&& objectAlias2.type.equals(ASTValue.Type.STRING)
				&& objectAlias1.stringValue.equals(objectAlias2.stringValue)) {
			throw new AliasException("Aliases may not be named the same.");
		}
		
		HashSet<WordsObject> objectsToCheck1 = new HashSet<WordsObject>();
		HashSet<WordsObject> objectsToCheck2 = new HashSet<WordsObject>();
		ASTValue returnVal = new ASTValue(false);
		
		if (subject1.type.equals(ASTValue.Type.STRING)) {
			objectsToCheck1 = environment.getObjectsByClass(subject1.stringValue);
		} else if (subject1.type.equals(ASTValue.Type.OBJ)) {
			objectsToCheck1.add(subject1.objValue);
		}
		
		if (subject2.type.equals(ASTValue.Type.STRING)) {
			objectsToCheck2 = environment.getObjectsByClass(subject2.stringValue);
		} else if (subject2.type.equals(ASTValue.Type.OBJ)) {
			objectsToCheck2.add(subject2.objValue);
		}
		
		for (WordsObject object1: objectsToCheck1) {
			for (WordsObject object2: objectsToCheck2) {
				if (object1 != object2 && object1.getCurrentPosition().equals(object2.getCurrentPosition())) {
					returnVal.booleanValue = true;
					environment.enterNewLocalScope();
					if (objectAlias1.type.equals(ASTValue.Type.STRING)) {
						environment.addObjectToCurrentNameScope(objectAlias1.stringValue, object1);
					}
					if (objectAlias2.type.equals(ASTValue.Type.STRING)) {
						environment.addObjectToCurrentNameScope(objectAlias2.stringValue, object2);
					}
					stmtList.eval(environment);
					environment.exitLocalScope();
				}
			}
		}
		return returnVal;
	}
}