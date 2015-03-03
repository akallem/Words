import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;


public class Game extends Thread {
	
	private HashMap<String, WordsClass> classes;
	private HashMap<String, WordsObject> objects;
	private ArrayList<WordsEventListener> eventListeners;
	private LinkedBlockingDeque<Command> commandQueue;
	private WordsUI GUI;
	private static int TIME_TO_WAIT = 1000;
	
	public Game(WordsUI GUI) {
		classes = new HashMap<String, WordsClass>();
		objects = new HashMap<String, WordsObject>();
		eventListeners = new ArrayList<WordsEventListener>();
		commandQueue = new LinkedBlockingDeque<Command>();
		setupWorld();
		this.GUI = GUI;
	}
	
	public void addCommandToQueue(Object command) {
		commandQueue.add((Command) command);
		System.out.println(command.toString());
	}

	
	public void run() {
		int counter = 1;
		while(true) {
			long timeToSleep = 1000;
		    long start, end, slept;
		    boolean interrupted;
			while(timeToSleep > 0){
		        start=System.currentTimeMillis();
		        try{
		            Thread.sleep(timeToSleep);
		            break;
		        }
		        catch(InterruptedException e){

		            //work out how much more time to sleep for
		            end=System.currentTimeMillis();
		            slept=end-start;
		            timeToSleep-=slept;
		            interrupted=true;
		        }
		    }
			while (!commandQueue.isEmpty()) {
				Command command = commandQueue.pop();
				processCommand(command);
			}
			for (WordsObject object : objects.values()) {
				object.doAction();
			}
			
			GUI.clear();
			for (WordsObject object : objects.values()) {
				GUI.add(object.getCurrentCell(), object.getClassName(), object.getObjectName(), object.getCurrentMessage());
			}
			GUI.render();
			executeEventListeners();
			counter++;
		}
	}
	
	private void setupWorld() {
		WordsClass thing = new WordsClass("thing", null);
		classes.put("thing", thing);
	}
	
	private void processCommand(Command command) {
		if (command.getType() == CommandType.CREATE) {
			String className = (String) command.parameters.get("class");
			String objectName = (String) command.parameters.get("name");
			WordsClass objectClass = classes.get(className);
			WordsPosition cell = (WordsPosition) command.parameters.get("cell");
			WordsObject newObject = new WordsObject(objectClass, objectName, cell);
			objects.put(objectName, newObject);
		}
		if (command.getType() == CommandType.MAKE) {
			String objectName = (String) command.parameters.get("objectName");
			WordsObject objectToModify = objects.get(objectName);
			String functionName = (String) command.parameters.get("functionName");
			String arguments = (String) command.parameters.get("arguments");
			if (functionName.equals("move")) {
				String[] argv = arguments.split(" ");
				WordsMove.MoveDirection direction = WordsMove.getMoveDirection(argv[0]);
				int numberOfSpaces = (int) Double.parseDouble(argv[1]);
				System.out.println(numberOfSpaces);
				for (int i = 0; i < numberOfSpaces; i++) {
					objectToModify.enqueueAction(new WordsMove(objectToModify, direction));
				}
			}
		}
	}
	
	private void executeEventListeners() {
		//TODO
	}

}
