package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class PropertyAssignAction extends Action {
	private AST propertyAssignmentList;
	
	public PropertyAssignAction(AST propertyAssignmentList) {
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
