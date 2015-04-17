package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.INodeSaysPredicate;
import words.ast.LNodeIdentifier;
import words.ast.LNodeString;
import words.environment.WordsPosition;
import words.exceptions.WordsRuntimeException;

public class TestINodeSaysPredicate extends TestINode {

	@Test
	public void knowsIfAnObjectSaidSomething() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayString);
		loop.enqueueAST(moveFredLeft2);
		
		INodeSaysPredicate thingSaidHelloWorld = new INodeSaysPredicate(new LNodeIdentifier("thing"), new LNodeIdentifier("alias"), new LNodeString("Hello World"));
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", thingSaidHelloWorld.eval(environment).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says a string not hello world
		assertEquals("Doesn't eval true when Fred says the wrong thing", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
	}
	
	@Test
	public void usesInheritence() throws WordsRuntimeException {
		environment.createClass("child", "thing");
		environment.createObject("Fred", "child", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayString);
		loop.enqueueAST(moveFredLeft2);
		
		INodeSaysPredicate thingSaidHelloWorld = new INodeSaysPredicate(new LNodeIdentifier("thing"), new LNodeIdentifier("alias"), new LNodeString("Hello World"));
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", thingSaidHelloWorld.eval(environment).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says a string not hello world
		assertEquals("Doesn't eval true when Fred says the wrong thing", thingSaidHelloWorld.eval(environment).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment).booleanValue, false);
	}
}
