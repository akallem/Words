package words.environment;
import java.util.HashSet;
import java.util.LinkedList;

import words.exceptions.*;
import words.ast.*;

public class CustomAction extends Action {
	private AST statementList;
	private HashSet<String> parameters;
	
	public CustomAction(AST actions) {
		this.statementList = actions;
		this.parameters = new HashSet<String>();
	}

	@Override
	public boolean isExecutable() {
		return false;
	}

	@Override
	protected void doExecute(WordsObject object, Environment environment) {}

	@Override
	protected LinkedList<Action> doExpand(WordsObject object, Environment environment) throws WordsProgramException {
		object.startExpandingCustomAction();
		environment.enterNewLocalScope();
		String[] pronouns = {"them", "it", "him", "her", "his", "its", "their"};
		for (String pronoun : pronouns) {
			environment.addVariableToCurrentNameScope(pronoun, new Property(object));
		}
		try {
			statementList.eval(environment);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(statementList, e);
		}
		environment.exitLocalScope();
		return object.finishExpandingCustomAction();
	}
	
	public void addParameter(String paramName) {
		parameters.add(paramName);
	}
}