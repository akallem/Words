package words.exceptions;
@SuppressWarnings("serial")
public class CustomActionNotFoundException extends WordsRuntimeException {
	private String customActionName;
	
	public CustomActionNotFoundException(String customActionName) {
		this.customActionName = customActionName;
	}
	
	public String getObjectName() {
		return customActionName;
	}
	
	@Override
	public String toString() {
		return String.format("Custom action %s was not found.", customActionName);
	}
}
