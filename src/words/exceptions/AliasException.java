package words.exceptions;

@SuppressWarnings("serial")
public class AliasException extends WordsRuntimeException {
	public AliasException() { }
	
	@Override
	public String toString() {
		return "Aliases may not be named the same.";
	}
}
