import java.util.LinkedList;

public class WordsPropertyAssignment extends WordsAction {
	private String propertyName;
	private AST propertyValue;
	
	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) {
		// TODO
		
		/*
		 * Evaluate propertyValue, check result if needed, and assign it to propertyName using the object's methods for that
		 */
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object) { return null; }
}
