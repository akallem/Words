package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.environment.Variable.VariableType;
import words.exceptions.*;


public class TestINodeRemove extends TestINode {
	@Test
	public void testBasicRemove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0.0, 0.0));
		assertNotEquals("Variable does exist", environment.getVariable("Fred").type, Variable.VariableType.NOTHING);

		
		INodeReferenceList refList = new INodeReferenceList();
		LNodeIdentifier id = new LNodeIdentifier("Fred");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
		loop.fastForwardEnvironment(1);
		assertEquals("RemovedObject is nothing", environment.getVariable("Fred").type, Variable.VariableType.NOTHING);
	}

	@Test (expected = ObjectNotFoundException.class)
	public void testRemoveMissingObject() throws WordsRuntimeException {
		assertEquals("Variable does not exist", environment.getVariable("Garbage").type, Variable.VariableType.NOTHING);
		
		INodeReferenceList refList = new INodeReferenceList();
		LNodeIdentifier id = new LNodeIdentifier("Garbage");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
	}
	
	@Test
	public void testRemoveReferersProperty() throws WordsRuntimeException {
		WordsObject fred = environment.createObject("Fred", "thing", new Position(0.0, 0.0));
		WordsObject mark = environment.createObject("Mark", "thing", new Position(0.0, 1.0));
		fred.setProperty("friend", new Variable(mark));
		
		WordsObject fredsFriend = fred.getProperty("friend").objProperty;
		assertEquals(fredsFriend, mark);
		
		assertNotEquals("Variable does exist", environment.getVariable("Mark").type, Variable.VariableType.NOTHING);
		
		LNodeReference ref = new LNodeReference("Fred's");
		INodeReferenceList refList = new INodeReferenceList(ref);
		LNodeIdentifier id = new LNodeIdentifier("friend");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
		loop.fastForwardEnvironment(1);
		assertEquals("RemovedObject is nothing", environment.getVariable("Mark").type, Variable.VariableType.NOTHING);

	}
	
	@Test
	public void testRemoveReferersPropertyByName() throws WordsRuntimeException {
		WordsObject fred = environment.createObject("Fred", "thing", new Position(0.0, 0.0));
		WordsObject mark = environment.createObject("Mark", "thing", new Position(0.0, 1.0));
		fred.setProperty("friend", new Variable(mark));
		
		WordsObject fredsFriend = fred.getProperty("friend").objProperty;
		assertEquals(fredsFriend, mark);
		
		assertNotEquals("Variable does exist", environment.getVariable("Mark").type, Variable.VariableType.NOTHING);

		
		INodeReferenceList refList = new INodeReferenceList();
		LNodeIdentifier id = new LNodeIdentifier("Mark");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
		loop.fastForwardEnvironment(1);
		
		Variable fredsNewFriend = fred.getProperty("friend");
		assertEquals(fredsNewFriend.objProperty, null);
		assertEquals(fredsNewFriend.type, VariableType.NOTHING);
	}
}
