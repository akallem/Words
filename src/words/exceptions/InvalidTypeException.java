package words.exceptions;
@SuppressWarnings("serial")
public class InvalidTypeException extends WordsRuntimeException {
	
	private String expectedType;
	private String receivedType;
	
	public InvalidTypeException(String expected, String received) {
		this.expectedType = expected;
		this.receivedType = received;
	}

	@Override
	public String toString() {
		return String.format("Expected %s, received %s.", expectedType, receivedType);
	}
}
