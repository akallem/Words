@SuppressWarnings("serial")
public class WordsObjectNotFoundException extends WordsException {
	
	private String objectName;
	
	public WordsObjectNotFoundException(String objectName, int lineNo) {
		super(lineNo);
		this.objectName = objectName;
	}
	
	public String getObjectName() {
		return objectName;
	}
	
	public String toString() {
		return String.format("Error: At line %d, object %s did not exist.", lineNo, objectName);
	}
	
}
