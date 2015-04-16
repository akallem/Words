package words.ast;

import java.util.Collection;
import java.util.HashSet;

import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.exceptions.WordsRuntimeException;

public class INodeTouchesPredicate extends INode {
	public INodeTouchesPredicate(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue class1 = children.get(0).eval(environment);
		ASTValue objectAlias1 = children.get(1).eval(environment);
		ASTValue class2 = children.get(2).eval(environment);
		ASTValue objectAlias2 = children.get(3).eval(environment);
		
		HashSet<WordsObject> objectsOfClass1 = environment.getObjectsByClass(class1.stringValue);
		HashSet<WordsObject> objectsOfClass2 = environment.getObjectsByClass(class2.stringValue);
		
		for (WordsObject object1: objectsOfClass1) {
			for (WordsObject object2: objectsOfClass2) {
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