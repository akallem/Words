package words.exceptions;

import words.environment.WordsProperty;

@SuppressWarnings("serial")
public class WordsReferenceException extends WordsRuntimeException {
	
	private WordsProperty.PropertyType wordsType;
	private String wordsId;
	
	public WordsReferenceException(String wordsId, WordsProperty.PropertyType wordsType) {
		this.wordsId = wordsId;
		this.wordsType = wordsType;
	}
	
	public String toString() {
		return String.format("Attempted to dereference %s which has invalid type %s", wordsId, wordsType);
	}
}
