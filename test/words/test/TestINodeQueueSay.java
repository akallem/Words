package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeQueueSay extends TestINode {
	@Test
	public void testWorkingSay() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0, 0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		assertEquals("Initially no message", environment.getObject("Fred").getCurrentMessage(), null);

		AST idLeaf = new LNodeIdentifier("Fred");
		AST strLeaf = new LNodeString("Hello World");
		INode testNode = new INodeQueueSay(new INodeReferenceList(), idLeaf, strLeaf, null);
		testNode.eval(environment);
		loop.fastForwardEnvironment(1);
		assertEquals("New message assigned", environment.getObject("Fred").getCurrentMessage(), "Hello World");
	}

	@Test
	public void testSayWithReferenceList() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new Position(0, 0));
		LNodeReference alexRef = new LNodeReference("Alex's");

		WordsObject bobObject = environment.createObject("Bob", "thing", new Position(0, 0));
		alexObject.setProperty("friend", new Property(bobObject));
		LNodeReference friendRef = new LNodeReference("friend's");

		WordsObject chrisObject = environment.createObject("Chris", "thing", new Position(0, 0));
		bobObject.setProperty("enemy", new Property(chrisObject));

		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		assertEquals("Initially no message", environment.getObject("Chris").getCurrentMessage(), null);

		AST refLeaf = new INodeReferenceList(alexRef, friendRef);
		AST idLeaf = new LNodeIdentifier("enemy");
		AST strLeaf = new LNodeString("Hello World");
		INode testNode = new INodeQueueSay(refLeaf, idLeaf, strLeaf, null);
		testNode.eval(environment);
		loop.fastForwardEnvironment(1);
		assertEquals("New message assigned", environment.getObject("Chris").getCurrentMessage(), "Hello World");
	}

	@Test
	public void testSayWithNow() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0, 0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		assertEquals("Initially no message", environment.getObject("Fred").getCurrentMessage(), null);

		AST idLeaf = new LNodeIdentifier("Fred");
		AST strLeaf1 = new LNodeString("First message");
		AST strLeaf2 = new LNodeString("Second message");
		AST strLeaf3 = new LNodeString("Third message");
		INode testNode1 = new INodeQueueSay(new INodeReferenceList(), idLeaf, strLeaf1, null);
		INode testNode2 = new INodeQueueSay(new INodeReferenceList(), idLeaf, strLeaf2, null);
		INode testNode3 = new INodeQueueSay(new INodeReferenceList(), idLeaf, strLeaf3, new LNodeNow());
		testNode1.eval(environment);
		testNode2.eval(environment);
		testNode3.eval(environment);
		loop.fastForwardEnvironment(1);
		assertEquals("Immediate message", environment.getObject("Fred").getCurrentMessage(), "Third message");
		loop.fastForwardEnvironment(1);
		assertEquals("Queued message", environment.getObject("Fred").getCurrentMessage(), "First message");
		loop.fastForwardEnvironment(1);
		assertEquals("Second queued message", environment.getObject("Fred").getCurrentMessage(), "Second message");
	}

	@Test
	public void testSayWithExpression() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0, 0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		assertEquals("Initially no message", environment.getObject("Fred").getCurrentMessage(), null);

		AST idLeaf = new LNodeIdentifier("Fred");
		AST numLeaf1 = new LNodeNum(3);
		AST numLeaf2 = new LNodeNum(4);
		AST expressionNode = new INodeMultiply(numLeaf1, numLeaf2);
		INode testNode = new INodeQueueSay(new INodeReferenceList(), idLeaf, expressionNode, null);
		testNode.eval(environment);
		loop.fastForwardEnvironment(1);
		assertEquals("New message assigned", Double.parseDouble(environment.getObject("Fred").getCurrentMessage()), 12.0, 0.0001);
	}

}
