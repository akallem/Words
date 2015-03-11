
public class Direction {
	public enum Type {
		ANYWHERE,
		DOWN,
		LEFT,
		RIGHT,
		UP
	}
	
	public Type type;
	
	public Direction(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type.toString();
	}
}
