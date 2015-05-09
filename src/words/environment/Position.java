package words.environment;
public class Position {
	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(double x, double y) {
		this.x = (int) Math.round(x);
		this.y = (int) Math.round(y);
	}
	
	public Position(Position p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	@Override 
	public String toString() {
		return Integer.toString(x) + " , " + Integer.toString(y);
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Position)) {
			return false;
		}
		Position otherPos = (Position) other;
		return this.x == otherPos.x && this.y == otherPos.y;
	}
	
	/**
	 * Returns whether this position is adjacent to another position by a given direction.
	 * The direction indicates which direction this position must be relative to the other position,
	 * i.e., DOWN would mean this position is below the other position. 
	 */
	public boolean isAdjacentOf(Position otherPos, Direction direction) {
		switch (direction) {
			case ANYWHERE:
				return (this.x == otherPos.x && Math.abs(this.y - otherPos.y) == 1) || ((this.y == otherPos.y && Math.abs(this.x - otherPos.x) == 1));
			case DOWN:
				return this.x == otherPos.x && this.y == otherPos.y - 1;
			case LEFT:
				return this.y == otherPos.y && this.x == otherPos.x - 1;
			case RIGHT:
				return this.y == otherPos.y && this.x == otherPos.x + 1;
			case UP:
				return this.x == otherPos.x && this.y == otherPos.y + 1;
			default:
				break;
		}
		
		return false;
	}

}
