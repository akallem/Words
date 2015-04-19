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
}
