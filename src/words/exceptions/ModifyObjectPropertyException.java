package words.exceptions;
@SuppressWarnings("serial")
public class ModifyObjectPropertyException extends WordsRuntimeException {
	private String propertyName;
	
	public ModifyObjectPropertyException(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String toString() {
		return "Not permitted to modify Object " + propertyName;
	}
}