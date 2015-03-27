import java.util.HashMap;

/**
 * An abstract syntax tree node, which may be either an internal node or leaf node.
 */
public abstract class AST {
	
	protected class Value {

		public double n;
		public String s;
		public WordsObject obj;
		public ValueType type;
		public Direction d;

		public Value(double num) {
			this.type = ValueType.NUM;
			this.n = num;
		}	

		public Value(String s) {
			this.type = ValueType.STRING;
			this.s = s;
		}
		
		public Value(WordsObject obj) {
			this.type = ValueType.OBJ;
			this.obj = obj;
		}
		
		public Value(Direction d) {
			this.type = ValueType.DIRECTION;
			this.d = d;
		}
		
		public Value(ValueType type) {
			this.type = type;
		}
		
	}

	protected enum ValueType {
		NUM,
		STRING,
		OBJ,
		NOTHING,
		DIRECTION,
		NOW
	}
	
	/**
	 * The type of the node, which determines how the node is evaluated.
	 */
	public enum ASTType { 
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
		
		RESET,
		EXIT,
		
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
		TOUCHES_PREDICATE,
		
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
		QUEUE_ASSIGN_PROPERTY_LIST,
		
		PARAMETER,
		SUBJECT,
		ALIAS,
		QUEUE_ASSIGN_PROPERTY,
	};
	
	public ASTType type;
	
	public AST(ASTType nodeType) {
		this.type = nodeType;
	}
	
	/**
	 * Debugging method to dump this node and all its children, if any, to standard error for inspection.
	 * 
	 * @param level the indentation level
	 */
	abstract public void dump(int level);
	
	public Value eval(WordsEnvironment currentEnvironment,
			HashMap<String, Value> params) {
		// TODO Auto-generated method stub
		return null;
	}
}
