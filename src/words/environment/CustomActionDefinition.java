package words.environment;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import words.exceptions.*;
import words.ast.*;

public class CustomActionDefinition {
	private AST statementList;
	private HashSet<String> parameters;
	
	public CustomActionDefinition(AST actions) {
		this.statementList = actions;
		this.parameters = new HashSet<String>();
	}
	
	public void addParameter(String paramName) {
		parameters.add(paramName);
	}
	
	public void execute(Environment environment, WordsObject object, HashMap<String, AST> arguments) throws WordsProgramException {
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
	}
}