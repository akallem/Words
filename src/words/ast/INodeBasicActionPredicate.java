package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public abstract class INodeBasicActionPredicate extends INode {
	
	public INodeBasicActionPredicate(Object... children) {
		super(children);
	}
	
	public abstract ASTValue eval(WordsEnvironment environment, Object inherited) throws WordsRuntimeException;
}
