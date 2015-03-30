import java.util.LinkedList;

public class WordsMove extends WordsAction {
	private Direction direction;
	private AST distanceExpression;
	private int distanceValue;
	
	//TODO: handle ANYWHERE direction by selecting a random direction
	public WordsMove(Direction direction, AST distanceExpression) {
		this.direction = direction;
		this.distanceExpression = distanceExpression;
	}
	
	//TODO: handle ANYWHERE direction by selecting a random direction
	/**
	 * Create a new WordsMove action.  distanceValue must round to a positive or negative integer.
	 * distanceValue cannot round to zero.
	 */
	public WordsMove(Direction direction, double distanceValue) {
		this.direction = direction;
		this.distanceValue = (int) Math.round(distanceValue);
		
		// TODO: flip if distanceValue is negative
	}

	@Override
	public boolean isExecutable() {
		if (distanceExpression != null)
			return false;

		if (distanceValue != 1)
			return false;
		
		return true;
	}

	@Override
	public void doExecute(WordsObject object, WordsEnvironment environment) {
		// We know that the distanceValue is 1
		// ANYWHERE directions will already have been replaced to be a real direction
		switch(direction.type) {
			case DOWN:
				object.moveDown();
			case LEFT:
				object.moveLeft();
			case RIGHT:
				object.moveRight();
			case UP:
				object.moveUp();
			default:
				throw new AssertionError("Attempted to execute direction type " + direction.type.toString());
		}		
	}

	@Override
	public LinkedList<WordsAction> doExpand(WordsObject object) {
		if (distanceExpression != null) {
			AST.ASTValue value = distanceExpression.eval(null);
			
			if (value.type != AST.ValueType.NUM) {
				// TODO
				// Throw an appropriate WordsException
			}
			
			distanceValue = (int) Math.round(value.numValue);
			distanceExpression = null;						// Not necessary, but including for clarity since once the expression is evaluated, it is no longer needed
		}
		
		//TODO: flip if distanceValue is negative

		LinkedList<WordsAction> list = new LinkedList<WordsAction>();

		// Decompose into executable 1-unit moves, or a wait action if distanceValue is zero
		if (distanceValue > 0) {
			for (int i = 0; i < distanceValue; i++) {
				list.add(new WordsMove(direction, 1));
			}
		} else {
			list.add(new WordsWait(1));
		}
		
		return list;
	}
}