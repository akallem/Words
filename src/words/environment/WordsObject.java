package words.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import words.environment.Property.PropertyType;
import words.exceptions.*;

public class WordsObject {
	private String objectName;
	private WordsClass wordsClass;
	private HashMap<String, Property> properties;
	private LinkedList<Action> actionQueue;
	private Position cell;
	private String currentMessage;
	private Action lastAction;
	private HashMap<WordsObject, ArrayList<Property>> referers;
	
	public void updateReferers(WordsObject referringObject, Property referringProperty) {
		ArrayList<Property> referringProperties;
		if ((referringProperties = referers.get(referringObject)) == null) {
			referringProperties = new ArrayList<Property>();
			referers.put(referringObject, referringProperties);
		}
		
		referringProperties.add(referringProperty);
	}
	
	public void clearReferers() {
		for (WordsObject referringObject : referers.keySet()) {
			for (Property property : referers.get(referringObject)) {
				property.type = PropertyType.NOTHING;
				property.objProperty = null;
			}
			referers.remove(referringObject);
		}
	}
	
	public WordsObject(String objectName, WordsClass wordsClass, Position cell) {
		this.wordsClass = wordsClass;
		this.objectName = objectName;
		this.cell = cell;
		this.actionQueue = new LinkedList<Action>();
		this.properties = new HashMap<String, Property>();
		this.referers = new HashMap<WordsObject, ArrayList<Property>>();
	}
	
	public void clearActionQueue() {
		actionQueue.clear();
	}

	public void enqueueAction(Action action) {
		actionQueue.add(action);
	}

	public void enqueueActionAtFront(Action action) {
		actionQueue.addFirst(action);
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
		// Special handling of "row" and "column" properties
		if (propertyName.equals("row"))
			return new Property(cell.y);
		else if (propertyName.equals("column"))
			return new Property(cell.x);

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

		if (properties.containsKey(propertyName) && property.type == Property.PropertyType.NOTHING)
			properties.remove(propertyName);
		else {
			if (property.type == PropertyType.OBJECT) {
				property.objProperty.updateReferers(this, property);
			}
			properties.put(propertyName, property);
		}
	}
	
	public void addReferer(WordsObject referer, Property refererProperty) {
		
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
		if (!actionQueue.isEmpty()) {
			while (actionQueue.peek().isExpandable()) {
				Action action = actionQueue.pop();
				actionQueue.addAll(0, action.expand(this, environment));
			}
			
			Action action = actionQueue.pop();
			lastAction = action;
			action.execute(this, environment);
		} else {
			lastAction = new WaitAction();
		}
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