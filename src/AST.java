public class AST {
	public enum Type { 
		// Leaf node types
		NUM,
		IDENTIFIER,
		OBJ,
		STRING,
		
		// Internal node types
		// TBD
	};
	
	public Type type;
	
	public AST(Type nodeType) {
		this.type = nodeType;
	}
}
