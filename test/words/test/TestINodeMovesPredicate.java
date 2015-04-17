package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.INodeMovesPredicate;
import words.ast.INodeReferenceList;
import words.ast.INodeSubject;
import words.ast.LNodeIdentifier;
import words.environment.Position;
import words.exceptions.WordsRuntimeException;

public class TestINodeMovesPredicate extends TestINode {

	@Test
	public void knowsIfAnObjectMoved() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		
		INodeMovesPredicate thingMoved = new INodeMovesPredicate(new LNodeIdentifier("thing"), new LNodeIdentifier("alias"), null);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", thingMoved.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
	}
	
	@Test
	public void knowsIfAnObjectMovedInADirection() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredRight2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		
		INodeMovesPredicate thingMovedRight = new INodeMovesPredicate(new INodeSubject(new LNodeIdentifier("thing"), null, null), new LNodeIdentifier("alias"), rightDirectionLeaf);
		INodeMovesPredicate thingMovedLeft = new INodeMovesPredicate(new INodeSubject(new LNodeIdentifier("thing"), null, null), new LNodeIdentifier("alias"), leftDirectionLeaf);
		loop.fastForwardEnvironment(1); // Fred moves right
		assertEquals("Left move evals false when thing moves right", thingMovedLeft.eval(environment, statementsAboutFred).booleanValue, false);
		assertEquals("Right move pred evals true when thing moves right", thingMovedRight.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves right
		assertEquals("Left move evals false when thing moves right", thingMovedLeft.eval(environment, statementsAboutFred).booleanValue, false);
		assertEquals("Right move pred evals true when thing moves right", thingMovedRight.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Right move pred evals false when thing says a string", thingMovedRight.eval(environment, statementsAboutFred).booleanValue, false);
		assertEquals("Left move pred evals false when thing says a string", thingMovedLeft.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves left
		assertEquals("Left move pred evals true when thing moves left", thingMovedLeft.eval(environment, statementsAboutFred).booleanValue, true);
		assertEquals("Right move pred evals false when thing moves left", thingMovedRight.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves left
		assertEquals("Left move pred evals true when thing moves left", thingMovedLeft.eval(environment, statementsAboutFred).booleanValue, true);
		assertEquals("Right move pred evals false when thing moves left", thingMovedRight.eval(environment, statementsAboutFred).booleanValue, false);
	}
	
	@Test
	public void worksOnSingleObject() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredRight2);
		loop.enqueueAST(moveGeorgeRight2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(moveGeorgeLeft2);
		
		INodeMovesPredicate fredMovedRight = new INodeMovesPredicate(new INodeSubject(null, new INodeReferenceList(), fredStringLeaf), new LNodeIdentifier("alias"), rightDirectionLeaf);
		loop.fastForwardEnvironment(1); // Fred moves right
		assertEquals("Right move pred evals true when thing moves right", fredMovedRight.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves right
		assertEquals("Right move pred evals true when thing moves right", fredMovedRight.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Right move pred evals false when thing says a string", fredMovedRight.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves left
		assertEquals("Right move pred evals false when thing moves left", fredMovedRight.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves left
		assertEquals("Right move pred evals false when thing moves left", fredMovedRight.eval(environment, statementsAboutFred).booleanValue, false);
	}
	
	@Test
	public void usesInheritence() throws WordsRuntimeException {
		environment.createClass("child", "thing");
		environment.createObject("Fred", "child", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		
		INodeMovesPredicate thingMoved = new INodeMovesPredicate(new LNodeIdentifier("thing"), new LNodeIdentifier("alias"), null);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", thingMoved.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Evals true when thing moves", thingMoved.eval(environment, statementsAboutFred).booleanValue, true);
	}

}
