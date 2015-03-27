
public class WordsMove extends WordsAction {
	
	private Direction direction;
	private int dist;
	
	//TODO: handle negative distance, anywhere direction
	public WordsMove(Direction direction, double dist) {
		this.direction = direction;
		this.dist = (int) Math.round(dist);
	}
	
//	public void execute() {
//		if (direction == Direction.RIGHT) {
//			owner.moveRight();
//		}
//		if (direction == Direction.LEFT) {
//			owner.moveLeft();
//		}
//		if (direction == Direction.UP) {
//			owner.moveUp();
//		}
//		if (direction == Direction.DOWN) {
//			owner.moveDown();
//		}
//	}
}
