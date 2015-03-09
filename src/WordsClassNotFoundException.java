
public class WordsClassNotFoundException extends Exception {

	private static final long serialVersionUID = 7435641006130413183L;
	private String className;
	
	public WordsClassNotFoundException(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
}
