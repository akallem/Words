package words.environment;
import java.util.LinkedList;

import java.util.Random;
import words.ast.AST;
import words.ast.ASTValue;
import words.ast.ASTValue.Type;
import words.ast.LNodeNum;
import words.exceptions.InvalidTypeException;
import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

public class MoveAction extends Action {
	private Direction direction;
	private AST distanceExpression;		// The expression whose value will be the number of moves to make; used only before the move has been expanded

	/**
	 * Create a new WordsMove action.  distanceExpression should evaluate to a number (or be coercible into a number)
	 * else an exception will be thrown to the user when the action is executed.
	 * 
	 * distanceExpression may be null, in which case the WordsMove will be treated as a 1-unit move.
	 */
	public MoveAction(Direction direction, AST distanceExpression) {
		if (direction.type == Direction.Type.ANYWHERE) {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(4);
			this.direction = new Direction(Direction.explicit[randomInt]);
		} else {
			this.direction = direction;
		}
		this.distanceExpression = distanceExpression;
	}
	
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Private constructor used to create a 1-unit move action.
	 */
	private MoveAction(Direction direction) {
		this.direction = direction;
		this.distanceExpression = null;
	}

	@Override
	public boolean isExecutable() {
		if (distanceExpression != null)
			return false;

		return true;
	}

	@Override
	public void doExecute(WordsObject object, Environment environment) throws WordsProgramException {
		// We know that the distanceValue is 1
		// ANYWHERE directions will already have been replaced to be a real direction
		switch(direction.type) {
			case DOWN:
				object.moveDown();
				break;
			case LEFT:
				object.moveLeft();
				break;
			case RIGHT:
				object.moveRight();
				break;
			case UP:
				object.moveUp();
				break;
			default:
				throw new AssertionError("Attempted to execute direction type " + direction.type.toString());
		}
	}

	@Override
	public LinkedList<Action> doExpand(WordsObject object, Environment environment) throws WordsProgramException {
		ASTValue value;
		try {
			value = distanceExpression.eval(environment).tryCoerceTo(ASTValue.Type.NUM);
		} catch (WordsRuntimeException e) {
			throw new WordsProgramException(distanceExpression, e);
		}

		if (value.type != ASTValue.Type.NUM) {
			throw new WordsProgramException(distanceExpression, new InvalidTypeException(value.type.toString(), ASTValue.Type.NUM.toString()));
		}

		int distanceValue = (int) Math.round(value.numValue);

		if (distanceValue < 0) {
			distanceValue = -distanceValue;
			direction.type = Direction.getOpposite(direction.type);
		}

		LinkedList<Action> list = new LinkedList<Action>();

		// Decompose into executable 1-unit moves, or a wait action if distanceValue is zero
		if (distanceValue > 0) {
			for (int i = 0; i < distanceValue; i++) {
				// Use the private constructor to create the 1-unit move
				list.add(new MoveAction(direction));
			}
		} else {
			list.add(new WaitAction(new LNodeNum(1)));
		}

		return list;
	}
}