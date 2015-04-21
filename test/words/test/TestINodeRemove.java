package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.environment.Property.PropertyType;
import words.exceptions.*;


public class TestINodeRemove extends TestINode {
	@Test
	(expected = words.exceptions.ObjectNotFoundException.class)
	public void testBasicRemove() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0.0, 0.0));
		try {
			environment.getObject("Fred");
		} catch (ObjectNotFoundException e) {
			fail();
		}
		
		INodeReferenceList refList = new INodeReferenceList();
		LNodeIdentifier id = new LNodeIdentifier("Fred");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
		environment.getObject("Fred");
	}
	
	@Test
	(expected = words.exceptions.ObjectNotFoundException.class)
	public void testRemoveReferersProperty() throws WordsRuntimeException {
		WordsObject fred = environment.createObject("Fred", "thing", new Position(0.0, 0.0));
		WordsObject mark = environment.createObject("Mark", "thing", new Position(0.0, 1.0));
		fred.setProperty("friend", new Property(mark));
		
		WordsObject fredsFriend = fred.getProperty("friend").objProperty;
		assertEquals(fredsFriend, mark);
		
		try {
			environment.getObject("Mark");
		} catch (ObjectNotFoundException e) {
			fail();
		}
		
		LNodeReference ref = new LNodeReference("Fred's");
		INodeReferenceList refList = new INodeReferenceList(ref);
		LNodeIdentifier id = new LNodeIdentifier("friend");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
		environment.getObject("Mark");
	}
	
	@Test
	public void testRemoveReferersPropertyByName() throws WordsRuntimeException {
		WordsObject fred = environment.createObject("Fred", "thing", new Position(0.0, 0.0));
		WordsObject mark = environment.createObject("Mark", "thing", new Position(0.0, 1.0));
		fred.setProperty("friend", new Property(mark));
		
		WordsObject fredsFriend = fred.getProperty("friend").objProperty;
		assertEquals(fredsFriend, mark);
		
		try {
			environment.getObject("Mark");
		} catch (ObjectNotFoundException e) {
			fail();
		}
		
		INodeReferenceList refList = new INodeReferenceList();
		LNodeIdentifier id = new LNodeIdentifier("Mark");
		
		INodeRemoveObject removeObj = new INodeRemoveObject(refList, id);
		removeObj.eval(environment);
		
		Property fredsNewFriend = fred.getProperty("friend");
		assertEquals(fredsNewFriend.objProperty, null);
		assertEquals(fredsNewFriend.type, PropertyType.NOTHING);
	}
}
