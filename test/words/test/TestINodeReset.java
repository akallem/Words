package words.test;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;


public class TestINodeReset extends TestINode {
	
	@Test
	(expected = ObjectNotFoundException.class)
	public void testObjectDisappears() throws WordsRuntimeException {
		environment.createObject("Alex", "thing", new Position(0, 0));
		
		INodeReset testNode = new INodeReset();
		testNode.eval(environment);
		
		environment.getObject("Alex");	
	}
}
