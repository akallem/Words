
@SuppressWarnings("serial")
public class WordsClassAlreadyExistsException extends WordsException {
	
	private String className;
	
	public WordsClassAlreadyExistsException(int lineNo, String className) {
		super(lineNo);
		this.className = className;
	}
	
	@Override
	public String toString() {
		return String.format("Error: At line %d, class %s already existed. Cannot be created again.", lineNo, className);
	}
}
