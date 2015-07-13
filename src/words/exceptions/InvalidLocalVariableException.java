package words.exceptions;

@SuppressWarnings("serial")
public class InvalidLocalVariableException extends WordsRuntimeException {
	
	@Override
	public String toString() {
		return "Local variables can only represent numbers or strings";
	}
}
