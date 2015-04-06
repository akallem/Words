@SuppressWarnings("serial")
public class WordsArithmeticException extends WordsRuntimeException {
	
	private String type1;
	private String type2;
	
	public WordsArithmeticException(String type1, String type2) {
		this.type1 = type1;
		this.type2 = type2;
	}

	@Override
	public String toString() {
		return String.format("Invalid arithmetic operation on %s and %s", type1, type2);
	}
}