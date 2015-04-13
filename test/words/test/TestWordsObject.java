package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.LNodeNum;
import words.ast.LNodeString;
import words.environment.Direction;
import words.environment.WordsClass;
import words.environment.WordsMove;
import words.environment.WordsObject;
import words.environment.WordsPosition;
import words.environment.WordsProperty;
import words.environment.WordsSay;

public class TestWordsObject {
	WordsPosition startPos = new WordsPosition(4, -9);
	WordsClass thing = new WordsClass("thing", null);
	WordsObject obj = new WordsObject("test", thing, new WordsPosition(4, -9));
	
	@Test
	public void executeNextActionShouldNotFailWhenEmpty() {
		// Any exception (which would likely be a Java null pointer exception or the like, if it occurred, is a failure
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void executeNextActionShouldExecuteNextMoveAction() {
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.RIGHT), new LNodeNum(1)));
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.RIGHT), new LNodeNum(1)));
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.LEFT), new LNodeNum(1)));
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.LEFT), new LNodeNum(1)));
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 1 to the right in total", obj.getCurrentCell().x, startPos.x + 1);
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 2 to the right in total", obj.getCurrentCell().x, startPos.x + 2);
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 1 to the right in total again", obj.getCurrentCell().x, startPos.x + 1);
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object is back at its starting position", obj.getCurrentCell().x, startPos.x);
	}
	
	@Test
	public void executeNextActionShouldExecuteNextSayAction() {
		// This line will need to be changed when WordsSay is updated to take an AST expression
		obj.enqueueAction(new WordsSay(new LNodeString("first")));
		obj.enqueueAction(new WordsSay(new LNodeString("second")));
		obj.enqueueAction(new WordsSay(new LNodeString("third")));
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object's message is first message", obj.getCurrentMessage(), "first");
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object's message is second message", obj.getCurrentMessage(), "second");
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object's message is third message", obj.getCurrentMessage(), "third");
	}
	
	
	@Test
	public void enqeueAtFrontShouldBeNextActionExecuted() {
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.RIGHT), new LNodeNum(3)));
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.UP), new LNodeNum(5)));
		obj.enqueueAction(new WordsMove(new Direction(Direction.Type.DOWN), new LNodeNum(5)));
		
		// This should be first to be executed
		obj.enqueueActionAtFront(new WordsMove(new Direction(Direction.Type.LEFT), new LNodeNum(1)));
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 1 to the left", obj.getCurrentCell().x, startPos.x - 1);
	}
	
	@Test
	public void objectMovesUpCorrectly() {
		obj.moveUp();
		assertEquals("Object moved up 1", obj.getCurrentCell().y, startPos.y - 1);
	}
	
	@Test
	public void objectMovesDownCorrectly() {
		obj.moveDown();
		assertEquals("Object moved down 1", obj.getCurrentCell().y, startPos.y + 1);
	}
	
	@Test
	public void objectMovesLeftCorrectly() {
		obj.moveLeft();
		assertEquals("Object moved let 1", obj.getCurrentCell().x, startPos.x - 1);
	}
	
	@Test
	public void objectMovesRightCorrectly() {
		obj.moveRight();
		assertEquals("Object moved right 1", obj.getCurrentCell().x, startPos.x + 1);
	}
	
	@Test
	public void shouldGetPreviouslySetProperty() {
		String propertyName = "height";
		WordsProperty numProperty = new WordsProperty(15.5);
		
		obj.setProperty(propertyName, numProperty);
		assertEquals("Retrieved property should match set property", numProperty, obj.getProperty(propertyName));
	}
	
	@Test
	public void missingPropertyShouldGetNothing() {
		String propertyName = "garbage";
		assertEquals("Retrieved property was NOTHING", WordsProperty.PropertyType.NOTHING, obj.getProperty(propertyName).type);
	}
	
	@Test
	public void settingMissingPropertyToNothingShouldHaveNoEffect() {
		String propertyName = "height";
		obj.setProperty(propertyName, new WordsProperty(WordsProperty.PropertyType.NOTHING));		
		assertEquals("Retrieved property was NOTHING", WordsProperty.PropertyType.NOTHING, obj.getProperty(propertyName).type);
	}
	
	@Test
	public void settingExistingPropertyToNothingShouldRemoveIt() {
		String propertyName = "height";
		
		// Set it, then remove it by assigning NOTHING
		obj.setProperty(propertyName, new WordsProperty(15.5));		
		obj.setProperty(propertyName, new WordsProperty(WordsProperty.PropertyType.NOTHING));
		
		assertEquals("Retrieved property was NOTHING", WordsProperty.PropertyType.NOTHING, obj.getProperty(propertyName).type);
	}

	@Test
	public void gettingRowAndColumnPropertiesShouldMatchObjectPosition() {
		// Note: x is column, y is row
		assertEquals("Object's row matches y", (double) obj.getCurrentCell().y, obj.getProperty("row").numProperty, 0.0001);
		assertEquals("Object's column matches x", (double) obj.getCurrentCell().x, obj.getProperty("column").numProperty, 0.0001);
	}
	
	@Test
	public void settingRowAndColumnPropertiesShouldSetObjectPosition() {
		obj.setProperty("row", new WordsProperty(-4));
		obj.setProperty("column", new WordsProperty(2));
		
		// Note: x is column, y is row
		assertEquals("Object's x is 2", obj.getCurrentCell().x, 2);
		assertEquals("Object's y is 4", obj.getCurrentCell().y, -4);
	}
	
	@Test
	public void settingRowAndColumnPropertiesShouldBeRounded() {
		obj.setProperty("row", new WordsProperty(-4.3));
		obj.setProperty("column", new WordsProperty(2.9));
		
		// Note: x is column, y is row
		assertEquals("Object's x is 3", obj.getCurrentCell().x, 3);
		assertEquals("Object's y is -4", obj.getCurrentCell().y, -4);
	}
	
	@Test
	public void inheritanceOfClassPropertyShouldWork() {
		String propertyName = "height";
		WordsProperty numProperty = new WordsProperty(15.5);
		
		assertEquals("Object currently does not have the property", WordsProperty.PropertyType.NOTHING, obj.getProperty(propertyName).type);
		thing.setProperty(propertyName, numProperty);
		assertEquals("Object inherited property from parent", numProperty, obj.getProperty(propertyName));
	}
}
