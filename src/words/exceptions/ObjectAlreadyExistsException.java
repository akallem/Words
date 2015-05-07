package words.exceptions;
@SuppressWarnings("serial")
public class ObjectAlreadyExistsException extends WordsRuntimeException {

	private String objectName;
	
	public ObjectAlreadyExistsException(String objectName) {
		this.objectName = objectName;
	}
	
	@Override
	public String toString() {
		return String.format("Object %s already exists and cannot be created again.", objectName);
	}
}
