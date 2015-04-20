package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeAssign extends TestINode {
	
	@Test
	public void assignNumProperty() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new Position(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		INodeAssign numAssign = new INodeAssign(alexRefList, new LNodeIdentifier("height"), fiveLeaf);
		numAssign.eval(environment);
		
		assertEquals("Number assignment successful", alexObject.getProperty("height").numProperty, 5.0, .0001);
	}
	
	@Test
	public void assignStringProperty() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new Position(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);

		INodeAssign stringAssign = new INodeAssign(alexRefList, new LNodeIdentifier("greeting"), stringLeaf);
		stringAssign.eval(environment);

		assertEquals("String assignment successful", alexObject.getProperty("greeting").stringProperty, "string");
	}
	
	@Test
	public void assignObjectProperty() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new Position(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		WordsObject bobObject = environment.createObject("Bob", "thing", new Position(0,0));
		LNodeIdentifier bobIdentifier = new LNodeIdentifier("Bob");
		
		INodeAssign objAssign = new INodeAssign(alexRefList, new LNodeIdentifier("friend"), new INodeRetrieveProperty(new INodeReferenceList(), bobIdentifier));
		objAssign.eval(environment);
		assertEquals("Object assignment assigned correct type", alexObject.getProperty("friend").type, Property.PropertyType.OBJECT);
		assertEquals("Object assignment successful", alexObject.getProperty("friend").objProperty, bobObject);
	}
	
	@Test
	public void assignNothingProperty() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new Position(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		INodeAssign nothingAssign = new INodeAssign(alexRefList, new LNodeIdentifier("myNothing"), nothingLeaf);
		nothingAssign.eval(environment);
		
		assertEquals("Nothing assignment successful", alexObject.getProperty("myNothing").type, Property.PropertyType.NOTHING);
	}
	
	@Test (expected = ReferenceException.class)
	public void assignPropertyToNonExistentObject() throws WordsRuntimeException {
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);

		INodeAssign stringAssign = new INodeAssign(alexRefList, new LNodeIdentifier("greeting"), stringLeaf);
		stringAssign.eval(environment);
	}
}
