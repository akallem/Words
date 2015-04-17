package words.environment;
import java.util.ArrayList;
import java.util.LinkedList;

import words.ast.AST;

public class CustomAction extends Action {
	private AST statementList;
	private String name;
	private ArrayList<String> parameters;
	
	public CustomAction(String name, AST actions) {
		this.name = name;
		this.statementList = actions;
		this.parameters = parameters;
	}

	@Override
	public boolean isExecutable() {
		return false;
	}

	@Override
	protected void doExecute(WordsObject object, Environment environment) {}

	@Override
	protected LinkedList<Action> doExpand(WordsObject object, Environment environment) {
		// TODO
		
		/**
		 * Roughly, we need to put the parameters into scope, evaluate each non-queueing statement, make the queueing statements NOW and evaluate them in reverse order
		 */
		
		return null;
	}
}