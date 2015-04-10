package words.ast;

import words.environment.WordsEnvironment;
import words.environment.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class INodeStatementList extends INode {
	public INodeStatementList(Object... children) {
		super(children);
	}
	
	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		for (int i = 0; i < children.size(); i++) {
			// At the beginning and end of each statement list, we should be at the same level of scope
			int startingScopeLevel = environment.getNumberOfScopes();
			
			// The parser could potentially return null for a statement if there are syntax errors
			// Avoid attempting to do any evaluation in that case
			if (children.get(i) == null)
				continue;
			
			try {
				children.get(i).eval(environment);
			} catch (WordsRuntimeException e) {
				// First, deal with any local scopes that made need to be closed
				environment.popScopesUntilSize(startingScopeLevel);
				// Add the AST to the exception, and then print it.
				WordsProgramException decoratedException = new WordsProgramException(children.get(i), e);
				System.err.println();
				System.err.println(decoratedException);
				System.out.print("> ");
			}
			
			assert startingScopeLevel == environment.getNumberOfScopes() : "Scope error during statement list";
		}
		
		return null;
	}
}