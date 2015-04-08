package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeRetrieveProperty extends TestINode {
	
	@Test
	public void testNoReferenceList() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		LNodeIdentifier alexLNodeId = new LNodeIdentifier("Alex");
		
		INodeRetrieveProperty retrieveAlex = new INodeRetrieveProperty(new INodeReferenceList(), alexLNodeId);
		ASTValue alexValue = retrieveAlex.eval(environment);
		
		assertEquals(alexValue.objValue, alexObject);
	}
	
	@Test
	public void testWithReferenceListIdObject() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		WordsObject bobObject = environment.createObject("Bob", "thing", new WordsPosition(0, 0));
		alexObject.setProperty("friend", new WordsProperty(bobObject));
		
		INodeRetrieveProperty retrieveAlexFriend = new INodeRetrieveProperty(alexRefList, new LNodeIdentifier("friend"));
		ASTValue alexFriendValue = retrieveAlexFriend.eval(environment);
		
		assertEquals(alexFriendValue.objValue, bobObject);
	}
	
	@Test
	public void testWithReferenceListIdNum() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		alexObject.setProperty("height", new WordsProperty(6.1));
		
		INodeRetrieveProperty retrieveAlexHeight = new INodeRetrieveProperty(alexRefList, new LNodeIdentifier("height"));
		ASTValue alexHeightValue = retrieveAlexHeight.eval(environment);
		
		assertEquals(alexHeightValue.numValue, 6.1, .0001);
	}
	
	@Test
	public void testWithReferenceListIdString() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		alexObject.setProperty("occupation", new WordsProperty("model"));
		
		INodeRetrieveProperty retrieveAlexOccupation = new INodeRetrieveProperty(alexRefList, new LNodeIdentifier("occupation"));
		ASTValue alexOccupation = retrieveAlexOccupation.eval(environment);
		
		assertEquals(alexOccupation.stringValue, "model");
	}
	
	@Test
	public void testWithReferenceListIdNothing() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		INodeRetrieveProperty retrieveAlexHeight = new INodeRetrieveProperty(alexRefList, new LNodeIdentifier("height"));
		ASTValue alexHeightValue = retrieveAlexHeight.eval(environment);
		
		assertEquals(alexHeightValue.type, ASTValue.ValueType.NOTHING);
	}
	
	@Test (expected = WordsReferenceException.class)
	public void testNoRefListIdNotObject() throws WordsRuntimeException {
		INodeRetrieveProperty retrieveError = new INodeRetrieveProperty(new INodeReferenceList(), new LNodeIdentifier("bad"));
		retrieveError.eval(environment);
	}


}
