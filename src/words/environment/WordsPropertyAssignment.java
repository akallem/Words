package words.environment;
import java.util.LinkedList;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.INode;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class WordsPropertyAssignment extends WordsAction {
	private INode propertyAssignmentList;
	
	public WordsPropertyAssignment(INode propertyAssignmentList) {
		this.propertyAssignmentList = propertyAssignmentList;
	}
	
	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) throws WordsProgramException {
		for (AST p : propertyAssignmentList.children) {
			INode propertyAssign = (INode) p;

			try {
				String propertyName = propertyAssign.children.get(0).eval(environment).stringValue;
				ASTValue propertyASTValue = propertyAssign.children.get(1).eval(environment);
				object.setProperty(propertyName, propertyASTValue.toWordsProperty());
			} catch (WordsRuntimeException e) {
				throw new WordsProgramException(propertyAssign, e);
			}
		}
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object, WordsEnvironment environment) { return null; }
}
