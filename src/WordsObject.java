import java.util.HashMap;
import java.util.LinkedList;


public class WordsObject {
	private String objectName;
	private WordsClass wordsClass;
	private HashMap<String, WordsProperty> properties;
	private LinkedList<WordsAction> actionQueue;
	private WordsPosition cell;
	private String currentMessage;
	
	public WordsObject(WordsClass wordsClass, String objectName, WordsPosition cell) {
		this.wordsClass = wordsClass;
		this.objectName = objectName;
		this.cell = cell;
		this.actionQueue = new LinkedList<WordsAction>();
	}
	
	public void enqueueAction(WordsAction action) {
		actionQueue.add(action);
	}
	
	public void enqueueActionAtFront(WordsAction action) {
		actionQueue.addFirst(action);
	}
	
	public WordsProperty getProperty(String propName) {
		if (properties.containsKey(propName)) {
			return properties.get(propName);
		} else {
			return new WordsProperty(WordsProperty.PropertyType.NOTHING);
		}
	}
	
	public void moveUp() {
		this.cell.y--;
	}
	
	public void moveDown() {
		this.cell.y++;
	}
	
	public void moveLeft() {
		this.cell.x--;
	}
	
	public void moveRight() {
		this.cell.x++;
	}
	
	public void doAction() {
		if (!actionQueue.isEmpty()) {
			WordsAction action = actionQueue.pop();
			action.execute();
		}
	}
	
	public WordsPosition getCurrentCell() {
		return cell;
	}
	
	public String getClassName() {
		return wordsClass.getClassName();
	}
	
	public WordsClass getWordsClass() {
		return wordsClass;
	}
	
	public String getCurrentMessage() {
		return currentMessage;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setMessage(String message) {
		currentMessage = message;
	}
}
