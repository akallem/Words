/**
 * An abstract syntax tree leaf node.
 */
public class LNode extends AST {
	public boolean b;
	public Direction d;
	public double n;
	public String s;
	
	public LNode(Type type, Direction.Type d) {
		super(type);
		this.d = new Direction(d);
	}
	
	public LNode(Type type) {
		super(type);
		this.b = true;
	}
	
	public LNode(Type type, double n) {
		super(type);
		this.n = n;
	}
	
	public LNode(Type type, String s) {
		super(type);
		
		if (type == Type.STRING) {
			// Remove leading and trailing " characters
			// Do not do a full string replace, since some " characters may be escaped
			if (s.startsWith("\""))
					s = s.substring(1, s.length());
			
			if (s.endsWith("\""))
				s = s.substring(0, s.length() - 1);
			
			// Replace supported escape sequences
			s = s.replace("\\\n", "\n");
			s = s.replace("\\\\", "\\");
			s = s.replace("\\\"", "\"");
			
			this.s = s;
		} else if (type == Type.IDENTIFIER) {
			this.s = s;
		} else if (type == Type.REFERENCE) {
			this.s = s.replace("'s", "");
		}
	}
	
	private String valueAsString() {
		if (type == Type.DIRECTION)
			return d.toString();
		else if (type == Type.NOTHING || type == Type.NOW)
			return "true";
		else if (type == Type.NUM)
			return Double.toString(n);
		else if (type == Type.STRING || type == Type.IDENTIFIER || type == Type.REFERENCE)
			return s;
		
		return "";
	}
	
	public void dump(int level) {
		for (int i = 0; i < level; i++)
			System.err.printf("  ");
		
		System.err.println(this.type.toString() + ": " + valueAsString());
	}
	
	@Override
	public String toString() {
		return "[" + type.toString() + ": " + valueAsString() + "]";
	}
}