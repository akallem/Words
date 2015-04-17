package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.INodeAlias;
import words.ast.INodeReferenceList;
import words.ast.INodeSaysPredicate;
import words.ast.INodeSubject;
import words.ast.LNodeIdentifier;
import words.ast.LNodeString;
import words.environment.Position;
import words.exceptions.WordsRuntimeException;

public class TestINodeSaysPredicate extends TestINode {

	@Test
	public void knowsIfAnObjectSaidSomething() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayString);
		loop.enqueueAST(moveFredLeft2);
		
		INodeSaysPredicate thingSaidHelloWorld = new INodeSaysPredicate(new INodeSubject(new LNodeIdentifier("thing"), null, null), new INodeAlias(new LNodeIdentifier("alias")), new LNodeString("Hello World"));
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says a string not hello world
		assertEquals("Doesn't eval true when Fred says the wrong thing", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
	}
	
	@Test
	public void worksOnSingleObject() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		environment.createObject("George", "thing", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(makeGeorgeSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayString);
		loop.enqueueAST(moveFredLeft2);
		
		INodeSaysPredicate fredSaidHelloWorld = new INodeSaysPredicate(new INodeSubject(null, new INodeReferenceList(), fredStringLeaf), new INodeAlias(new LNodeIdentifier("alias")), new LNodeString("Hello World"));
		loop.fastForwardEnvironment(1); // Fred moves, George says "Hello World"
		assertEquals("Doesn't eval true when Fred moves", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says a string not hello world
		assertEquals("Doesn't eval true when Fred says the wrong thing", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", fredSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
	}
	
	@Test
	public void usesInheritence() throws WordsRuntimeException {
		environment.createClass("child", "thing");
		environment.createObject("Fred", "child", new Position(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up.
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayHelloWorld);
		loop.enqueueAST(moveFredLeft2);
		loop.enqueueAST(makeFredSayString);
		loop.enqueueAST(moveFredLeft2);
		
		INodeSaysPredicate thingSaidHelloWorld = new INodeSaysPredicate(new INodeSubject(new LNodeIdentifier("thing"), null, null), new LNodeIdentifier("alias"), new LNodeString("Hello World"));
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says hello world
		assertEquals("Evals true when Fred says the string", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, true);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred says a string not hello world
		assertEquals("Doesn't eval true when Fred says the wrong thing", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
		loop.fastForwardEnvironment(1); // Fred moves
		assertEquals("Doesn't eval true when Fred moves", thingSaidHelloWorld.eval(environment, statementsAboutFred).booleanValue, false);
	}
}
