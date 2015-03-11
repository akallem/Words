public class LNode extends AST {
	public double num;
	public String string;
	public String identifier;
	
	public LNode(Type type, double num) {
		super(type);
		this.num = num;
	}
	
	public LNode(Type type, String s) {
		super(type);
		
		if (type == Type.STRING)
			this.string = s;
		else if (type == Type.IDENTIFIER)
			this.identifier = s;
	}
	
	@Override
	public String toString() {
		String s;
		String c = "";
		
		if (type == Type.NUM)
			c = Double.toString(num);
		else if (type == Type.STRING)
			c = string;
		else if (type == Type.IDENTIFIER)
			c = identifier;
				
		s = "[" + type.toString() + ": " + c + "]";
		
		return s;
	}
}
