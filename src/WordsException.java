
@SuppressWarnings("serial")
public class WordsException extends Exception {
	
	public int lineNo;
	
	public WordsException(int lineNo) {
		this.lineNo = lineNo;
	}
}
