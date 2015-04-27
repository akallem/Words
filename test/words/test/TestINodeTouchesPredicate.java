package words.test;

import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeTouchesPredicate extends TestINode {

	@Test
	public void knowsIfTwoThingsTouch() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		environment.createObject("George", "thing", new Position(0,0));
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias1"), 
				thingStringLeaf, new LNodeIdentifier("alias2"));
		loop.fastForwardEnvironment(1); //objects are created with a 1 frame wait, so use it up.
		assertEquals("Two things in same location eval true when first created", touchesPred.eval(environment, statementsAboutFred).booleanValue, true);
		
		loop.enqueueAST(makeFredWait1);
		loop.enqueueAST(makeGeorgeWait1);
		loop.fastForwardEnvironment(1);
		assertEquals("Two things in same location eval false when haven't moved", touchesPred.eval(environment, statementsAboutFred).booleanValue, false);
		loop.enqueueAST(moveFredLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Two things not in same location eval false", touchesPred.eval(environment, statementsAboutFred).booleanValue, false);
		loop.enqueueAST(moveGeorgeLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Two things in same location eval true when just moved", touchesPred.eval(environment, statementsAboutFred).booleanValue, true);
	}
	
	@Test
	public void objectsDontTouchThemselves() throws WordsRuntimeException {
		environment.createObject("Fred", "thing", new Position(0,0));
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias1"), 
				thingStringLeaf, new LNodeIdentifier("alias2"));
		assertEquals("One thing evals false", touchesPred.eval(environment, statementsAboutFred).booleanValue, false);
	}
	
	@Test (expected = AliasException.class)
	public void twoAliasesCannotBeSame() throws WordsRuntimeException {
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias"), 
				thingStringLeaf, new LNodeIdentifier("alias"));
		touchesPred.eval(environment, statementsAboutFred);
	}
	
	@Test
	public void differentiatesClasses() throws WordsRuntimeException {
		environment.createClass("child", "thing");
		environment.createObject("Fred", "thing", new Position(0,0));
		environment.createObject("George", "child", new Position(0,0));
		loop.fastForwardEnvironment(1); //objects are created with a 1 frame wait, so use it up.
		
		INodeTouchesPredicate touchesPred = new INodeTouchesPredicate(thingStringLeaf, new LNodeIdentifier("alias1"), 
				thingStringLeaf, new LNodeIdentifier("alias2"));
		assertEquals("Two things in same location eval true when first created", touchesPred.eval(environment, statementsAboutFred).booleanValue, true);
		
		loop.enqueueAST(makeFredWait1);
		loop.enqueueAST(makeGeorgeWait1);
		loop.fastForwardEnvironment(1);
		assertEquals("Two things in same location eval false when haven't moved", touchesPred.eval(environment, statementsAboutFred).booleanValue, false);
		loop.enqueueAST(moveFredLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Two things not in same location eval false", touchesPred.eval(environment, statementsAboutFred).booleanValue, false);
		loop.enqueueAST(moveGeorgeLeft2);
		loop.fastForwardEnvironment(1);
		assertEquals("Two things in same location eval true when just moved", touchesPred.eval(environment, statementsAboutFred).booleanValue, true);
	}

}
