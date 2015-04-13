package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.environment.WordsClass;
import words.environment.WordsProperty;

public class TestWordsClass {
	@Test
	public void classNameShouldBeSetAndRetained() {
		String className = "myClass";
		WordsClass testClass = new WordsClass(className, null);
		assertEquals("Class name retained", className, testClass.getClassName());
	}
	
	@Test
	public void shouldGetPreviouslySetProperty() {
		WordsClass testClass = new WordsClass("testClass", null);
		
		String propertyName = "height";
		WordsProperty numProperty = new WordsProperty(15.5);		
		testClass.setProperty(propertyName, numProperty);
		
		assertEquals("Retrieved property should match set property", numProperty, testClass.getProperty(propertyName));
	}
	
	@Test
	public void missingPropertyShouldGetNothing() {
		WordsClass testClass = new WordsClass("testClass", null);
		
		String propertyName = "garbage";
		assertEquals("Retrieved property was NOTHING", WordsProperty.PropertyType.NOTHING, testClass.getProperty(propertyName).type);
	}
	
	@Test
	public void inheritanceOfExistingPropertyShouldWork() {
		WordsClass parentClass = new WordsClass("parent", null);
		WordsClass childClass = new WordsClass("child", parentClass);
		WordsClass grandchildClass = new WordsClass("grandchild", childClass);
		
		String propertyName = "height";
		WordsProperty numProperty = new WordsProperty(15.5);
		parentClass.setProperty(propertyName, numProperty);
		
		assertEquals("Parent class retrieved its own property", numProperty, parentClass.getProperty(propertyName));
		assertEquals("Child inherited parent class property", numProperty, childClass.getProperty(propertyName));
		assertEquals("Grandchild inherited grandparent class property", numProperty, grandchildClass.getProperty(propertyName));
	}
	
	@Test
	public void inheritanceOfMissingPropertyShouldBeNothing() {
		WordsClass parentClass = new WordsClass("parent", null);
		WordsClass childClass = new WordsClass("child", parentClass);
		WordsClass grandchildClass = new WordsClass("grandchild", childClass);
		
		String propertyName = "garbage";
		assertEquals("Parent class retrieved NOTHING", WordsProperty.PropertyType.NOTHING, parentClass.getProperty(propertyName).type);
		assertEquals("Child class retrieved NOTHING", WordsProperty.PropertyType.NOTHING, childClass.getProperty(propertyName).type);
		assertEquals("Grandchild class retrieved NOTHING", WordsProperty.PropertyType.NOTHING, grandchildClass.getProperty(propertyName).type);
	}
	
	@Test
	public void overridenInheritanceShouldWork() {
		WordsClass parentClass = new WordsClass("parent", null);
		WordsClass childClass = new WordsClass("child", parentClass);
		WordsClass grandchildClass = new WordsClass("grandchild", childClass);
		
		// Use the same propertyName in all cases to test that overriding does work
		String propertyName = "height";
		
		WordsProperty parentProperty = new WordsProperty(15.5);
		parentClass.setProperty(propertyName, parentProperty);
		
		WordsProperty grandchildProperty = new WordsProperty("something");
		grandchildClass.setProperty(propertyName, grandchildProperty);
		
		assertEquals("Parent class retrieved its own property", parentProperty, parentClass.getProperty(propertyName));
		assertEquals("Child inherited parent class property", parentProperty, childClass.getProperty(propertyName));
		assertEquals("Grandchild retrieved its own property, overriding inheritance", grandchildProperty, grandchildClass.getProperty(propertyName));
		assertNotSame("Grandchild did not retrieve parent property", grandchildClass.getProperty(propertyName), parentClass.getProperty(propertyName));
	}
}
