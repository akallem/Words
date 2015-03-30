import java.util.ArrayList;
import java.util.LinkedList;

public class WordsCustomAction extends WordsAction {
	private AST statementList;
	private String name;
	private ArrayList<String> parameters;
	
	public WordsCustomAction(String name, AST actions) {
		this.name = name;
		this.statementList = actions;
		this.parameters = parameters;
	}

	@Override
	public boolean isExecutable() {
		return false;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) {}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object) {
		// TODO
		
		/**
		 * Roughly, we need to put the parameters into scope, evaluate each non-queueing statement, make the queueing statements NOW and evaluate them in reverse order
		 */
		
		return null;
	}
}