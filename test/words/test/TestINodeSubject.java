package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.AST;
import words.ast.INodeReferenceList;
import words.ast.INodeSubject;
import words.ast.LNodeIdentifier;
import words.ast.LNodeReference;
import words.environment.WordsObject;
import words.environment.WordsPosition;
import words.environment.WordsProperty;
import words.exceptions.WordsRuntimeException;

public class TestINodeSubject extends TestINode {

	@Test
	public void properlyHandlesClassName() throws WordsRuntimeException {
		AST subject = new INodeSubject(thingStringLeaf, null, null);
		assertEquals("Returns the class name", subject.eval(environment).stringValue, "thing");
	}
	
	@Test
	public void properlyHandlesObject() throws WordsRuntimeException {
		WordsObject fredObject = environment.createObject("Fred", "thing", new WordsPosition(0, 0));
		WordsObject georgeObject = environment.createObject("George", "thing", new WordsPosition(0, 0));
		LNodeReference fredRef = new LNodeReference("Fred's");
		fredObject.setProperty("brother", new WordsProperty(georgeObject));
		INodeReferenceList fredRefList = new INodeReferenceList(fredRef);
		LNodeIdentifier id = new LNodeIdentifier("brother");
		AST subject = new INodeSubject(null, fredRefList, id);
		assertEquals("Follows reference list and returns object", subject.eval(environment).objValue, georgeObject);
	}

}
