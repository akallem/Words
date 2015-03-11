public class AST {
	public enum Type { 
		// Leaf node types
		NUMBER,
		IDENTIFIER,
		OBJ,
		STRING,
		
		// Internal node types
		GETOBJ,
		GETPROP,
		MOVESPEC,
		CMD_MOVE,
		CMD_SAY,
		CMD_FUNC,
		CMD_CREATE_OBJ
	};
	
	public Type type;
	
	public AST(Type nodeType) {
		this.type = nodeType;
	}
}
