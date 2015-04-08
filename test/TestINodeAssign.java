import static org.junit.Assert.*;

import org.junit.Test;

public class TestINodeAssign extends TestINode {
	
	@Test
	public void assignProperty() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		WordsObject bobObject = environment.createObject("Bob", "thing", new WordsPosition(0,0));
		LNodeIdentifier bobIdentifier = new LNodeIdentifier("Bob");
		
		INodeAssign numAssign = new INodeAssign(alexRefList, new LNodeIdentifier("height"), fiveLeaf);
		INodeAssign stringAssign = new INodeAssign(alexRefList, new LNodeIdentifier("greeting"), stringLeaf);
		INodeAssign objAssign = new INodeAssign(alexRefList, new LNodeIdentifier("friend"), new INodeReferenceList(), bobIdentifier);
		INodeAssign nothingAssign = new INodeAssign(alexRefList, new LNodeIdentifier("myNothing"), new LNodeNothing());
		
		assertEquals("Number assignment successful", alexObject.getProperty("height").numProperty, 5.0);
		assertEquals("String assignment successful", alexObject.getProperty("greeting").stringProperty, "string");
		assertEquals("Object assignment successful", alexObject.getProperty("friend"), bobObject);
		assertEquals("Nothing assignment successful", alexObject.getProperty("myNothing", WordsProperty.PropertyType.NOTHING);
	}
}
