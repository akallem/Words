package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeReferenceList extends TestINode {
	
	@Test
	public void testSizeZero() throws WordsRuntimeException {
		INodeReferenceList refList = new INodeReferenceList();
		ASTValue nothingValue = refList.eval(environment);
		
		assertEquals(nothingValue.type, ASTValue.ValueType.NOTHING);
	}
	
	@Test
	public void testSizeOne() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0, 0));
		LNodeReference alexRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexRef);
		
		ASTValue alexValue = alexRefList.eval(environment);
		assertEquals(alexValue.objValue, alexObject);
	}
	
	@Test
	public void testSizeTwo() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0, 0));
		LNodeReference alexRef = new LNodeReference("Alex's");
		
		WordsObject bobObject = environment.createObject("Bob", "thing", new WordsPosition(0, 0));
		alexObject.setProperty("friend", new WordsProperty(bobObject));
		
		LNodeReference friendRef = new LNodeReference("friend");
		INodeReferenceList refList = new INodeReferenceList(alexRef, friendRef);
		
		ASTValue alexFriendValue = refList.eval(environment);
		assertEquals(alexFriendValue.objValue, bobObject);
	}
	
	@Test (expected = WordsObjectNotFoundException.class)
	public void firstNotAnObject() throws WordsRuntimeException {
		LNodeReference friendRef = new LNodeReference("friend");
		INodeReferenceList refList = new INodeReferenceList(friendRef);
		
		refList.eval(environment);
	}
	
	@Test (expected = WordsReferenceException.class)
	public void secondNotAnObject() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0, 0));
		LNodeReference alexRef = new LNodeReference("Alex's");
		
		alexObject.setProperty("friend", new WordsProperty(6.1));
		
		LNodeReference friendRef = new LNodeReference("friend");
		INodeReferenceList refList = new INodeReferenceList(alexRef, friendRef);
		
		refList.eval(environment);
	}

}
