package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.WordsPosition;
import words.exceptions.WordsInvalidTypeException;
import words.exceptions.WordsObjectAlreadyExistsException;
import words.exceptions.WordsObjectNotFoundException;
import words.exceptions.WordsRuntimeException;


public class TestINodeWhile extends TestINode {
	@Test
	public void testWorkingRepeat() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		AST statementList = new INodeStatementList(new INodeAssign(environment.getObject("Fred"), new LNodeString("row"), new LNodeNum(environment.getObject("Fred").getCurrentCell().x++)));
		AST relation = new INodeLess(new LNodeNum(environment.getObject("Fred").getCurrentCell().x), new LNodeNum(2));
		AST testNode = new INodeWhile(relation, statementList);
		
		//test
		loop.enqueueAST(testNode);
		loop.fastForwardEnvironment(2);
		assertEquals("Fred has moved correctly", environment.getObject("Fred").getCurrentCell(), new WordsPosition(2,0));
	}
}
