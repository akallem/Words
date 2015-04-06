import static org.junit.Assert.*;

import org.junit.Test;


public class TestINode {
	// Some generic variables useful for all tests
	WordsEnvironment environment = new WordsEnvironment();
	FrameLoop loop = new FrameLoop(environment);

	AST nothingLeaf = new LNode(AST.ASTType.NOTHING, 0);
	AST numLeaf = new LNode(AST.ASTType.NUM, 0, 0.0);
	AST twoLeaf = new LNode(AST.ASTType.NUM, 0, 2.0);
	AST fiveLeaf = new LNode(AST.ASTType.NUM, 0, 5.0);
	AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "string");
	
	AST fredStringLeaf = new LNode(AST.ASTType.STRING, 0, "Fred");
	AST leftDirectionLeaf = new LNode(AST.ASTType.DIRECTION, 0, Direction.Type.LEFT);
	AST rightDirectionLeaf = new LNode(AST.ASTType.DIRECTION, 0, Direction.Type.RIGHT);
	
	AST moveFredLeft2 = new INode(AST.ASTType.QUEUE_MOVE, 0, nothingLeaf, fredStringLeaf, leftDirectionLeaf, twoLeaf, null);
	AST moveFredRight2 = new INode(AST.ASTType.QUEUE_MOVE, 0, nothingLeaf, fredStringLeaf, rightDirectionLeaf, twoLeaf, null);

	AST trueLeaf = new INode(AST.ASTType.EQUALS, 0, nothingLeaf, nothingLeaf);
	AST falseLeaf = new INode(AST.ASTType.EQUALS, 0, nothingLeaf, numLeaf);
	
	/*********************
	 * evalAnd
	 ********************/	
	@Test
	public void logicalAndShouldGiveCorrectResults() throws WordsRuntimeException {
		INode testNode;
		ASTValue result;
		
		testNode = new INode(AST.ASTType.AND, 0, trueLeaf, trueLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
		
		testNode = new INode(AST.ASTType.AND, 0, falseLeaf, trueLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
		
		testNode = new INode(AST.ASTType.AND, 0, trueLeaf, falseLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
		
		testNode = new INode(AST.ASTType.AND, 0, falseLeaf, falseLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	/*********************
	 * evalEquals 
	 ********************/	
	@Test
	public void nothingShouldEqualNothing() throws WordsRuntimeException {
		AST nothingLeaf1 = new LNode(AST.ASTType.NOTHING, 0);
		AST nothingLeaf2 = new LNode(AST.ASTType.NOTHING, 0);
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, nothingLeaf1, nothingLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void nothingShouldNotEqualAnythingElse() throws WordsRuntimeException {
		INode testNode;
		ASTValue result;
		
		testNode = new INode(AST.ASTType.EQUALS, 0, nothingLeaf, numLeaf);
		result = testNode.eval(environment);
		assertFalse("Result is false", result.booleanValue);
		
		testNode = new INode(AST.ASTType.EQUALS, 0, nothingLeaf, stringLeaf);
		result = testNode.eval(environment);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void numberShouldEqualItself() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}

	@Test
	public void numberShouldNotEqualAnother() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 4.7);
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void stringShouldEqualItself() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "test");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "test");
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, stringLeaf1, stringLeaf1);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}

	@Test
	public void stringShouldNotEqualAnother() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "this");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "that");
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void numberShouldEqualEquivalentString() throws WordsRuntimeException {
		AST numLeaf = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "1.4");
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void numberShouldNotEqualNonequivalentString() throws WordsRuntimeException {
		AST numLeaf = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "4.7");
		
		INode testNode = new INode(AST.ASTType.EQUALS, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	/*********************
	 * evalGEQ
	 ********************/
	@Test
	public void numberShouldBeGEQItself() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.GEQ, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void numberShouldBeGEQAnother() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 4.7);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.GEQ, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void stringShouldBeGEQItself() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "string");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "string");
		
		INode testNode = new INode(AST.ASTType.GEQ, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void stringShouldBeGEQAnother() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "fghij");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "abcde");
		
		INode testNode = new INode(AST.ASTType.GEQ, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test (expected = WordsOperatorTypeMismatchException.class)
	public void numberAndStringsShouldNotBeGEQ() throws WordsRuntimeException {
		INode testNode = new INode(AST.ASTType.GEQ, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
	}
	
	/*********************
	 * evalGreater
	 ********************/
	@Test
	public void numberShouldNotBeGreaterItself() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.GREATER, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void numberShouldBeGreaterAnother() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 4.7);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.GREATER, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void stringShouldNotBeGreaterItself() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "string");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "string");
		
		INode testNode = new INode(AST.ASTType.GREATER, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void stringShouldBeGreaterAnother() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "fghij");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "abcde");
		
		INode testNode = new INode(AST.ASTType.GREATER, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test (expected = WordsOperatorTypeMismatchException.class)
	public void numberAndStringsShouldNotBeGreater() throws WordsRuntimeException {
		INode testNode = new INode(AST.ASTType.GREATER, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
	}

	/*********************
	 * evalLEQ
	 ********************/
	@Test
	public void numberShouldBeLEQItself() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.LEQ, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void numberShouldBeLEQAnother() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 4.7);
		
		INode testNode = new INode(AST.ASTType.LEQ, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void stringShouldBeLEQItself() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "string");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "string");
		
		INode testNode = new INode(AST.ASTType.LEQ, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void stringShouldBeLEQAnother() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "abcde");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "fghij");
		
		INode testNode = new INode(AST.ASTType.LEQ, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test (expected = WordsOperatorTypeMismatchException.class)
	public void numberAndStringsShouldNotBeLEQ() throws WordsRuntimeException {
		INode testNode = new INode(AST.ASTType.LEQ, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
	}
	
	/*********************
	 * evalLesser
	 ********************/
	@Test
	public void numberShouldNotBeLesserItself() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode testNode = new INode(AST.ASTType.LESS, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void numberShouldBeLesserAnother() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 4.7);
		
		INode testNode = new INode(AST.ASTType.LESS, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void stringShouldNotBeLesserItself() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "string");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "string");
		
		INode testNode = new INode(AST.ASTType.LESS, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void stringShouldBeLesserAnother() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "abcde");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "fghij");
		
		INode testNode = new INode(AST.ASTType.LESS, 0, stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test (expected = WordsOperatorTypeMismatchException.class)
	public void numberAndStringsShouldNotBeLesser() throws WordsRuntimeException {
		INode testNode = new INode(AST.ASTType.LESS, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
	}

	/*********************
	 * evalNot
	 ********************/	
	@Test
	public void logicalNotShouldGiveCorrectResults() throws WordsRuntimeException {
		INode testNode;
		ASTValue result;
		
		testNode = new INode(AST.ASTType.NOT, 0, trueLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
		
		testNode = new INode(AST.ASTType.NOT, 0, falseLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	/*********************
	 * evalOr
	 ********************/	
	@Test
	public void logicalOrShouldGiveCorrectResults() throws WordsRuntimeException {
		INode testNode;
		ASTValue result;
		
		testNode = new INode(AST.ASTType.OR, 0, trueLeaf, trueLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
		
		testNode = new INode(AST.ASTType.OR, 0, falseLeaf, trueLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
		
		testNode = new INode(AST.ASTType.OR, 0, trueLeaf, falseLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
		
		testNode = new INode(AST.ASTType.OR, 0, falseLeaf, falseLeaf);
		result = testNode.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	/*********************
	 * evalPosition 
	 ********************/	
	@Test
	public void positionWithNothingWrong() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 0.0);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 2.1);
		
		INode testNode = new INode(AST.ASTType.POSITION, 0, numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a position", result.type, ASTValue.ValueType.POSITION);
		assertEquals("The position is the right position", result.positionValue, new WordsPosition(0,2));
	}
	
	@Test
	public void positionWithCoercableString() throws WordsRuntimeException {
		AST numLeaf = new LNode(AST.ASTType.NUM, 0, 0.0);
		AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "2.1");
		
		INode testNode = new INode(AST.ASTType.POSITION, 0, numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
		assertEquals("Creates a position", result.type, ASTValue.ValueType.POSITION);
		assertEquals("The position is the right position", result.positionValue, new WordsPosition(0,2));
	}
	
	@Test (expected = WordsInvalidTypeException.class)
	public void positionWithNonCoercableString() throws WordsRuntimeException {
		AST numLeaf = new LNode(AST.ASTType.NUM, 0, 0.0);
		AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "ham");
		
		INode testNode = new INode(AST.ASTType.POSITION, 0, numLeaf, stringLeaf);
		testNode.eval(environment);
	}
	
	/*********************
	 * evalQueueMove
	 ********************/
	@Test
	public void testWorkingMove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		assertEquals("Fred in good start position", environment.getObject("Fred").getCurrentCell(), new WordsPosition(0,0));
		loop.enqueueAST(moveFredLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentCell(), new WordsPosition(-1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentCell(), new WordsPosition(-2,0));
	}
	
	/*********************
	 * evalIf
	 ********************/
	@Test
	public void testIfOnTrue() throws WordsRuntimeException {
		AST statementList = new INode(AST.ASTType.STATEMENT_LIST, 0, moveFredRight2);
		AST relationalExp = new INode(AST.ASTType.EQUALS, 0, numLeaf, numLeaf);
		AST testNode = new INode(AST.ASTType.IF, 0, relationalExp, statementList);
		
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(testNode);
		loop.fastForwardEnvironment(2);
		assertEquals("Fred has moved correctly", environment.getObject("Fred").getCurrentCell(), new WordsPosition(2,0));
	}
	
	@Test
	public void testIfOnFalse() throws WordsRuntimeException {
		AST statementList = new INode(AST.ASTType.STATEMENT_LIST, 0, moveFredRight2);
		AST relationalExp = new INode(AST.ASTType.EQUALS, 0, twoLeaf, fiveLeaf);
		AST testNode = new INode(AST.ASTType.IF, 0, relationalExp, statementList);
		
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //object is created with a 1 frame wait, so use it up. 
		loop.enqueueAST(testNode);
		loop.fastForwardEnvironment(2);
		assertEquals("Fred has not moved", environment.getObject("Fred").getCurrentCell(), new WordsPosition(0,0));
	}
	
	/*********************
	 * evalIteration
	 ********************/
	@Test
	public void testWorkingIteration() throws WordsRuntimeException {
		AST statementList = new INode(AST.ASTType.STATEMENT_LIST, 0, moveFredLeft2, moveFredRight2);
		AST iterativeLoop = new INode(AST.ASTType.REPEAT, 0, fiveLeaf, statementList);
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
	public void testBadIteration() throws WordsRuntimeException {
		AST statementList = new INode(AST.ASTType.STATEMENT_LIST, 0, moveFredLeft2, moveFredRight2);
		AST iterativeLoop = new INode(AST.ASTType.REPEAT, 0, stringLeaf, statementList);
		iterativeLoop.eval(environment);
	}
	
}
