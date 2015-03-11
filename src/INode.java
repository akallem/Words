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
	
	public void add(ArrayList<AST> nodes) {
		for (AST node : nodes)
			this.children.add(node);
	}
	
	public void dump(int level) {
		for (int i = 0; i < level; i++)
			System.out.printf("  ");
		
		System.out.println(this.type.toString());
		
		for (AST child : children)
			if (child != null)
				child.dump(level + 1);
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
