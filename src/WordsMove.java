
public class WordsMove extends WordsAction {
	
	public enum MoveDirection {
		UP,DOWN,LEFT,RIGHT
	}
	
	private MoveDirection direction;
	
	public WordsMove(WordsObject owner, MoveDirection direction) {
		this.direction = direction;
		this.owner = owner;
	}
	
	public void execute() {
		if (direction == MoveDirection.RIGHT) {
			owner.moveRight();
		}
		if (direction == MoveDirection.LEFT) {
			owner.moveLeft();
		}
		if (direction == MoveDirection.UP) {
			owner.moveUp();
		}
		if (direction == MoveDirection.DOWN) {
			owner.moveDown();
		}
	}
	
	public static MoveDirection getMoveDirection(String direction) throws WordsFunctionArgException {
		if (direction.equals("left")) {
			return MoveDirection.LEFT;
		}
		else if (direction.equals("right")) {
			return MoveDirection.RIGHT;
		}
		else if (direction.equals("up")) {
			return MoveDirection.UP;
		}
		else if (direction.equals("down")) {
			return MoveDirection.DOWN;
		}
		else throw new WordsFunctionArgException("move", direction, "one of [\"left\", \"right\", \"up\" \"down\"]");
	}
}
