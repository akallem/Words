

public class INodeReferenceList extends INode {
	public INodeReferenceList(Object... children) {
		super(children);
	}

	@Override
	/**
	 * Returns ASTValue.ValueType.NOTHING if no children. Returns an ASTValue of type OBJ if all references are to objects.
	 * Throws WordsReferenceException if some reference isn't to an object.
	 */
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		if (children.size() == 0) {
			return new ASTValue(ASTValue.ValueType.NOTHING);
		}
		
		String firstObjRefName = children.get(0).eval(environment).stringValue;
		WordsObject currentObj = environment.getObject(firstObjRefName);
		
		for (int i = 1; i < children.size(); i++) {
			String propertyRefName = children.get(i).eval(environment).stringValue;
			WordsProperty prop = currentObj.getProperty(propertyRefName);
			if (prop.type != WordsProperty.PropertyType.OBJECT) {
				throw new WordsReferenceException();
			}
			currentObj = prop.objProperty;
		}
		
		return new ASTValue(currentObj);
	}
}