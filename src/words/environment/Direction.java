package words.environment;

import java.util.Random;

/**
 * An orthogonal direction.
 */
public class Direction {
	/**
	 * One of the four orthogonal directions or an indication that any direction is permitted.
	 */
	public static enum Type {
		ANYWHERE,
		DOWN,
		LEFT,
		RIGHT,
		UP
	}
	
	public static Type[] explicit = {Type.DOWN, Type.LEFT, Type.RIGHT, Type.UP};

	/**
	 * Returns a random explicit direction, i.e., not ANYWHERE.
	 */
	public static Type getRandom() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(explicit.length);
		return Direction.explicit[randomInt];		
	}
	
	/**
	 * Returns the opposite of a given direction.  Returns null if called with ANYWHERE.
	 */
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
			default:
				break;
		} 
		
		return null;
	}
}
