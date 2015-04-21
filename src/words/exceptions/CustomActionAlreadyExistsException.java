package words.exceptions;

@SuppressWarnings("serial")
public class CustomActionAlreadyExistsException extends WordsRuntimeException {
	private String customActionName;
	
	public CustomActionAlreadyExistsException(String customActionName) {
		this.customActionName = customActionName;
	}
	
	@Override
	public String toString() {
		return String.format("Action %s was already defined.  Cannot be defined again.", customActionName);
	}
}
