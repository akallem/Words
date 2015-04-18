package words.ast;

import java.util.HashSet;

import words.environment.*;
import words.exceptions.*;

public abstract class INodeBasicActionPredicate extends INode {
	
	public INodeBasicActionPredicate(Object... children) {
		super(children);
	}
	
	protected HashSet<WordsObject> getObjectsToCheck(ASTValue subject, Environment environment) throws WordsClassNotFoundException {
			HashSet<WordsObject> objectsToCheck = new HashSet<WordsObject>();
			if (subject.type.equals(ASTValue.Type.STRING)) {
				objectsToCheck = environment.getObjectsByClass(subject.stringValue);
			} else if (subject.type.equals(ASTValue.Type.OBJ)) {
				objectsToCheck.add(subject.objValue);
			}
			return objectsToCheck;
	}
	
	public abstract ASTValue eval(Environment environment, Object inherited) throws WordsRuntimeException;
}
