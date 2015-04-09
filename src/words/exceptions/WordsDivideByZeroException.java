package words.exceptions;
@SuppressWarnings("serial")
public class WordsDivideByZeroException extends WordsRuntimeException {
	
	public WordsDivideByZeroException() {}

	@Override
	public String toString() {
		return "Attempted to divide by 0.";
	}
}