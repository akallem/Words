package words.exceptions;
@SuppressWarnings("serial")
public class WordsReferenceException extends WordsRuntimeException {
	
	private String message;
	
	public WordsReferenceException(String message) {
		this.message = message;
	}
	
	public WordsReferenceException() {
		this.message = "attempted to dereference nothing";
	}
	
	public String toString() {
		return this.message;
	}
}
