package words.ast;

import words.environment.*;

/**
 * A syntax tree leaf node for a reference.
 */
public class LNodeReference extends LNode {
	public String reference;
	
	public LNodeReference(String reference) {
		super();
		this.reference = reference.replace("'s", "");
	}
	
	@Override
	protected String valueAsString() {
		return reference;
	}
	
	@Override
	public ASTValue eval(Environment environment) {
		return new ASTValue(this.reference);
	}
}