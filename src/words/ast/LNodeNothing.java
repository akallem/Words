package words.ast;

import words.environment.*;

/**
 * A syntax tree leaf node for the keyword 'nothing'.
 */
public class LNodeNothing extends LNode {	
	public LNodeNothing() {
		super();
	}
	
	@Override
	protected String valueAsString() {
		return "nothing";
	}
	
	@Override
	public ASTValue eval(Environment environment) {
		return new ASTValue(ASTValue.Type.NOTHING);
	}
}