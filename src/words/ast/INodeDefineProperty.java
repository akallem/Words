package words.ast;

import words.environment.WordsEnvironment;
import words.exceptions.WordsRuntimeException;

public class INodeDefineProperty extends INode {
	
	public LNodeIdentifier id;
	public LNode literal;
	
	public INodeDefineProperty(Object... children) {
		super(children);
		id = (LNodeIdentifier) this.children.get(0);
		literal = (LNode) this.children.get(1);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		// TODO
		throw new AssertionError("Not yet implemented");
	}
}