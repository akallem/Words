import java.util.ArrayList;
import java.lang.StringBuilder;


/**
 * An abstract syntax tree internal node.
 */
public class INode extends AST {
	public ArrayList<AST> children;
	
	public INode(ASTType type, Object... children) {
		super(type);
		
		this.children = new ArrayList<AST>(children.length);
		
		for (int i = 0; i < children.length; i++) {
			if (children[i] == null)
				this.children.add(null);
			else
				this.children.add((AST) children[i]);
		}
	}
	
	/**
	 * Appends a given list of nodes to this node's list of children. 
	 * 
	 * @param nodes the list of nodes to be added
	 * @return this node
	 */
	public INode add(ArrayList<AST> nodes) {
		for (AST node : nodes)
			this.children.add(node);
		
		return this;
	}
	
	private void indent(int level) {
		for (int i = 0; i < level; i++)
			System.err.printf("  ");		
	}
	
	public void dump(int level) {
		indent(level);
		System.err.println(this.type.toString());
		
		if (children.size() > 0) {
			for (AST child : children) {
				if (child != null) {
					child.dump(level + 1);
				}
			}
		} else {
			indent(level + 1);
			System.err.println("  empty");
		}		
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("[" + type.toString() + ": ");
		
		for (AST child : children)
			if (child != null)
				s.append(child.toString());
		
		s.append("]");

		return s.toString();
	}

	@Override
	public Value eval(WordsEnvironment environment) {
		switch (this.type) {
			case STATEMENT_LIST:
				return evalStatementList(environment);
			case QUEUE_MOVE:
				return evalQueueMove(environment);
			case QUEUE_ACTION:
				return evalQueueCustomAction(environment);
			case EQUALS:
				return evalEquals(environment);
			case EXIT:
				return evalExit(environment);
		}
		return null;
	}

	private Value evalQueueCustomAction(WordsEnvironment environment) {
		//MAKE reference_list identifier identifier WITH parameter_list
		Value referenceObject = children.get(1).eval(environment);
		Value identifier = children.get(2).eval(environment);
		Value actionName = children.get(3).eval(environment);
		// Assumes that params returns a hashmap of strings to Values
		Value newParams = children.get(4).eval(environment);
		
		WordsClass objectClass = identifier.obj.getWordsClass();
		//WordsCustomAction customAction = objectClass.getCustomAction(actionName);
		//customAction.expandIntoBasicActions(params);
		return null;
	}
	
	private Value evalRetrieveProp(WordsEnvironment environment) {
		Value referenceObject = children.get(1).eval(environment);
		Value identifier = children.get(2).eval(environment);
		
		if (referenceObject.equals(ValueType.NOTHING)) {
			//if (params.containsKey(identifier.s)) {
				//return params.get(identifier);
			//}
		}
		
		return null;
		//return referenceObject.obj.getProperty(identifier.s);
	}

	private Value evalStatementList(WordsEnvironment environment) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).eval(environment);
		}
		return null;
	}

	private Value evalQueueMove(WordsEnvironment environment) {
		//MAKE reference_list identifier MOVE direction value_expression now
		Value referenceObject = children.get(1).eval(environment);
		Value identifier = children.get(2).eval(environment);
		Value direction = children.get(3).eval(environment);
		Value distance = children.get(4).eval(environment);
		AST doNow = children.get(5);
		
		WordsObject objectToMove;
		if (referenceObject.type.equals(ValueType.OBJ)){
			//objectToMove = referenceObject.obj.getProperty(identifier.s);
		} else {
			//objectToMove = environment.getObject(identifier);
		}
		
		//TODO: Distance = 0 should create a wait method
		/*WordsMove move = new WordsMove(direction.d, distance.n);
		if (doNow.equals(null)) {
			objectToMove.enqueueAction(move);
		} else {
			objectToMove.enqueueActionAtFront(move);
		}*/
		return null;
	}

	private Value evalEquals(WordsEnvironment environment) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value evalExit(WordsEnvironment environment) {
		// TODO Auto-generated method stub
		return null;
	}
}
