
@SuppressWarnings("serial")
public class WordsClassAlreadyExistsException extends WordsEnvironmentException {
	
	private String className;
	
	public WordsClassAlreadyExistsException(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return String.format("class %s already existed. Cannot be created again.", className);
	}
}
