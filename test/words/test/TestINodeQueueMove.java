package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;


public class TestINodeQueueMove extends TestINode {
	@Test
	public void testWorkingMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		assertEquals("Fred in good start position", environment.getObject("Fred").getCurrentPosition(), new Position(0,0));
		loop.enqueueAST(moveFredLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(-1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(-2,0));
	}
	
	@Test
	public void testMoveWithReferenceList() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new Position(0, 0));
		LNodeReference alexRef = new LNodeReference("Alex's");

		WordsObject bobObject = environment.createObject("Bob", "thing", new Position(0, 0));
		alexObject.setProperty("friend", new Property(bobObject));
		LNodeReference friendRef = new LNodeReference("friend's");

		WordsObject chrisObject = environment.createObject("Chris", "thing", new Position(0, 0));
		bobObject.setProperty("enemy", new Property(chrisObject));

		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		assertEquals("Chris at starting position", environment.getObject("Chris").getCurrentPosition(), new Position(0, 0));

		AST refLeaf = new INodeReferenceList(alexRef, friendRef);
		AST idLeaf = new LNodeIdentifier("enemy");
		INode testNode = new INodeQueueMove(refLeaf, idLeaf, rightDirectionLeaf, twoLeaf, null);
		testNode.eval(environment);
		loop.fastForwardEnvironment(1);
		loop.fastForwardEnvironment(2);
		assertEquals("Chris at correct position", environment.getObject("Chris").getCurrentPosition(), new Position(2, 0));		
	}
	
	@Test
	public void testMoveWithNow() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		assertEquals("Fred in good start position", environment.getObject("Fred").getCurrentPosition(), new Position(0,0));

		moveFredLeft2.eval(environment);
		AST moveFredRight2 = new INodeQueueMove(nothingLeaf, fredStringLeaf, rightDirectionLeaf, twoLeaf, new LNodeNow());
		moveFredRight2.eval(environment);
		
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(2,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(0,0));
	}

	@Test
	public void negativeDistanceMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(moveFredLeftNegative2); // Fred should move right.
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentPosition(), new Position(2,0));
	}

	@Test
	public void anywhereDirectionMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(moveFredAnywhere2); 
		loop.fastForwardEnvironment(2);
		Position fredCurrent = environment.getObject("Fred").getCurrentPosition();
		assertTrue(fredCurrent.equals(new Position(0, 2))
				|| fredCurrent.equals(new Position(2, 0))
				|| fredCurrent.equals(new Position(0, -2))
				|| fredCurrent.equals(new Position(-2, 0)));
	}
}
