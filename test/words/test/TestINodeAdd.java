package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.exceptions.*;

public class TestINodeAdd extends TestINode {
	@Test
	public void twoNumbersAddCorrectly() throws WordsRuntimeException {
		AST numLeaf1 = new LNodeNum(4);
		AST numLeaf2 = new LNodeNum(3);
		
		INode testNode = new INodeAdd(numLeaf1, numLeaf2);
		ASTValue result = testNode.eval(environment);
		assertTrue("Returns NUM value", result.type == ASTValue.ValueType.NUM);
		assertEquals("Correct Addition", result.numValue, 7.0, 0.0001);
	}
	
	@Test
	public void numbersInStringFormAreTreatedAsNumbers() throws WordsRuntimeException {
		AST numLeaf1 = new LNodeNum(6);
		AST stringLeaf1 = new LNodeString("7");
		
		INode testNode = new INodeAdd(numLeaf1, stringLeaf1);
		ASTValue result = testNode.eval(environment);
		assertTrue("Returns NUM value", result.type == ASTValue.ValueType.NUM);
		assertEquals("Correct Addition", result.numValue, 13.0, 0.0001);
	}

	@Test
	public void stringConcatenation() throws WordsRuntimeException {
		AST stringLeaf1 = new LNodeString("a");
		AST stringLeaf2 = new LNodeString("b");
		
		INode testNode = new INodeAdd(stringLeaf1, stringLeaf2);
		ASTValue result = testNode.eval(environment);
		assertTrue("Returns STRING value", result.type == ASTValue.ValueType.STRING);
		assertEquals("Correct String Concatenation", result.stringValue, "ab");
	}
}
