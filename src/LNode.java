/**
 * An abstract syntax tree leaf node.
 */
public class LNode extends AST {
	public boolean hasNoVal;
	public Direction d;
	public double n;
	public String s;
	
	public LNode(ASTType type, Direction.Type d) {
		super(type);
		this.d = new Direction(d);
	}
	
	public LNode(ASTType type) {
		super(type);
		this.hasNoVal = true;
	}
	
	public LNode(ASTType type, double n) {
		super(type);
		this.n = n;
	}
	
	public LNode(ASTType type, String s) {
		super(type);
		
		if (type == ASTType.STRING) {
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
		} else if (type == ASTType.IDENTIFIER) {
			this.s = s;
		} else if (type == ASTType.REFERENCE) {
			this.s = s.replace("'s", "");
		}
	}
	
	private String valueAsString() {
		if (type == ASTType.DIRECTION)
			return d.toString();
		else if (type == ASTType.NOTHING || type == ASTType.NOW)
			return "true";
		else if (type == ASTType.NUM)
			return Double.toString(n);
		else if (type == ASTType.STRING || type == ASTType.IDENTIFIER || type == ASTType.REFERENCE)
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

	@SuppressWarnings("incomplete-switch")
	@Override
	public Value eval(WordsEnvironment currentEnvironment) {
		switch (this.type) {
			case STRING:
			case REFERENCE:
			case IDENTIFIER:
				return new Value(this.s);
			case NUM:
				return new Value(this.n);
			case DIRECTION:
				return new Value(this.d);
			case NOTHING:
				return new Value(ValueType.NOTHING);
			case NOW:
				return new Value(ValueType.NOW);
		}
		return null;
	}
}
