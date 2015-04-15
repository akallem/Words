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

			String propertyName = null;
			ASTValue propertyASTValue = null;
			
			try {
				propertyName = propertyAssign.children.get(0).eval(environment).stringValue;
				propertyASTValue = propertyAssign.children.get(1).eval(environment);
			} catch (WordsRuntimeException e) {
				throw new WordsProgramException(propertyAssign, e);
			}
			
			WordsProperty wordsProp = null;
			switch (propertyASTValue.type) {
				case NUM:
					wordsProp = new WordsProperty(propertyASTValue.numValue);
					break;
				case STRING:
					wordsProp = new WordsProperty(propertyASTValue.stringValue);
					break;
				case OBJ:
					wordsProp = new WordsProperty(propertyASTValue.objValue);
					break;
				case NOTHING:
					wordsProp = new WordsProperty(WordsProperty.PropertyType.NOTHING);
					break;
				default:
					throw new AssertionError("Shouldn't get here");
			}

			try {
				object.setProperty(propertyName, wordsProp);
			} catch (WordsRuntimeException e) {
				throw new WordsProgramException(propertyAssign, e);
			}
		}
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object, WordsEnvironment environment) { return null; }
}
