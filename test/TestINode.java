import static org.junit.Assert.*;

import org.junit.Test;

public class TestINode {
	// Some generic variables useful for all tests
	WordsEnvironment environment = new WordsEnvironment();

	AST nothingLeaf = new LNode(AST.ASTType.NOTHING, 0);
	AST numLeaf = new LNode(AST.ASTType.NUM, 0, 0.0);
	AST stringLeaf = new LNode(AST.ASTType.NUM, 0, "string");
		
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
