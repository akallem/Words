public class INodeStatementList extends INode {
	public INodeStatementList(Object... children) {
		super(children);
	}
	
	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		for (int i = 0; i < children.size(); i++) {
			try {
				children.get(i).eval(environment);
			} catch (WordsRuntimeException e) {
				// Add the AST to the exception, and then print it.
				WordsProgramException decoratedException = new WordsProgramException(children.get(i), e);
				System.err.println();
				System.err.println(decoratedException);
				System.out.print("> ");
			}
		}
		
		return null;
	}
}