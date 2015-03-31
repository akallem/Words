@SuppressWarnings("serial")
public class InvalidTypeException extends WordsException {
	
	private String expectedType;
	private String receivedType;
	
	public InvalidTypeException(int lineNo, String expected, String received) {
		super(lineNo);
		this.expectedType = expected;
		this.receivedType = received;
	}

	public String toString() {
		return String.format("Error: At line %d, expected %s, received %s.", lineNo, expectedType, receivedType);
	}
}
