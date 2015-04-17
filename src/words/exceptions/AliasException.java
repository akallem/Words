package words.exceptions;

@SuppressWarnings("serial")
public class AliasException extends WordsRuntimeException {
	
	private String message;
	
	public AliasException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
