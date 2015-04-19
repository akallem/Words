package words.exceptions;

import words.environment.Property;

@SuppressWarnings("serial")
public class ReferenceException extends WordsRuntimeException {
	
	private Property.PropertyType wordsType;
	private String wordsId;
	
	public ReferenceException(String wordsId, Property.PropertyType wordsType) {
		this.wordsId = wordsId;
		this.wordsType = wordsType;
	}
	
	public String toString() {
		return String.format("Attempted to dereference %s which has invalid type %s", wordsId, wordsType);
	}
}
