@SuppressWarnings("serial")
public class WordsObjectAlreadyExistsException extends WordsException {

	private String objectName;
	
	public WordsObjectAlreadyExistsException(int lineNo, String objectName) {
		super(lineNo);
		this.objectName = objectName;
	}
	
	public String toString() {
		return String.format("Error: At line %d, object %s already existed. Cannot be created again.", lineNo, objectName);
	}
}
