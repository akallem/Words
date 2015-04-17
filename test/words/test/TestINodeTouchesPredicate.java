package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.INodeTouchesPredicate;
import words.ast.LNodeIdentifier;
import words.environment.WordsPosition;
import words.exceptions.WordsAliasException;
import words.exceptions.WordsRuntimeException;

public class TestINodeTouchesPredicate extends TestINode {

	@Test
	public void knowsIfTwoThingsTouch() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		environment.createObject("George", "thing", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //objects are created with a 1 frame wait, so use it up.
		
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias1"), 
				thingStringLeaf, new LNodeIdentifier("alias2"));
		
		assertEquals("Two things in same location eval true", touchesPred.eval(environment).booleanValue, true);
		environment.getObject("Fred").moveDown();
		assertEquals("Two things not in same location eval false", touchesPred.eval(environment).booleanValue, false);
		environment.getObject("George").moveDown();
		assertEquals("Two things in same location eval true", touchesPred.eval(environment).booleanValue, true);
	}
	
	@Test
	public void objectsDontTouchThemselves() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias1"), 
				thingStringLeaf, new LNodeIdentifier("alias2"));
		assertEquals("One thing evals false", touchesPred.eval(environment).booleanValue, false);
	}
	
	@Test (expected = WordsAliasException.class)
	public void twoAliasesCannotBeSame() throws WordsRuntimeException {
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias"), 
				thingStringLeaf, new LNodeIdentifier("alias"));
		touchesPred.eval(environment);
	}
	
	@Test
	public void differentiatesClasses() throws WordsRuntimeException {
		environment.createClass("child", "thing");
		environment.createObject("Fred", "thing", new WordsPosition(0,0));
		environment.createObject("George", "child", new WordsPosition(0,0));
		loop.fastForwardEnvironment(1); //objects are created with a 1 frame wait, so use it up.
		
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias1"), 
				thingStringLeaf, new LNodeIdentifier("alias2"));
		
		assertEquals("Two things in same location eval true", touchesPred.eval(environment).booleanValue, true);
		environment.getObject("Fred").moveDown();
		assertEquals("Two things not in same location eval false", touchesPred.eval(environment).booleanValue, false);
		environment.getObject("George").moveDown();
		assertEquals("Two things in same location eval true", touchesPred.eval(environment).booleanValue, true);
	}

}
