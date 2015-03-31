import java.util.LinkedList;

public class WordsWait extends WordsAction {
	private AST lengthExpression;
	private double lengthValue;
	
	public WordsWait(AST lengthExpression) {
		this.lengthExpression = lengthExpression;
	}
	
	/**
	 * Create a new WordsWait action.  lengthValue must round to a positive integer.
	 */
	public WordsWait(double lengthValue) {
		this.lengthValue = lengthValue;
	}

	@Override
	public boolean isExecutable() {
		if (lengthExpression != null)
			return false;

		if (Math.round(lengthValue) != 1)
			return false;
		
		return true;
	}

	@Override
	public void doExecute(WordsObject object, WordsEnvironment environment) {
		// Nothing to do, this will simply cause the object to wait 1 frame
	}

	@Override
	public LinkedList<WordsAction> doExpand(WordsObject object) {
		if (lengthExpression != null) {
			AST.ASTValue value;
			try {
				value = lengthExpression.eval(null);
			} catch (WordsException e) {
				System.err.println(e.toString());
				return null;
			}
			
			if (value.type != AST.ValueType.NUM) {
				// TODO
				// Throw an appropriate WordsException
			}
			
			lengthValue = Math.round(value.numValue);
			lengthExpression = null;						// Not necessary, but including for clarity since once the expression is evaluated, it is no longer needed
		}

		// Throw an appropriate WordsException if lengthValue is zero or negative
		
		LinkedList<WordsAction> list = new LinkedList<WordsAction>();

		// Decompose into executable 1-frame waits
		for (int i = 0; i < lengthValue; i++)
			list.add(new WordsWait(1));
		
		return list;
	}
}