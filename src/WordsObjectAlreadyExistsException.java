
public class WordsObjectAlreadyExistsException extends Exception {

	private String objectName;
	
	public WordsObjectAlreadyExistsException(String objectName) {
		this.objectName = objectName;
	}
	
	public String getObjectName() {
		return objectName;
	}
}
