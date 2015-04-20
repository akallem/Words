package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeRetrieveProperty extends INode {
	public INodeRetrieveProperty(Object... children) {
		super(children);
	}

	@Override
	/**
	 * Returns the value of the second child (identifier).
	 * If the first child(reference list) evaluates to NOTHING, the identifier must be an object.
	 * Otherwise, the reference list evaluates to an object, and identifier refers to one of the object's properties.
	 * Returns NOTHING if the property isn't found.
	 * Throws a WordsReferenceException if the reference_list or identifier doesn't refer to an object when it's expected to.
	 */
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		ASTValue refList = children.get(0).eval(environment);
		if (refList.type == ASTValue.Type.NOTHING) {
			ASTValue id = children.get(1).eval(environment);
			
			Property property = environment.getVariable(id.stringValue);
			return new ASTValue(property);

		}
		
		WordsObject obj = refList.objValue;
		assert obj != null : "Obj was null when it shouldn't have been.";

		ASTValue id = children.get(1).eval(environment);
		String propName = id.stringValue;
		
		Property wordsProp = obj.getProperty(propName);
		ASTValue astValue = null;
		switch (wordsProp.type) {
			case STRING:
				astValue = new ASTValue(wordsProp.stringProperty);
				break;
			case NUM:
				astValue = new ASTValue(wordsProp.numProperty);
				break;
			case OBJECT:
				astValue = new ASTValue(wordsProp.objProperty);
				break;
			case NOTHING:
				astValue = new ASTValue(ASTValue.Type.NOTHING);
				break;
			default:
				throw new AssertionError("Shouldn't get here in INodeRetrieveProperty");
		}
		
		return astValue;
	}

}