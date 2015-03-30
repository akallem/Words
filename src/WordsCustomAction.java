import java.util.ArrayList;
import java.util.HashMap;


public class WordsCustomAction extends WordsAction {
	
	private AST statementList;
	private String name;
	private ArrayList<String> parameters;
	private WordsEnvironment environment;
	
	public WordsCustomAction(String name, WordsEnvironment environment, AST actions) {
		this.name = name;
		this.statementList = actions;
		this.parameters = parameters;
		this.environment = environment;
	}
	
	public void expandIntoBasicActions() {
		//TODO: check that parameters match
		statementList.eval(environment);
	}

}
