
@SuppressWarnings("serial")
public class WordsFunctionArgException extends WordsException {
	
	private String expectedArg;
	private String receivedArg;
	private String functionName;

	public WordsFunctionArgException(int lineNo, String functionName, String receivedArg, String expectedArg) {
		super(lineNo);
		this.functionName = functionName;
		this.receivedArg = receivedArg;
		this.expectedArg = expectedArg;
	}
	
	public String getString() {
		return String.format("Error: At line %d, function %s expected %s, received \"%s\".", 
				lineNo, functionName, expectedArg, receivedArg);
	}
}
