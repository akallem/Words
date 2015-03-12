/**
 * An abstract syntax tree node, which may be either an internal node or leaf node.
 */
public abstract class AST {
	/**
	 * The type of the node, which determines how the node is evaluated.
	 */
	public enum Type { 
		// Leaf node types
		DIRECTION,
		NOTHING,
		NOW,
		NUM,
		IDENTIFIER,
		REFERENCE,
		STRING,
		
		// Internal node types
		STATEMENT_LIST,
		
		CREATE_CLASS,
		CLASS_STATEMENT_LIST,
		DEFINE_PROPERTY,
		DEFINE_ACTION,
		CREATE_OBJ,
		REMOVE,
		ASSIGN,
		
		REPEAT,
		WHILE,
		IF,
		
		LISTENER_PERM,
		LISTENER_TEMP,
		
		QUEUE_MOVE,
		QUEUE_SAY,
		QUEUE_WAIT,
		QUEUE_STOP,
		QUEUE_ASSIGN,
		QUEUE_ACTION,
		
		MOVES_PREDICATE,
		SAYS_PREDICATE,
		WAITS_PREDICATE,
		
		NOT,
		AND,
		OR,
		
		EQUALS,
		LESS,
		GREATER,
		LEQ,
		GEQ,
		
		NEGATE,
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		EXPONENTIATE,
		
		POSITION,
		RETRIEVE_PROPERTY,
		
		REFERENCE_LIST,
		IDENTIFIER_LIST,
		PARAMETER_LIST,
		QUEUE_ASSIGNMENT_LIST,
		
		PARAMETER,
		QUEUE_ASSIGNMENT,
	};
	
	public Type type;
	
	public AST(Type nodeType) {
		this.type = nodeType;
	}
	
	/**
	 * Debugging method to dump this node and all its children, if any, to standard error for inspection.
	 * 
	 * @param level the indentation level
	 */
	abstract public void dump(int level);
}
