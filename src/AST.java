public abstract class AST {
	public enum Type { 
		// Leaf node types
		DIRECTION,
		NOTHING,
		NUM,
		IDENTIFIER,
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
		QUEUE_MOVE_NOW,
		QUEUE_SAY,
		QUEUE_SAY_NOW,
		QUEUE_WAIT,
		QUEUE_WAIT_NOW,
		QUEUE_STOP,
		QUEUE_ASSIGN,
		QUEUE_ASSIGN_NOW,
		QUEUE_ACTION,
		QUEUE_ACTION_NOW,
		
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
		
		IDENTIFIER_LIST,
		POSITION,
		REFERENCE,
		PARAMETER,
		PARAMETER_LIST
	};
	
	public Type type;
	
	public AST(Type nodeType) {
		this.type = nodeType;
	}
	
	abstract public void dump(int level);
}
