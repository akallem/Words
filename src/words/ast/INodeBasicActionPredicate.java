package words.ast;

import words.environment.*;
import words.exceptions.*;

public abstract class INodeBasicActionPredicate extends INode {
	
	public INodeBasicActionPredicate(Object... children) {
		super(children);
	}
	
	public abstract ASTValue eval(Environment environment, Object inherited) throws WordsRuntimeException;
}
