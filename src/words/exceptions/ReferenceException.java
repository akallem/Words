package words.exceptions;

import words.environment.Variable;

@SuppressWarnings("serial")
public class ReferenceException extends WordsRuntimeException {
	
	private Variable.VariableType wordsType;
	private String wordsId;
	
	public ReferenceException(String wordsId, Variable.VariableType wordsType) {
		this.wordsId = wordsId;
		this.wordsType = wordsType;
	}
	
	public String toString() {
		return String.format("Attempted to dereference %s which has invalid type %s", wordsId, wordsType);
	}
}
