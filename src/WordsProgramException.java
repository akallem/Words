/**
 * This exception stores an exception that was thrown by the environment and the AST where the exception occurred.
 * The WordsEnvironmentException contains what went wrong, while the AST contains where it went wrong.
 *
 */
@SuppressWarnings("serial")
public class WordsProgramException extends Exception {
	
	private WordsEnvironmentException exception;
	private AST offendingAST;
	
	public WordsProgramException(AST offendingAST, WordsEnvironmentException exception) {
		this.offendingAST = offendingAST;
		this.exception = exception;
	}
	
	public WordsEnvironmentException getEnvironmentException() {
		return exception;
	}
	
	public void replaceAST(AST newAST) {
		this.offendingAST = newAST;
	}
	
	@Override
	public String toString() {
		return String.format("Error at line %d: %s.", offendingAST.lineNo, exception.toString());
	}
}
