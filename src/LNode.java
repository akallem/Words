public class LNode extends AST {
	public boolean isNothing;
	public Direction direction;
	public double num;
	public String string;
	public String identifier;
	
	public LNode(Type type, Direction.Type direction) {
		super(type);
		this.direction = new Direction(direction);
	}
	
	public LNode(Type type) {
		super(type);
		this.isNothing = true;
	}
	
	public LNode(Type type, double num) {
		super(type);
		this.num = num;
	}
	
	public LNode(Type type, String s) {
		super(type);
		
		if (type == Type.STRING)
			this.string = s.replace("\"", "");
		else if (type == Type.IDENTIFIER)
			this.identifier = s;
	}
	
	private String valueAsString() {
		if (type == Type.DIRECTION)
			return direction.toString();
		else if (type == Type.NUM)
			return Double.toString(num);
		else if (type == Type.STRING)
			return string;
		else if (type == Type.IDENTIFIER)
			return identifier;
		
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
