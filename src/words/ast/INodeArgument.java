package words.ast;

import java.util.HashSet;

import words.environment.*;
import words.exceptions.*;

public class INodeArgument extends INode {
	public INodeArgument(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false : "Requires an inherited object";
		return null;
	}
	
	@Override
	public ASTValue eval(Environment environment, Object inherited) throws WordsRuntimeException {
		@SuppressWarnings("unchecked")
		HashSet<String> parameters = (HashSet<String>) inherited;
		ASTValue argumentName = children.get(0).eval(environment);
		
		if (parameters.contains(argumentName.stringValue)) {
			ASTValue argumentValue = children.get(1).eval(environment);
			environment.addToCurrentScope(argumentName.stringValue, argumentValue.toWordsProperty());
		}
		
		return null;
	}
}