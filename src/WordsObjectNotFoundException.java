
public class WordsObjectNotFoundException extends Exception {
	
	private static final long serialVersionUID = -3671883021207555542L;
	private String objectName;
	
	public WordsObjectNotFoundException(String objectName) {
		this.objectName = objectName;
	}
	
	public String getObjectName() {
		return objectName;
	}
	
}
