package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeParameter extends INode {
	public INodeParameter(Object... children) {
		super(children);
	}
	
	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		assert false: "Requires an inherited object";
		return null;
	}

	@Override
	public ASTValue eval(Environment environment, Object inherited) throws WordsRuntimeException {
		CustomAction customAction = (CustomAction) inherited;
		ASTValue param = children.get(0).eval(environment);
		assert param.type == ASTValue.Type.STRING : "Parameter names should always be strings";
		customAction.addParameter(param.stringValue);
		return null;
	}
}