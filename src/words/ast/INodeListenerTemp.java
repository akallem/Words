package words.ast;

import words.environment.*;
import words.exceptions.*;

public class INodeListenerTemp extends INode {
	public INodeListenerTemp(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(Environment environment) throws WordsRuntimeException {
		AST predicate = children.get(0);
		AST statementList = children.get(1);
		
		if (predicate instanceof INodeBasicActionPredicate) {
			ASTValue predicateValue = predicate.eval(environment, statementList);
			
			if (predicateValue.booleanValue == true) {
				environment.createListener(predicate, statementList, true);
			}
			return null;
		} else {
			ASTValue predicateValue = predicate.eval(environment);
	
			// currently only restricted to boolean predicate
			assert predicateValue.type == ASTValue.Type.BOOLEAN : "Predicate has type " + predicateValue.type.toString();
	
			// temporary listener is created only if the predicate is true
			if (predicateValue.booleanValue == true) {
				environment.createListener(predicate, statementList, true);
			}
			
			return null;
		}
	}
}