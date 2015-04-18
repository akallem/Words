package words.exceptions;
@SuppressWarnings("serial")
public class ObjectAlreadyExistsException extends WordsRuntimeException {

	private String objectName;
	
	public ObjectAlreadyExistsException(String objectName) {
		this.objectName = objectName;
	}
	
	@Override
	public String toString() {
		return String.format("object %s already existed. Cannot be created again.", objectName);
	}
}
