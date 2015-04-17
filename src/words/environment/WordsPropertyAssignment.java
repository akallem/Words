package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class WordsPropertyAssignment extends WordsAction {
	private AST propertyAssignmentList;
	
	public WordsPropertyAssignment(AST propertyAssignmentList) {
		this.propertyAssignmentList = propertyAssignmentList;
	}
	
	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) throws WordsProgramException {
		try {
			propertyAssignmentList.eval(environment, object);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(propertyAssignmentList, e);
		}
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object, WordsEnvironment environment) { return null; }
}
