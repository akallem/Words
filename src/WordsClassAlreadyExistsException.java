
public class WordsClassAlreadyExistsException extends Exception {
	private String className;
	
	public WordsClassAlreadyExistsException(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
}
