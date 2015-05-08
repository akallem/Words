package words.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import words.environment.Property.PropertyType;
import words.exceptions.*;

/**
 * An event listener as specified in the Words language.
 */
public class WordsObject {
	private String objectName;
	private WordsClass wordsClass;
	private HashMap<String, Property> properties;
	private LinkedList<Action> actionQueue;
	private Position cell;
	private String currentMessage;
	private Action lastAction;
	private boolean shouldRemove;
	private boolean createdInThisFrame;
	private Position lastCell;
	private boolean movedInTurn;
	
	// While an object is expanding a custom action, actions are enqueued in a separate list
	private boolean isExpandingCustomAction;
	private LinkedList<Action> customActionExpansion;
	private HashMap<WordsObject, ArrayList<Property>> referers;
	
	public WordsObject(String objectName, WordsClass wordsClass, Position cell) {
		this.wordsClass = wordsClass;
		this.objectName = objectName;
		this.cell = cell;
		this.actionQueue = new LinkedList<Action>();
		this.properties = new HashMap<String, Property>();
		this.customActionExpansion = new LinkedList<Action>();
		this.isExpandingCustomAction = false;
		this.referers = new HashMap<WordsObject, ArrayList<Property>>();
		this.shouldRemove = false;
		this.createdInThisFrame = true;
	}
	
	public void clearActionQueue() {
		actionQueue.clear();
	}

	public void enqueueAction(Action action) {
		if (this.isExpandingCustomAction) {
			customActionExpansion.add(action);
		} else {
			actionQueue.add(action);
		}
	}

	public void enqueueActionAtFront(Action action) {
		if (this.isExpandingCustomAction) {
			customActionExpansion.addFirst(action);
		} else {
			actionQueue.addFirst(action);
		}
	}

	/**
	 * Prepares this object to receive queue statements that represent the expansion of a custom action.
	 */
	public void startExpandingCustomAction() {
		isExpandingCustomAction = true;
		customActionExpansion.clear();
	}
	
	/**
	 * Returns the list of actions that was expanded and reverts the object to its normal state.
	 */
	public LinkedList<Action> finishExpandingCustomAction() {
		isExpandingCustomAction = false;
		return customActionExpansion;
	}

	/**
	 * Retrieves a property of an object by looking only at the object itself, ignoring its class chain.
	 * A missing property returns null.
	 */
	private Property getOwnProperty(String propertyName) {
		if (properties.containsKey(propertyName))
			return properties.get(propertyName);
		else
			return null;
	}

	/**
	 * Retrieves a property on an object by looking at the object itself and its class chain.
	 * A missing property returns a WordsProperty of type NOTHING.
	 */
	public Property getProperty(String propertyName) {
		// Special handling of "row" "column" "name" and "class" properties
		if (propertyName.equals("row"))
			return new Property(cell.y);
		else if (propertyName.equals("column"))
			return new Property(cell.x);
		else if (propertyName.equals("name"))
			return new Property(objectName);
		else if (propertyName.equals("class"))
			return new Property(wordsClass.getClassName());

		Property property = getOwnProperty(propertyName);

		if (property != null)
			return property;
		else
			return wordsClass.getProperty(propertyName);
	}

	/**
	 * Assigns a property to an object.  Assigning NOTHING removes the property, if it exists.
	 */
	public void setProperty(String propertyName, Property property) throws WordsRuntimeException {
		// Special handling of "row" and "column" properties
		if (propertyName.equals("row") || propertyName.equals("column")) {
			if (property.type != Property.PropertyType.NUM) {
				throw new InvalidTypeException("NUM", property.type.toString());
			}

			if (propertyName.equals("row"))
				cell.y = (int) Math.round(property.numProperty);
			else
				cell.x = (int) Math.round(property.numProperty);
			
			return;
		}
		
		// User cannot change "name" and "class"
		if (propertyName.equals("name") || propertyName.equals("class")) {
			throw new ModifyObjectPropertyException(propertyName);
		}

		if (properties.containsKey(propertyName) && property.type == Property.PropertyType.NOTHING)
			properties.remove(propertyName);
		else {
			if (property.type == PropertyType.OBJECT) {
				property.objProperty.updateReferers(this, property);
			}
			properties.put(propertyName, property);
		}
	}
	
	public void updateReferers(WordsObject referringObject, Property referringProperty) {
		ArrayList<Property> referringProperties;
		if ((referringProperties = referers.get(referringObject)) == null) {
			referringProperties = new ArrayList<Property>();
			referers.put(referringObject, referringProperties);
		}
		
		referringProperties.add(referringProperty);
	}
	
	public void clearReferers() {
		for (Iterator<ArrayList<Property>> it = referers.values().iterator(); it.hasNext();) {
			ArrayList<Property> properties = it.next();
			
			for (Property property : properties) {
				property.type = PropertyType.NOTHING;
				property.objProperty = null;
			}
			
			it.remove();
		}
	}
	
	public void prepareForRemoval() {
		shouldRemove = true;
	}
	
	public boolean shouldRemove() {
		return shouldRemove;
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

	public void executeNextAction(Environment environment) throws WordsProgramException {
		if (createdInThisFrame) {
			createdInThisFrame = false;
		} else {
			if (!lastCell.equals(cell)) {
				this.movedInTurn = true;
			}
			else {
				this.movedInTurn = false;
			}
			if (!actionQueue.isEmpty()) {
				while (actionQueue.peek().isExpandable()) {
					Action action = actionQueue.pop();
					actionQueue.addAll(0, action.expand(this, environment));
					
					// If the action we just expanded was a custom action, it is possible that its expansion
					// executed some immediate statements but caused no new actions to be enqueued
					// In this case, we are done
					if (actionQueue.isEmpty()) {
						lastAction = new WaitAction(environment.getCurrentScope());
						return;
					}
				}
				
				Action action = actionQueue.pop();
				lastAction = action;
				lastCell = new Position(cell);
				action.execute(this, environment);
				if (!lastCell.equals(cell)) {
					this.movedInTurn = true;
				}
			} else {
				lastAction = new WaitAction(environment.getCurrentScope());
			}
		}
		// Store the last position based on actions
		lastCell = new Position(cell);
	}
	
	public boolean movedInLastTurn() {
		return lastAction == null || movedInTurn;
	}

	public Position getCurrentPosition() {
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
	
	public Action getLastAction() {
		return lastAction;
	}

	public void setMessage(String message) {
		currentMessage = message;
	}
}