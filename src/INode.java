import java.util.ArrayList;
import java.util.HashMap;
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
	public Value eval(WordsEnvironment currentEnvironment, HashMap<String, Value> params) {
		switch (this.type) {
			case STATEMENT_LIST:
				return evalStatementList(currentEnvironment, null);
			case QUEUE_MOVE:
				return evalQueueMove(currentEnvironment, null);
			case QUEUE_ACTION:
				return evalQueueCustomAction(currentEnvironment, null);
			case EQUALS:
				return evalEquals(currentEnvironment);
			case EXIT:
				return evalExit(currentEnvironment);
		}
		return null;
	}

	private Value evalQueueCustomAction(WordsEnvironment currentEnvironment, HashMap<String, Value> params) {
		//MAKE reference_list identifier identifier WITH parameter_list
		Value referenceObject = children.get(1).eval(currentEnvironment, params);
		Value identifier = children.get(2).eval(currentEnvironment, params);
		Value actionName = children.get(3).eval(currentEnvironment, params);
		// Assumes that params returns a hashmap of strings to Values
		Value newParams = children.get(4).eval(currentEnvironment, params);
		
		WordsClass objectClass = identifier.obj.getWordsClass();
		WordsCustomAction customAction = objectClass.getCustomAction(actionName);
		customAction.expandIntoBasicActions(params);
	}
	
	private Value evalRetrieveProp(WordsEnvironment currentEnvironment, HashMap<String, Value> params) {
		Value referenceObject = children.get(1).eval(currentEnvironment, params);
		Value identifier = children.get(2).eval(currentEnvironment, params);
		
		if (referenceObject.equals(ValueType.NOTHING)) {
			if (params.containsKey(identifier.s)) {
				return params.get(identifier);
			}
		}
		return referenceObject.obj.getProperty(identifier.s);
	}

	private Value evalStatementList(WordsEnvironment currentEnvironment, HashMap<String, Value> params) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).eval(currentEnvironment, params);
		}
		return null;
	}

	private Value evalQueueMove(WordsEnvironment currentEnvironment, HashMap<String, Value> params) {
		//MAKE reference_list identifier MOVE direction value_expression now
		Value referenceObject = children.get(1).eval(currentEnvironment, params);
		Value identifier = children.get(2).eval(currentEnvironment, params);
		Value direction = children.get(3).eval(currentEnvironment, params);
		Value distance = children.get(4).eval(currentEnvironment, params);
		AST doNow = children.get(5);
		
		WordsObject objectToMove;
		if (referenceObject.type.equals(ValueType.OBJ)){
			objectToMove = referenceObject.obj.getProperty(identifier.s);
		} else {
			objectToMove = currentEnvironment.getObject(identifier);
		}
		
		//TODO: Distance = 0 should create a wait method
		WordsMove move = new WordsMove(direction.d, distance.n);
		if (doNow.equals(null)) {
			objectToMove.enqueueAction(move);
		} else {
			objectToMove.enqueueActionAtFront(move);
		}
		return null;
	}

	private Value evalEquals(WordsEnvironment currentEnvironment) {
		// TODO Auto-generated method stub
		return null;
	}

	private Value evalExit(WordsEnvironment currentEnvironment) {
		// TODO Auto-generated method stub
		return null;
	}
}
