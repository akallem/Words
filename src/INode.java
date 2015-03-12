import java.util.ArrayList;

public class INode extends AST {
	public ArrayList<AST> children;
	
	public INode(Type type, Object... children) {
		super(type);
		
		this.children = new ArrayList<AST>(children.length);
		
		for (int i = 0; i < children.length; i++)
			if (children[i] == null)
				this.children.add(null);
			else
				this.children.add((AST) children[i]);
	}
	
	public INode add(ArrayList<AST> nodes) {
		for (AST node : nodes)
			this.children.add(node);
		
		return this;
	}
	
	private void indent(int level) {
		for (int i = 0; i < level; i++)
			System.out.printf("  ");		
	}
	
	public void dump(int level) {
		indent(level);
		System.out.println(this.type.toString());
		
		if (children.size() > 0) {
			for (AST child : children) {
				if (child != null) {
					child.dump(level + 1);
				}
			}
		} else {
			indent(level + 1);
			System.out.println("  empty");
		}		
	}
	
	@Override
	public String toString() {
		String s;
		String c = "";
			
		for (AST child : children)
			if (child != null)
				c = c +  child.toString();
		
		s = "[" + type.toString() + ": " + c + "]";
		
		return s;
	}
}
