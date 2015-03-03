import java.util.ArrayList;
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
	
	public void moveUp() {
		this.cell.y++;
	}
	
	public void moveDown() {
		this.cell.y--;
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
			System.out.println("popped action");
			action.execute();
		}
	}
	
	public WordsPosition getCurrentCell() {
		return cell;
	}
	
	public String getClassName() {
		return wordsClass.getClassName();
	}
	
	public String getCurrentMessage() {
		return currentMessage;
	}

	public String getObjectName() {
		return objectName;
	}
	
}
