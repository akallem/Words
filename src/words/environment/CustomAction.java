package words.environment;
import java.util.LinkedList;

import words.exceptions.*;
import words.ast.*;

public class CustomAction extends Action {
	private CustomActionDefinition actionDefinition;
	private AST arguments;
	
	public CustomAction(Scope scope, CustomActionDefinition actionDefinition, AST arguments) {
		super(scope);
		this.actionDefinition = actionDefinition;
		this.arguments = arguments;
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
		actionDefinition.execute(environment, object, arguments);
		return object.finishExpandingCustomAction();
	}
}