import static org.junit.Assert.*;

import org.junit.Test;

public class TestINodeAssign extends TestINode {
	reference reference_list identifier IS value_expression '.'					{ $$ = new INodeAssign((new INodeReferenceList($1)).add(((INode) $2).children), $3, $5); ((AST) $$).lineNumber = lexer.lineNumber; }
	
	@Test
	public void assignProperty() throws WordsRuntimeException {
		WordsObject alexObject = environment.createObject("Alex", "thing", new WordsPosition(0,0));
		WordsObject bobObject = environment.createObject("Bob", "thing", new WordsPosition(0,0));
		
		LNodeReference alexLNodeRef = new LNodeReference("Alex's");
		INodeReferenceList alexRefList = new INodeReferenceList(alexLNodeRef);
		
		LNodeIdentifier bobIdentifier = new LNodeIdentifier("Bob");
		
		INodeAssign numAssign = new INodeAssign(alexRefList, new LNodeIdentifier("height"), fiveLeaf);
		INodeAssign stringAssign = new INodeAssign(alexRefList, new LNodeIdentifier("greeting"), stringLeaf);
		INodeAssign objAssign = new INodeAssign(alexRefList, new LNodeIdentifier("friend"), new INodeReferenceList(), bobIdentifier);
		INodeAssign nothingAssign = new INodeAssign(alexRefList, new LNodeIdentifier("myNothing"), new LNodeNothing());

	}
}
