package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.environment.WordsPosition;
import words.exceptions.WordsRuntimeException;


public class TestINodeQueueMove extends TestINode {
	@Test
	public void testWorkingMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		assertEquals("Fred in good start position", environment.getObject("Fred").getCurrentPosition(), new WordsPosition(0,0));
		loop.enqueueAST(moveFredLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new WordsPosition(-1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new WordsPosition(-2,0));
	}

	@Test
	public void negativeDistanceMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(moveFredLeftNegative2); // Fred should move right.
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new WordsPosition(1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new WordsPosition(2,0));
	}

	@Test
	public void anywhereDirectionMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(moveFredAnywhere2); 
		loop.fastForwardEnvironment(2);
		WordsPosition fredCurrent = environment.getObject("Fred").getCurrentPosition();
		assertTrue(fredCurrent.equals(new WordsPosition(0, 2))
				|| fredCurrent.equals(new WordsPosition(2, 0))
				|| fredCurrent.equals(new WordsPosition(0, -2))
				|| fredCurrent.equals(new WordsPosition(-2, 0)));
	}
}
