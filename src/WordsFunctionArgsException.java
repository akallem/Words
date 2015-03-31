
@SuppressWarnings("serial")
public class WordsFunctionArgsException extends WordsException {
	
	private String expectedArgs;
	private String receivedArgs;
	private String functionName;

	public WordsFunctionArgsException(int lineNo, String functionName, String receivedArgs, String expectedArgs) {
		super(lineNo);
		this.functionName = functionName;
		this.receivedArgs = receivedArgs;
		this.expectedArgs = expectedArgs;
	}
	
	public String getString() {
		return String.format("Error: At line %d, function %s expected %s, received \"%s\".", 
				lineNo, functionName, expectedArgs, receivedArgs);
	}
}
