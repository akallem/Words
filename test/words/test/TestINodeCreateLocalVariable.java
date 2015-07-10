package words.test;
import org.junit.Test;

import static org.junit.Assert.*;
import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestINodeCreateLocalVariable extends TestINode {

	@Test
	public void canCreateLocalVariableFromNumber() throws WordsRuntimeException {
		AST varCreator = new INodeCreateLocalVariable(stringLeaf, twoLeaf);
		varCreator.eval(environment);
		Variable varRecvd = environment.getVariable("string");
		assertEquals(varRecvd.numProperty, twoLeaf.eval(environment).numValue, .001);
	}
	
	@Test
	public void canCreateLocalVariableFromString() throws WordsRuntimeException {
		AST varCreator = new INodeCreateLocalVariable(stringLeaf, fredStringLeaf);
		varCreator.eval(environment);
		Variable varRecvd = environment.getVariable("string");
		assertEquals(varRecvd.stringProperty, fredStringLeaf.eval(environment).stringValue);
	}
	
	@Test (expected = InvalidLocalVariableException.class)
	public void cantCreateOtherLocalVariables() throws WordsRuntimeException {
		// To pass an object up an AST, we first need to create an object in the environment.
		createObjectFred.eval(environment);
		AST objRetriever = new INodeRetrieveProperty(new INodeReferenceList(), fredStringLeaf);
		AST varCreator = new INodeCreateLocalVariable(stringLeaf, objRetriever);
		varCreator.eval(environment);
		environment.getVariable("string");
	}
}
