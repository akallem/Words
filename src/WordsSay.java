import java.util.LinkedList;

public class WordsSay extends WordsAction {
	private String message;
	
	public WordsSay(String message) {
		this.message = message;
	}
	
	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void doExecute(WordsObject object, WordsEnvironment environment) {
		object.setMessage(message);
	}

	@Override
	protected LinkedList<WordsAction> doExpand(WordsObject object) { return null; }
}