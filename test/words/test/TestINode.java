package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.FrameLoop;
import words.ast.*;
import words.environment.*;


public class TestINode {
	// Some generic variables useful for all tests
	WordsEnvironment environment = new WordsEnvironment();
	FrameLoop loop = new FrameLoop(environment);

	AST nothingLeaf = new LNodeNothing();
	AST numLeaf = new LNodeNum(0.0);
	AST twoLeaf = new LNodeNum(2.0);
	AST negTwoLeaf = new LNodeNum(-2.0);
	AST fiveLeaf = new LNodeNum(5.0);
	AST stringLeaf = new LNodeString("string");
	AST thingStringLeaf = new LNodeString("thing");
	AST position00 = new INodePosition(numLeaf, numLeaf);
	
	AST fredStringLeaf = new LNodeString("Fred");
	AST leftDirectionLeaf = new LNodeDirection(Direction.Type.LEFT);
	AST rightDirectionLeaf = new LNodeDirection(Direction.Type.RIGHT);
	AST anywhereDirectionLeaf = new LNodeDirection(Direction.Type.ANYWHERE);
	
	AST moveFredLeft2 = new INodeQueueMove(nothingLeaf, fredStringLeaf, leftDirectionLeaf, twoLeaf, null);
	AST moveFredRight2 = new INodeQueueMove(nothingLeaf, fredStringLeaf, rightDirectionLeaf, twoLeaf, null);
	AST moveFredLeftNegative2 = new INodeQueueMove(nothingLeaf, fredStringLeaf, leftDirectionLeaf, negTwoLeaf, null);
	AST moveFredAnywhere2 = new INodeQueueMove(nothingLeaf, fredStringLeaf, anywhereDirectionLeaf, twoLeaf, null);

	AST trueLeaf = new INodeEquals(nothingLeaf, nothingLeaf);
	AST falseLeaf = new INodeEquals(nothingLeaf, numLeaf);
	
	AST createObjectFred = new INodeCreateObject(fredStringLeaf, thingStringLeaf, null, position00);
	
	@Test
	public void canAddNullToINode() {
		INode testNode = new INodeStatementList(null, null, null, null);
		assertEquals("INode has correct number of children", testNode.children.size(), 4);
	}
}
