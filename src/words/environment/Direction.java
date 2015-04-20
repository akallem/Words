package words.environment;

import java.util.Random;

/**
 * An orthogonal direction.
 */
public enum Direction {
		ANYWHERE,
		DOWN,
		LEFT,
		RIGHT,
		UP;
	
	public static Direction[] explicit = {DOWN, LEFT, RIGHT, UP};

	/**
	 * Returns a random explicit direction, i.e., not ANYWHERE.
	 */
	public static Direction getRandom() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(explicit.length);
		return Direction.explicit[randomInt];		
	}
	
	/**
	 * Returns the opposite of a given direction.  Returns null if called with ANYWHERE.
	 */
	public static Direction getOpposite(Direction input) {
		switch(input) {
			case DOWN:
				return Direction.UP;
			case LEFT:
				return Direction.RIGHT;
			case RIGHT:
				return Direction.LEFT;
			case UP:
				return Direction.DOWN;
			default:
				break;
		} 
		
		return null;
	}
}
