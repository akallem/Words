import java.util.HashMap;


public class WordsClass {

	private HashMap<String, WordsProperty> properties;
	private HashMap<String, WordsFunction> functions;
	private WordsClass parent;
	private String className;
	
	public WordsClass(String className, WordsClass parent) {
		properties = new HashMap<String, WordsProperty>();
		functions = new HashMap<String, WordsFunction>();
		this.parent = parent;
		this.className = className;
	}
	
	public WordsObject createObject(String name, HashMap<String, WordsProperty> properties) {
		//TODO
		return null;
	}
	
	public String getClassName() {
		return className;
	}
}
