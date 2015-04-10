package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.exceptions.*;

public class TestINodeDivide extends TestINode {
	@Test
	public void twoNumbersDivideCorrectly() throws WordsRuntimeException {
		AST numLeaf1 = new LNodeNum(1);
		AST numLeaf2 = new LNodeNum(2);
		
		INode testNode = new INodeDivide(numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertTrue("Returns NUM value", result.type == ASTValue.ValueType.NUM);
		assertEquals("Correct Division", result.numValue, 0.5, 0.0001);
	}
	
	@Test (expected = WordsArithmeticException.class)
	public void onlyOperatesOnTwoNumbers() throws WordsRuntimeException {
		INode testNode = new INodeDivide(numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
	}

	@Test (expected = WordsDivideByZeroException.class)
	public void cannotDivideByZero() throws WordsRuntimeException {
		INode testNode = new INodeDivide(twoLeaf, numLeaf);
		ASTValue result = testNode.eval(environment);
	}
}