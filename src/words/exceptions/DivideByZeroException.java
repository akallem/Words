package words.exceptions;
@SuppressWarnings("serial")
public class DivideByZeroException extends WordsRuntimeException {
	
	public DivideByZeroException() {}

	@Override
	public String toString() {
		return "Attempted to divide by 0.";
	}
}