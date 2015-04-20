package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeIdentifierList extends INode {
	public INodeIdentifierList(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment, Object customActionObj) throws WordsRuntimeException {
		CustomAction customAction = (CustomAction) customActionObj;
		for (AST child : this.children) {
			ASTValue param = child.eval(environment);
			assert param.type == ASTValue.Type.STRING : "Parameter names should always be strings";
			customAction.addParameter(param.stringValue);
		}
		return null;
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false: "Requires an inherited object";
		return null;
	}
}