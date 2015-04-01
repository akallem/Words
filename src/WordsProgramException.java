
@SuppressWarnings("serial")
public class WordsProgramException extends Exception {
	private WordsEnvironmentException exception;
	private int lineNo;
	
	public WordsProgramException(int lineNo, WordsEnvironmentException exception) {
		this.lineNo = lineNo;
		this.exception = exception;
	}
	
	public WordsEnvironmentException getInnerException() {
		return exception;
	}
	
	@Override
	public String toString() {
		return String.format("Error at line %d: %s", lineNo, exception.toString());
	}
	
}
