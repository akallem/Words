@SuppressWarnings("serial")
public class InvalidTypeException extends WordsException {
	
	private AST.ValueType expectedType;
	private AST.ValueType receivedType;
	
	public InvalidTypeException(int lineNo, AST.ValueType expected, AST.ValueType received) {
		super(lineNo);
		this.expectedType = expected;
		this.receivedType = received;
	}
	
	public String toString() {
		return String.format("Error: At line %d, expected %s, received %s.", lineNo, expectedType.toString(), receivedType.toString());
	}
}
