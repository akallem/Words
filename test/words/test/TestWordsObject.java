package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsObject {
	Position startPos = new Position(4, -9);
	WordsClass thing = new WordsClass("thing", null);
	WordsObject obj = new WordsObject("test", thing, new Position(4, -9));
	
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
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.RIGHT), new LNodeNum(1)));
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.RIGHT), new LNodeNum(1)));
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.LEFT), new LNodeNum(1)));
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.LEFT), new LNodeNum(1)));
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 1 to the right in total", obj.getCurrentPosition().x, startPos.x + 1);
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 2 to the right in total", obj.getCurrentPosition().x, startPos.x + 2);
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 1 to the right in total again", obj.getCurrentPosition().x, startPos.x + 1);
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object is back at its starting position", obj.getCurrentPosition().x, startPos.x);
	}
	
	@Test
	public void executeNextActionShouldExecuteNextSayAction() {
		// This line will need to be changed when WordsSay is updated to take an AST expression
		obj.enqueueAction(new SayAction(new LNodeString("first")));
		obj.enqueueAction(new SayAction(new LNodeString("second")));
		obj.enqueueAction(new SayAction(new LNodeString("third")));
		
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
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.RIGHT), new LNodeNum(3)));
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.UP), new LNodeNum(5)));
		obj.enqueueAction(new MoveAction(new Direction(Direction.Type.DOWN), new LNodeNum(5)));
		
		// This should be first to be executed
		obj.enqueueActionAtFront(new MoveAction(new Direction(Direction.Type.LEFT), new LNodeNum(1)));
		
		try {
			obj.executeNextAction(null);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals("Object moved 1 to the left", obj.getCurrentPosition().x, startPos.x - 1);
	}
	
	@Test
	public void objectMovesUpCorrectly() {
		obj.moveUp();
		assertEquals("Object moved up 1", obj.getCurrentPosition().y, startPos.y + 1);
	}
	
	@Test
	public void objectMovesDownCorrectly() {
		obj.moveDown();
		assertEquals("Object moved down 1", obj.getCurrentPosition().y, startPos.y - 1);
	}
	
	@Test
	public void objectMovesLeftCorrectly() {
		obj.moveLeft();
		assertEquals("Object moved let 1", obj.getCurrentPosition().x, startPos.x - 1);
	}
	
	@Test
	public void objectMovesRightCorrectly() {
		obj.moveRight();
		assertEquals("Object moved right 1", obj.getCurrentPosition().x, startPos.x + 1);
	}
	
	@Test
	public void shouldGetPreviouslySetProperty() {
		String propertyName = "height";
		Property numProperty = new Property(15.5);
		
		try {
			obj.setProperty(propertyName, numProperty);
		} catch (Exception e) {
			fail();
		}
		assertEquals("Retrieved property should match set property", numProperty, obj.getProperty(propertyName));
	}
	
	@Test
	public void missingPropertyShouldGetNothing() {
		String propertyName = "garbage";
		assertEquals("Retrieved property was NOTHING", Property.PropertyType.NOTHING, obj.getProperty(propertyName).type);
	}
	
	@Test
	public void settingMissingPropertyToNothingShouldHaveNoEffect() {
		String propertyName = "height";
		try {
			obj.setProperty(propertyName, new Property(Property.PropertyType.NOTHING));
		} catch (Exception e) {
			fail();
		}	
		assertEquals("Retrieved property was NOTHING", Property.PropertyType.NOTHING, obj.getProperty(propertyName).type);
	}
	
	@Test
	public void settingExistingPropertyToNothingShouldRemoveIt() {
		String propertyName = "height";
		
		// Set it, then remove it by assigning NOTHING
		try {
			obj.setProperty(propertyName, new Property(15.5));		
			obj.setProperty(propertyName, new Property(Property.PropertyType.NOTHING));
		} catch (Exception e) {
			fail();
		}
		assertEquals("Retrieved property was NOTHING", Property.PropertyType.NOTHING, obj.getProperty(propertyName).type);
	}

	@Test
	public void gettingRowAndColumnPropertiesShouldMatchObjectPosition() {
		// Note: x is column, y is row
		assertEquals("Object's row matches y", (double) obj.getCurrentPosition().y, obj.getProperty("row").numProperty, 0.0001);
		assertEquals("Object's column matches x", (double) obj.getCurrentPosition().x, obj.getProperty("column").numProperty, 0.0001);
	}
	
	@Test
	public void settingRowAndColumnPropertiesShouldSetObjectPosition() {
		try {
			obj.setProperty("row", new Property(-4));
			obj.setProperty("column", new Property(2));
		} catch (Exception e) {
			fail();
		}
		
		// Note: x is column, y is row
		assertEquals("Object's x is 2", obj.getCurrentPosition().x, 2);
		assertEquals("Object's y is 4", obj.getCurrentPosition().y, -4);
	}
	
	@Test
	public void settingRowAndColumnPropertiesShouldBeRounded() {
		try {
			obj.setProperty("row", new Property(-4.3));
			obj.setProperty("column", new Property(2.9));
		} catch (Exception e) {
			fail();
		}
		// Note: x is column, y is row
		assertEquals("Object's x is 3", obj.getCurrentPosition().x, 3);
		assertEquals("Object's y is -4", obj.getCurrentPosition().y, -4);
	}

	@Test (expected = InvalidTypeException.class)
	public void setPropertyCorrectType() throws WordsRuntimeException {
		obj.setProperty("row", new Property("String"));
	}
	
	@Test
	public void inheritanceOfClassPropertyShouldWork() {
		String propertyName = "height";
		Property numProperty = new Property(15.5);
		
		assertEquals("Object currently does not have the property", Property.PropertyType.NOTHING, obj.getProperty(propertyName).type);
		thing.setProperty(propertyName, numProperty);
		assertEquals("Object inherited property from parent", numProperty, obj.getProperty(propertyName));
	}
}
