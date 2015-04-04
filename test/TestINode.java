import static org.junit.Assert.*;

import org.junit.Test;

public class TestINode {
	// Some generic variables useful for all tests
	WordsEnvironment environment = new WordsEnvironment();

	AST nothingLeaf = new LNode(AST.ASTType.NOTHING, 0);
	AST numLeaf = new LNode(AST.ASTType.NUM, 0, 0.0);
	AST stringLeaf = new LNode(AST.ASTType.NUM, 0, "string");
			
	/*********************
	 * evalEquals 
	 ********************/	
	@Test
	public void nothingShouldEqualNothing() throws WordsRuntimeException {
		AST nothingLeaf1 = new LNode(AST.ASTType.NOTHING, 0);
		AST nothingLeaf2 = new LNode(AST.ASTType.NOTHING, 0);
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, nothingLeaf1, nothingLeaf2);
		ASTValue result = equalsEval.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void nothingShouldNotEqualAnythingElse() throws WordsRuntimeException {
		INode equalsEval;
		ASTValue result;
		
		equalsEval = new INode(AST.ASTType.EQUALS, 0, nothingLeaf, numLeaf);
		result = equalsEval.eval(environment);
		assertFalse("Result is false", result.booleanValue);
		
		equalsEval = new INode(AST.ASTType.EQUALS, 0, nothingLeaf, stringLeaf);
		result = equalsEval.eval(environment);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void numberShouldEqualItself() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 1.4);
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, numLeaf1, numLeaf2);
		ASTValue result = equalsEval.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}

	@Test
	public void numberShouldNotEqualAnother() throws WordsRuntimeException {
		AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 4.7);
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, numLeaf1, numLeaf2);
		ASTValue result = equalsEval.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void stringShouldEqualItself() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "test");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "test");
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, stringLeaf1, stringLeaf1);
		ASTValue result = equalsEval.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}

	@Test
	public void stringShouldNotEqualAnother() throws WordsRuntimeException {
		AST stringLeaf1 = new LNode(AST.ASTType.STRING, 0, "this");
		AST stringLeaf2 = new LNode(AST.ASTType.STRING, 0, "that");
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, stringLeaf1, stringLeaf2);
		ASTValue result = equalsEval.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertFalse("Result is false", result.booleanValue);
	}
	
	@Test
	public void numberShouldEqualEquivalentString() throws WordsRuntimeException {
		AST numLeaf = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "1.4");
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, numLeaf, stringLeaf);
		ASTValue result = equalsEval.eval(environment);
		assertEquals("Creates a boolean", result.type, ASTValue.ValueType.BOOLEAN);
		assertTrue("Result is true", result.booleanValue);
	}
	
	@Test
	public void numberShouldNotEqualNonequivalentString() throws WordsRuntimeException {
		AST numLeaf = new LNode(AST.ASTType.NUM, 0, 1.4);
		AST stringLeaf = new LNode(AST.ASTType.STRING, 0, "4.7");
		
		INode equalsEval = new INode(AST.ASTType.EQUALS, 0, numLeaf, stringLeaf);
		ASTValue result = equalsEval.eval(environment);
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
}
