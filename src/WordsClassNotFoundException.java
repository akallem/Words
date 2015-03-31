@SuppressWarnings("serial")
public class WordsClassNotFoundException extends WordsEnvironmentException {

	private String className;
	
	public WordsClassNotFoundException(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return String.format("class %s did not exist.", className);
	}
}
