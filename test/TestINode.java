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
	
	AST moveLeft2 = new INode(AST.ASTType.QUEUE_MOVE, 0, nothingLeaf, fredStringLeaf, leftDirectionLeaf, twoLeaf, null);
	AST moveRight2 = new INode(AST.ASTType.QUEUE_MOVE, 0, nothingLeaf, fredStringLeaf, rightDirectionLeaf, twoLeaf, null);
		
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
		loop.enqueueAST(moveLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentCell(), new WordsPosition(-1,0));
		loop.fastForwardEnvironment(1);
		assertEquals("Fred moved", environment.getObject("Fred").getCurrentCell(), new WordsPosition(-2,0));
	}
	
	/*********************
	 * evalIteration
	 ********************/
	@Test
	public void testWorkingIteration() throws WordsRuntimeException {
		AST statementList = new INode(AST.ASTType.STATEMENT_LIST, 0, moveLeft2, moveRight2);
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
		AST statementList = new INode(AST.ASTType.STATEMENT_LIST, 0, moveLeft2, moveRight2);
		AST iterativeLoop = new INode(AST.ASTType.REPEAT, 0, stringLeaf, statementList);
		iterativeLoop.eval(environment);
	}
	
}
