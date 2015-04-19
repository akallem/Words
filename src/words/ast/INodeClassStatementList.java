package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeClassStatementList extends INode {
	public INodeClassStatementList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		// TODO
		throw new AssertionError("Not yet implemented");
	}
	
	@Override
	public ASTValue eval(Environment environment, Object inherited) throws WordsRuntimeException {
		for (AST child : this.children) {
			child.eval(environment, inherited);
		}
		
		return null;
	}
}