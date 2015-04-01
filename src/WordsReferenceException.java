@SuppressWarnings("serial")
public class WordsReferenceException extends WordsEnvironmentException {
	
	public WordsReferenceException() {
	}
	
	public String toString() {
		return String.format("attempted to dereference nothing");
	}
}
