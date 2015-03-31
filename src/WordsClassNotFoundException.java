@SuppressWarnings("serial")
public class WordsClassNotFoundException extends WordsException {

	private String className;
	
	public WordsClassNotFoundException(int lineNo, String className) {
		super(lineNo);
		this.className = className;
	}
	
	@Override
	public String toString() {
		return String.format("Error: At line %d, class %s did not exist.", lineNo, className);
	}
}
