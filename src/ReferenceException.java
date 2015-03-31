@SuppressWarnings("serial")
public class ReferenceException extends WordsEnvironmentException {
	
	public ReferenceException() {
	}
	
	public String toString() {
		return String.format("attempted to dereference nothing");
	}
}
