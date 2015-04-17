package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.exceptions.*;

public class TestINodeExponentiate extends TestINode {
	@Test
	public void twoNumbersExponentiateCorrectly() throws WordsRuntimeException {
		AST numLeaf1 = new LNodeNum(4);
		AST numLeaf2 = new LNodeNum(3);
		
		INode testNode = new INodeExponentiate(numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertTrue("Returns NUM value", result.type == ASTValue.Type.NUM);
		assertEquals("Correct Exponentiation", result.numValue, 64.0, 0.0001);
	}
	
	@Test (expected = WordsArithmeticException.class)
	public void onlyOperatesOnTwoNumbers() throws WordsRuntimeException {
		INode testNode = new INodeExponentiate(numLeaf, stringLeaf);
		ASTValue result = testNode.eval(environment);
	}
}