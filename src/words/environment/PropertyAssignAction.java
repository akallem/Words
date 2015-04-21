package words.environment;
import java.util.LinkedList;

import words.exceptions.*;
import words.ast.*;

public class PropertyAssignAction extends Action {
	private AST propertyAssignmentList;
	
	public PropertyAssignAction(Scope scope, AST propertyAssignmentList) {
		super(scope);
		this.propertyAssignmentList = propertyAssignmentList;
	}
	
	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, Environment environment) throws WordsProgramException {
		try {
			propertyAssignmentList.eval(environment, object);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(propertyAssignmentList, e);
		}
	}

	@Override
	protected LinkedList<Action> doExpand(WordsObject object, Environment environment) { return null; }
}
