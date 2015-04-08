public class INodeRepeat extends INode {
	public INodeRepeat(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue times = children.get(0).eval(environment).tryCoerceTo(ASTValue.ValueType.NUM);
		AST statementList = children.get(1);
		
		if (times.type != ASTValue.ValueType.NUM) {
			throw new WordsInvalidTypeException(ASTValue.ValueType.NUM.toString(), times.type.toString());
		}
		
		for (int i = 0; i < times.numValue; i++) {
			statementList.eval(environment);
		}
		
		return null;
	}
}