
public class WordsFunctionArgException extends Exception {
	
	private static final long serialVersionUID = -441818511887327230L;
	private String expectedArg;
	private String receivedArg;
	private String functionName;

	public WordsFunctionArgException(String functionName, String receivedArg, String expectedArg) {
		this.functionName = functionName;
		this.receivedArg = receivedArg;
		this.expectedArg = expectedArg;
	}
	
	public String getExpectedArg() {
		return expectedArg;
	}
	
	public String getReceivedArg() {
		return receivedArg;
	}
	
	public String getFunctionName() {
		return functionName;
	}
}
