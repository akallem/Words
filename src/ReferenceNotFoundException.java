@SuppressWarnings("serial")
public class ReferenceNotFoundException extends WordsException {
	
	private String objectName;
	private String propertyName;
	
	public ReferenceNotFoundException(int lineNo, String objectName, String propertyName) {
		super(lineNo);
		this.objectName = objectName;
		this.propertyName = propertyName;
	}
	
	public String toString() {
		return String.format("Error: At line %d, object %s did not contain property %s.", lineNo, objectName, propertyName);
	}
}
