public class LNode extends AST {
	public boolean isNothing;
	public Direction direction;
	public double n;
	public String s;
	
	public LNode(Type type, Direction.Type direction) {
		super(type);
		this.direction = new Direction(direction);
	}
	
	public LNode(Type type) {
		super(type);
		this.isNothing = true;
	}
	
	public LNode(Type type, double n) {
		super(type);
		this.n = n;
	}
	
	public LNode(Type type, String s) {
		super(type);
		
		if (type == Type.STRING)
			this.s = s.replace("\"", "");
		else if (type == Type.IDENTIFIER)
			this.s = s;
		else if (type == Type.REFERENCE)
			this.s = s.replace("'s", "");
	}
	
	private String valueAsString() {
		if (type == Type.DIRECTION)
			return direction.toString();
		else if (type == Type.NUM)
			return Double.toString(n);
		else if (type == Type.STRING)
			return s;
		else if (type == Type.IDENTIFIER)
			return s;
		else if (type == Type.REFERENCE)
			return s;
		
		return "";
	}
	
	public void dump(int level) {
		for (int i = 0; i < level; i++)
			System.out.printf("  ");
		
		System.out.println(this.type.toString() + ": " + valueAsString());
	}
	
	@Override
	public String toString() {
		return "[" + type.toString() + ": " + valueAsString() + "]";
	}
}
