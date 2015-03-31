@SuppressWarnings("serial")
public class WordsObjectNotFoundException extends WordsException {
	
	private String objectName;
	
	public WordsObjectNotFoundException(int lineNo, String objectName) {
		super(lineNo);
		this.objectName = objectName;
	}
	
	public String getObjectName() {
		return objectName;
	}
	
	@Override
	public String toString() {
		return String.format("Error: At line %d, object %s did not exist.", lineNo, objectName);
	}
}
