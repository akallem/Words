package words.exceptions;

@SuppressWarnings("serial")
public class ClassAlreadyExistsException extends WordsRuntimeException {
	
	private String className;
	
	public ClassAlreadyExistsException(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return String.format("Class %s already exists and cannot be created again.", className);
	}
}
