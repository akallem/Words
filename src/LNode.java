/**
 * An abstract syntax tree leaf node.
 */
public class LNode extends AST {
	public boolean hasNoVal;
	public Direction direction;
	public double num;
	public String string;
	
	public LNode(ASTType type, int lineNo, Direction.Type d) {
		super(type, lineNo);
		this.direction = new Direction(d);
	}
	
	public LNode(ASTType type, int lineNo) {
		super(type, lineNo);
		this.hasNoVal = true;
	}
	
	public LNode(ASTType type, int lineNo, double n) {
		super(type, lineNo);
		this.num = n;
	}
	
	public LNode(ASTType type, int lineNo, String s) {
		super(type, lineNo);
		
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
			
			this.string = s;
		} else if (type == ASTType.IDENTIFIER) {
			this.string = s;
		} else if (type == ASTType.REFERENCE) {
			this.string = s.replace("'s", "");
		}
	}
	
	private String valueAsString() {
		if (type == ASTType.DIRECTION)
			return direction.toString();
		else if (type == ASTType.NOTHING || type == ASTType.NOW)
			return "true";
		else if (type == ASTType.NUM)
			return Double.toString(num);
		else if (type == ASTType.STRING || type == ASTType.IDENTIFIER || type == ASTType.REFERENCE)
			return string;
		
		return "";
	}
	
	public void dump(int level) {
		for (int i = 0; i < level; i++)
			System.err.printf("  ");
		
		System.err.println(this.lineNo + ": " + this.type.toString() + ": " + valueAsString());
	}
	
	@Override
	public String toString() {
		return "[" + type.toString() + ": " + valueAsString() + "]";
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) {
		switch (this.type) {
			case STRING:
			case REFERENCE:
			case IDENTIFIER:
				return new ASTValue(this.string);
			case NUM:
				return new ASTValue(this.num);
			case DIRECTION:
				return new ASTValue(this.direction);
			case NOTHING:
				return new ASTValue(ASTValue.ValueType.NOTHING);
			case NOW:
				return new ASTValue(ASTValue.ValueType.NOW);
			default:
				throw new AssertionError("LNode evaluated with unexpected AST node type " + this.type.toString());
		}
	}
}