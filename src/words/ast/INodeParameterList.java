package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeParameterList extends INode {
	public INodeParameterList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false : "Requires an inherited object";
		return null;
	}
	
	@Override
	public ASTValue eval(Environment environment, Object inherited) throws WordsRuntimeException {
		for (AST child : children) {
			child.eval(environment, inherited);
		}
		
		return null;
	}
}