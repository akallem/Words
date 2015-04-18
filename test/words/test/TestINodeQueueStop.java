package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;


public class TestINodeQueueStop extends TestINode {
	@Test
	public void testWorkingStop() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		assertEquals("Fred in good start position", environment.getObject("Fred").getCurrentPosition(), new Position(0,0));
		environment.getObject("Fred").enqueueAction(new MoveAction(new Direction(Direction.Type.RIGHT), new LNodeNum(1)));
		environment.getObject("Fred").clearQueue();
		loop.fastForwardEnvironment(1);
		assertEquals("Fred never moved", environment.getObject("Fred").getCurrentPosition(), new Position(0,0));
	}
}