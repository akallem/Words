import java.util.ArrayList;

public class INode extends AST {
	public ArrayList<AST> children;
	
	public INode(Type type, Object... children) {
		super(type);
		
		this.children = new ArrayList<AST>(children.length);
		
		for (int i = 0; i < children.length; i++)
			this.children.add((AST) children[i]);
	}
	
	@Override
	public String toString() {
		String s;
		String c = "";
			
		for (AST child : children)
			c = c + "(" + child.toString() + ")";
		
		s = "[" + type.toString() + ": " + c + "]";
		
		return s;
	}
}
