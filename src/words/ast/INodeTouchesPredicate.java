package words.ast;

import java.util.HashSet;

import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.exceptions.WordsAliasException;
import words.exceptions.WordsRuntimeException;

public class INodeTouchesPredicate extends INode {
	public INodeTouchesPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue subject1 = children.get(0).eval(environment);
		ASTValue objectAlias1 = children.get(1).eval(environment);
		ASTValue subject2 = children.get(2).eval(environment);
		ASTValue objectAlias2 = children.get(3).eval(environment);
		
		if (objectAlias1.type.equals(ASTValue.ValueType.STRING) 
				&& objectAlias2.type.equals(ASTValue.ValueType.STRING)
				&& objectAlias1.stringValue.equals(objectAlias2.stringValue)) {
			throw new WordsAliasException("Aliases may not be named the same.");
		}
		
		HashSet<WordsObject> objectsToCheck1 = new HashSet<WordsObject>();
		HashSet<WordsObject> objectsToCheck2 = new HashSet<WordsObject>();
		
		if (subject1.type.equals(ASTValue.ValueType.STRING)) {
			objectsToCheck1 = environment.getObjectsByClass(subject1.stringValue);
		} else if (subject1.type.equals(ASTValue.ValueType.OBJ)) {
			objectsToCheck1.add(subject1.objValue);
		}
		
		if (subject2.type.equals(ASTValue.ValueType.STRING)) {
			objectsToCheck2 = environment.getObjectsByClass(subject2.stringValue);
		} else if (subject2.type.equals(ASTValue.ValueType.OBJ)) {
			objectsToCheck2.add(subject2.objValue);
		}
		
		for (WordsObject object1: objectsToCheck1) {
			for (WordsObject object2: objectsToCheck2) {
				if (object1 != object2 && object1.getCurrentPosition().equals(object2.getCurrentPosition())) {
					return new ASTValue(true);
					// TODO: Modify the object table to contain an entry for these objects with their aliases,
					// run the statement list of the listener, and then pop the scope. 
				}
			}
		}
		return new ASTValue(false);
	}
}