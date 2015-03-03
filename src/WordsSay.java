
public class WordsSay extends WordsAction {

	private String message;
	
	public WordsSay(WordsObject owner, String message) {
		this.message = message;
		this.owner = owner;
	}
	
	public void execute() {
		owner.setMessage(message);
	}
	
}
