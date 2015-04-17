package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeWaitsPredicate extends TestINode {

	@Test
	public void knowsIfAnObjectWaited() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		AST idLeaf = new LNodeIdentifier("Fred");
		AST numLeaf = new LNodeNum(2);
		INode waitNode = new INodeQueueWait(new INodeReferenceList(), idLeaf, numLeaf, null);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(waitNode);
		loop.enqueueAST(moveFredLeft2);
		
		INodeWaitsPredicate thingWaited = new INodeWaitsPredicate(new LNodeIdentifier("thing"), new LNodeIdentifier("alias"));
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred waits
		assertEquals("Evals true when Fred waits", thingWaited.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred waits
		assertEquals("Evals true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, false);
	}
	
	@Test
	public void usesInheritence() throws WordsRuntimeException {
		environment.createClass("child", "thing");
		environment.createObject("Fred", "child", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		AST idLeaf = new LNodeIdentifier("Fred");
		AST numLeaf = new LNodeNum(2);
		INode waitNode = new INodeQueueWait(new INodeReferenceList(), idLeaf, numLeaf, null);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(waitNode);
		loop.enqueueAST(moveFredLeft2);
		
		INodeWaitsPredicate thingWaited = new INodeWaitsPredicate(new LNodeIdentifier("thing"), new LNodeIdentifier("alias"));
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred waits
		assertEquals("Evals true when Fred waits", thingWaited.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred waits
		assertEquals("Evals true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingWaited.eval(environment, statementsAboutFred).booleanValue, false);
	}
}
