package words.ast;

import words.environment.*;

/**
 * A syntax tree leaf node for a direction.
 */
public class LNodeDirection extends LNode {
	public Direction direction;
	
	public LNodeDirection(Direction.Type d) {
		super();
		this.direction = new Direction(d);
	}
	
	@Override
	protected String valueAsString() {
		return direction.toString();
	}
	
	@Override
	public ASTValue eval(Environment environment) {
		return new ASTValue(this.direction);
	}
}