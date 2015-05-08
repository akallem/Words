package words.ast;

import java.util.HashSet;

import words.environment.*;
import words.exceptions.*;

public class INodeAdjacencyPredicate extends INodeBasicActionPredicate {
	public INodeAdjacencyPredicate(Object... children) {
		super(children);
	}
	
	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false : "Cannot eval INodeAdjacentPredicate without inherited Statement List";
		return null;
	}

	@Override
	public ASTValue eval(Environment environment, Object inheritedStmts) throws WordsRuntimeException {
		ASTValue subject1 = children.get(0).eval(environment);
		ASTValue objectAlias1 = children.get(1).eval(environment);
		ASTValue direction = children.get(2).eval(environment);
		ASTValue subject2 = children.get(3).eval(environment);
		ASTValue objectAlias2 = children.get(4).eval(environment);
		
		INodeStatementList stmtList = (INodeStatementList) inheritedStmts;
		
		if (objectAlias1.type.equals(ASTValue.Type.STRING) 
				&& objectAlias2.type.equals(ASTValue.Type.STRING)
				&& objectAlias1.stringValue.equals(objectAlias2.stringValue)) {
			throw new AliasException();
		}
		
		HashSet<WordsObject> objectsToCheck1 = getObjectsToCheck(subject1, environment);
		HashSet<WordsObject> objectsToCheck2 = getObjectsToCheck(subject2, environment);
		ASTValue returnVal = new ASTValue(false);
		
		for (WordsObject object1: objectsToCheck1) {
			for (WordsObject object2: objectsToCheck2) {
				boolean objectsMoved = object1.movedInLastTurn() || object2.movedInLastTurn();
				if (object1 != object2 && objectsMoved && object1.getCurrentPosition().isAdjacentOf(object2.getCurrentPosition(), direction.directionValue)) {
					returnVal.booleanValue = true;
					environment.pushNewScope();
					if (objectAlias1.type.equals(ASTValue.Type.STRING)) {
						environment.addToCurrentScope(objectAlias1.stringValue, new Property(object1));
					}
					if (objectAlias2.type.equals(ASTValue.Type.STRING)) {
						environment.addToCurrentScope(objectAlias2.stringValue, new Property(object2));
					}
					stmtList.eval(environment);
					environment.popScope();
				}
			}
		}
		return returnVal;
	}
}