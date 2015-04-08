public class INodeIf extends INode {
	public INodeIf(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue predicate = children.get(0).eval(environment);
		AST statementList = children.get(1);
		
		assert predicate.type == ASTValue.ValueType.BOOLEAN : "Predicate has type " + predicate.type.toString();
		
		if (predicate.booleanValue == true) {
			statementList.eval(environment);
		}
		
		return null;
	}
}