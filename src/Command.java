import java.util.HashMap;


public class Command {
	private CommandType type;
	public HashMap<String, Object> parameters;
	
	public Command(CommandType type, HashMap<String, Object> parameters) {
		this.parameters = parameters;
		this.type = type;
	}
	
	public CommandType getType() {
		return type;
	}
	
	public String toString() {
		return type.toString() + parameters.toString();
	}
}
