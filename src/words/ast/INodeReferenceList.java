package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeReferenceList extends INode {
	public INodeReferenceList(Object... children) {
		super(children);
	}

	@Override
	/**
	 * Returns ASTValue.ValueType.NOTHING if no children. Returns an ASTValue of type OBJ if all references are to objects.
	 * Throws WordsReferenceException if some reference isn't to an object.
	 */
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		if (children.size() == 0) {
			return new ASTValue(ASTValue.Type.NOTHING);
		}
		
		String firstObjRefName = children.get(0).eval(environment).stringValue;
		WordsObject currentObj = environment.getObject(firstObjRefName);
		
		for (int i = 1; i < children.size(); i++) {
			String propertyRefName = children.get(i).eval(environment).stringValue;
			Property prop = currentObj.getProperty(propertyRefName);
			if (prop.type != Property.PropertyType.OBJECT) {
				throw new ReferenceException(propertyRefName, prop.type);
			}
			currentObj = prop.objProperty;
		}
		
		return new ASTValue(currentObj);
	}
}