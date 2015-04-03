import static org.junit.Assert.*;

import org.junit.Test;

public class TestINode {
	
	WordsEnvironment environment = new WordsEnvironment();

	/*********************
	 * EvalPosition 
	 ********************/
	AST numLeaf1 = new LNode(AST.ASTType.NUM, 0, 0.0);
	AST numLeaf2 = new LNode(AST.ASTType.NUM, 0, 2.1);
	AST stringLeafCoercable = new LNode(AST.ASTType.STRING, 0, "2.1");
	AST stringLeafNonCoercable = new LNode(AST.ASTType.STRING, 0, "ham");
	
	@Test
	public void positionWithNothingWrong() throws WordsRuntimeException {
		INode positionEval = new INode(AST.ASTType.POSITION, 0, numLeaf1, numLeaf2);
		AST.ASTValue result = positionEval.eval(environment);
		assertEquals("Creates a position", result.type, AST.ValueType.POSITION);
		assertEquals("The position is the right position", result.positionValue, new WordsPosition(0,2));
	}
	
	@Test
	public void positionWithCoercableString() throws WordsRuntimeException {
		INode positionEval = new INode(AST.ASTType.POSITION, 0, numLeaf1, stringLeafCoercable);
		AST.ASTValue result = positionEval.eval(environment);
		assertEquals("Creates a position", result.type, AST.ValueType.POSITION);
		assertEquals("The position is the right position", result.positionValue, new WordsPosition(0,2));
	}
	
	@Test (expected = WordsInvalidTypeException.class)
	public void positionWithNonCoercableString() throws WordsRuntimeException {
		INode positionEval = new INode(AST.ASTType.POSITION, 0, numLeaf1, stringLeafNonCoercable);
		positionEval.eval(environment);
	}
}
