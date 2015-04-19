package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeExit extends INode {
	public INodeExit(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		System.exit(0);
		
		return null;
	}
}