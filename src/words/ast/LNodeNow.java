package words.ast;

import words.environment.*;

/**
 * A syntax tree leaf node for the keyword 'now'.
 */
public class LNodeNow extends LNode {	
	public LNodeNow() {
		super();
	}
	
	@Override
	protected String valueAsString() {
		return "now";
	}
	
	@Override
	public ASTValue eval(Environment environment) {
		return new ASTValue(ASTValue.Type.NOW);
	}
}