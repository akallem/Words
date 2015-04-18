package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeAssign extends INode {
	public INodeAssign(Object... children) {
		super(children);
	}

	/**
	 * Always returns null. Throws WordsRuntimeException for called methods, including WordsObjectNotFoundException.
	 * Side effect of assigning a property to an object.
	 */
	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		WordsObject obj = children.get(0).eval(environment).objValue;
		assert obj != null : "Obj was null when it shouldn't have been.";
		
		String propertyName = children.get(1).eval(environment).stringValue;
		ASTValue propertyASTValue = children.get(2).eval(environment);	

		obj.setProperty(propertyName, propertyASTValue.toWordsProperty());
		
		return null;
	}
}