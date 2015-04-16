package words.ast;

import words.ast.ASTValue.ValueType;
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
		
		WordsClass wordsClass = environment.createClass(className.stringValue, parentClassName.stringValue);
		
		INodeClassStatementList statementList = (INodeClassStatementList) this.children.get(2);
		if (statementList != null) {
			for (Object child : statementList.children) {
				if (child instanceof INodeDefineProperty) {
					ASTValue propertyName = ((INodeDefineProperty) child).id.eval(environment);
					
					if (((INodeDefineProperty) child).literal == null) {
						wordsClass.setProperty(propertyName.stringValue, new WordsProperty(WordsProperty.PropertyType.NOTHING));
					} else {
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
						
						wordsClass.setProperty(propertyName.stringValue, wordsProp);
					}
				}
			}
		}
		
		return null;
	}
}