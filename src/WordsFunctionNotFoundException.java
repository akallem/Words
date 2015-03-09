
public class WordsFunctionNotFoundException extends Exception {

	private static final long serialVersionUID = 4731064017027639490L;
	private String functionName;
	private String className;
	
	public WordsFunctionNotFoundException(String functionName, String className) {
		this.functionName = functionName;
		this.className = className;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	
	public String getClassName() {
		return className;
	}
}
