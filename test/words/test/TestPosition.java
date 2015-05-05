package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.environment.*;

public class TestPosition {
	@Test
	public void testDoubleParams() {
		Position pos = new Position(3.7, 5.2);
		assertEquals("X position correctly rounded", pos.x, 4);
		assertEquals("Y position correctly rounded", pos.y, 5);
	}
	
	@Test
	public void testIntParams() {
		Position pos = new Position(2,4);
		assertEquals("X position correctly rounded", pos.x, 2);
		assertEquals("Y position correctly rounded", pos.y, 4);
	}
	
	@Test
	public void testAdjacency() {
		Position posOther = new Position(2,4);
		Position posLeft = new Position(1,4);
		Position posRight = new Position(3,4);
		Position posAbove = new Position(2,5);
		Position posBelow = new Position(2,3);
		
		assertTrue("posLeft is left of posOther", posLeft.isAdjacentOf(posOther, Direction.LEFT));
		assertTrue("posRight is right of posOther", posRight.isAdjacentOf(posOther, Direction.RIGHT));
		assertTrue("posAbove is above of posOther", posAbove.isAdjacentOf(posOther, Direction.UP));
		assertTrue("posBelow is below of posOther", posBelow.isAdjacentOf(posOther, Direction.DOWN));
	}
}
