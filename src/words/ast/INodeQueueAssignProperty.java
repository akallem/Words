package words.ast;

import words.environment.WordsEnvironment;
import words.environment.WordsObject;
import words.exceptions.WordsRuntimeException;

public class INodeQueueAssignProperty extends INode {
	public INodeQueueAssignProperty(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		assert false : "Cannot eval INodeQueueAssignPropertyList without inherited WordsObject";
		return null;
	}
	
	@Override
	public ASTValue eval(WordsEnvironment environment, Object inherited) throws WordsRuntimeException {
		WordsObject object = (WordsObject) inherited;
		
		String propertyName = children.get(0).eval(environment).stringValue;
		ASTValue propertyASTValue = children.get(1).eval(environment);
		object.setProperty(propertyName, propertyASTValue.toWordsProperty());
		
		return null;
	}
}