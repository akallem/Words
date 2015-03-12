public abstract class AST {
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
	
	abstract public void dump(int level);
}
