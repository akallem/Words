package words.ast;

import words.environment.WordsClass;
import words.environment.WordsEnvironment;
import words.environment.WordsProperty;
import words.exceptions.WordsClassAlreadyExistsException;
import words.exceptions.WordsClassNotFoundException;
import words.exceptions.WordsRuntimeException;

public class INodeCreateClass extends INode {
	public INodeCreateClass(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue className  = this.children.get(0).eval(environment);
		ASTValue parentClassName = this.children.get(1).eval(environment);
		
		try {
			environment.getClass(className.stringValue);
			throw new WordsClassAlreadyExistsException(className.stringValue);
		
		} catch (WordsClassNotFoundException e) {
			environment.getClass(parentClassName.stringValue); //throws error if class not found
			WordsClass freshClass = environment.createClass(className.stringValue, parentClassName.stringValue);
			
			INodeClassStatementList statementList = (INodeClassStatementList) this.children.get(2);
			if (statementList != null) {
				for (Object child : statementList.children) {
					if (child instanceof INodeDefineProperty) {
						ASTValue propertyName = ((INodeDefineProperty) child).id.eval(environment);
						ASTValue propertyValue = ((INodeDefineProperty) child).literal.eval(environment);
						
						WordsProperty wordsProp = null;
						switch (propertyValue.type) {
							case STRING:
								wordsProp = new WordsProperty(propertyValue.stringValue);
								break;
							case NUM:
								wordsProp = new WordsProperty(propertyValue.numValue);
								break;
							default:
								throw new AssertionError("literal wasn't number or string");
						}
						
						freshClass.setProperty(propertyName.stringValue, wordsProp);
					}
				}
			}
		}
		
		return null;
	}
}