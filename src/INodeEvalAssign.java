public class INodeEvalAssign extends INode {
	public INodeEvalAssign(Object... children) {
		super(children);
	}


	/**
	 * Always returns null. Throws WordsRuntimeException for called methods, including WordsObjectNotFoundException.
	 */
	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		WordsObject obj = children.get(0).eval(environment).objValue;
		String propertyName = children.get(1).eval(environment).stringValue;
		ASTValue propertyASTValue = children.get(2).eval(environment);	

		WordsProperty wordsProp = null;
		switch (propertyASTValue.type) {
			case NUM:
				wordsProp = new WordsProperty(propertyASTValue.numValue);
				break;
			case STRING:
				wordsProp = new WordsProperty(propertyASTValue.stringValue);
				break;
			case OBJ:
				wordsProp = new WordsProperty(propertyASTValue.objValue);
				break;
			case NOTHING:
				wordsProp = new WordsProperty(WordsProperty.PropertyType.NOTHING);
				break;
			default:
				throw new AssertionError("Shouldn't get here");
		}
	
		obj.setProperty(propertyName, wordsProp);
		
		return null;
	}

}
