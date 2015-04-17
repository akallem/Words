package words.exceptions;

@SuppressWarnings("serial")
public class WordsAliasException extends WordsRuntimeException {
	
	private String message;
	
	public WordsAliasException(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
