package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;


public class TestINodeReset extends TestINode {
	
	@Test
	(expected = ObjectNotFoundException.class)
	public void testObjectDisappears() throws WordsRuntimeException {
		WordsObject alexObj = environment.createObject("Alex", "thing", new Position(0, 0));
		
		INodeReset testNode = new INodeReset();
		testNode.eval(environment);
		
		WordsObject findAlex = environment.getObject("Alex");
		
	}
}
