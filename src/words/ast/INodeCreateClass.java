package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeCreateClass extends INode {
	public INodeCreateClass(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue className  = this.children.get(0).eval(environment);
		ASTValue parentClassName = this.children.get(1).eval(environment);
		WordsClass wordsClass = environment.createClass(className.stringValue, parentClassName.stringValue);
		
		INodeClassStatementList statementList = (INodeClassStatementList) this.children.get(2);
		if (statementList != null) {
			statementList.eval(environment, wordsClass);
		}
		
		return null;
	}
}