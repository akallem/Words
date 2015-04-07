/**
 * An abstract syntax tree leaf node.
 */
public abstract class LNode extends AST {
	public LNode() {
		super();
	}
	
	/**
	 * Return this LNode's value in string format.
	 */
	protected abstract String valueAsString();
	
	@Override
	public void dump(int level) {
		for (int i = 0; i < level; i++)
			System.err.printf("  ");
		
		System.err.println(this.lineNo + ": " + this.type.toString() + ": " + valueAsString());
	}
	
	@Override
	public String toString() {
		return "[" + type.toString() + ": " + valueAsString() + "]";
	}
}