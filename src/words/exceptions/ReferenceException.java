package words.exceptions;

import words.ast.ASTValue;

@SuppressWarnings("serial")
public class ReferenceException extends WordsRuntimeException {
	
	private ASTValue.Type wordsType;
	private String wordsId;
	
	public ReferenceException(String wordsId, ASTValue.Type wordsType) {
		this.wordsId = wordsId;
		this.wordsType = wordsType;
	}
	
	public String toString() {
		return String.format("Attempted to dereference %s which has invalid type %s", wordsId, wordsType);
	}
}
