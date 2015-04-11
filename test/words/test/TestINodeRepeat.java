package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.WordsPosition;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsObjectAlreadyExistsException;
import words.exceptions.WordsRuntimeException;


public class TestINodeRepeat extends TestINode {
	@Test
	public void testWorkingRepeat() throws WordsRuntimeException {
		AST statementList = new INodeStatementList(moveFredLeft2, moveFredRight2);
		AST iterativeLoop = new INodeRepeat(fiveLeaf, statementList);
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(iterativeLoop);
		assertEquals("Fred in good start position", environment.getObject("Fred").getCurrentCell(), new WordsPosition(0,0));
		loop.fastForwardEnvironment(2);
		assertEquals("Fred makes first move", environment.getObject("Fred").getCurrentCell(), new WordsPosition(-2,0));
		loop.fastForwardEnvironment(2);
		assertEquals("Fred returns from move", environment.getObject("Fred").getCurrentCell(), new WordsPosition(0,0));
		loop.fastForwardEnvironment(20);
		assertEquals("Fred ends in correct place", environment.getObject("Fred").getCurrentCell(), new WordsPosition(0,0));
	}
	
	@Test (expected = WordsInvalidTypeException.class)
	public void testBadRepeat() throws WordsRuntimeException {
		AST statementList = new INodeStatementList(moveFredLeft2, moveFredRight2);
		AST iterativeLoop = new INodeRepeat(stringLeaf, statementList);
		iterativeLoop.eval(environment);
	}
	
	@Test
	public void canRepeatObjectCreation() {
		AST statementList = new INodeStatementList(createObjectFred);
		AST iterativeLoop = new INodeRepeat(fiveLeaf, statementList);
		loop.enqueueAST(iterativeLoop);
		loop.fastForwardEnvironment(5);
		assertEquals("Successfully created multiple Freds", environment.getObjects().size(), 5);
		assertEquals("Outside of loop, ends in global scope", environment.getNumberOfScopes(), 1);
	}
	
	@Test
	public void scopeHandlesExceptions() throws WordsRuntimeException {
		AST statementList = new INodeStatementList(createObjectFred);
		AST iterativeLoop = new INodeRepeat(fiveLeaf, statementList);
		loop.enqueueAST(createObjectFred);
		loop.fastForwardEnvironment(1);
		// this iterative loop will fail 5 times
		loop.enqueueAST(iterativeLoop);
		loop.fastForwardEnvironment(2);
		assertEquals("Outside of loop, ends in global scope", environment.getNumberOfScopes(), 1);
	}
	
	@Test
	public void scopeHandlesMoreExceptions() throws WordsRuntimeException {
		AST statementList = new INodeStatementList(createObjectFred);
		AST iterativeLoop = new INodeRepeat(stringLeaf, statementList);
		loop.fastForwardEnvironment(1);
		loop.enqueueAST(iterativeLoop);
		loop.fastForwardEnvironment(2);
		assertEquals("Outside of loop, ends in global scope", environment.getNumberOfScopes(), 1);
	}
}
