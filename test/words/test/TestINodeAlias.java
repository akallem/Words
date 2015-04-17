package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.AST;
import words.ast.ASTValue;
import words.ast.INodeAlias;
import words.ast.LNodeIdentifier;
import words.exceptions.WordsRuntimeException;

public class TestINodeAlias extends TestINode {

	@Test
	public void handlesStringValue() throws WordsRuntimeException {
		AST aliasLeaf = new INodeAlias(new LNodeIdentifier("alias"));
		assertEquals("Alias with an identifier return the id", aliasLeaf.eval(environment).stringValue, "alias");
	}
	
	@Test
	public void handlesNothingValue() throws WordsRuntimeException {
		AST aliasLeaf = new INodeAlias();
		assertEquals("Alias with no identifier return nothing", aliasLeaf.eval(environment).type, ASTValue.ValueType.NOTHING);
	}

}
