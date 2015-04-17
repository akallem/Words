package words.environment;

/**
 * An orthogonal direction, possibly random.
 */
public class Direction {
	/**
	 * One of the four orthogonal directions or an indication that any direction is permitted.
	 */
	public enum Type {
		ANYWHERE,
		DOWN,
		LEFT,
		RIGHT,
		UP
	}
	
	public Type type;

	public static Type[] explicit = {Type.DOWN, Type.LEFT, Type.RIGHT, Type.UP};
	
	public Direction(Type type) {
		this.type = type;
	}

	public static Type getOpposite(Type input) {
		switch(input) {
			case DOWN:
				return Type.UP;
			case LEFT:
				return Type.RIGHT;
			case RIGHT:
				return Type.LEFT;
			case UP:
				return Type.DOWN;
		} 
		return null;
	}
	
	@Override
	public String toString() {
		return this.type.toString();
	}
}
