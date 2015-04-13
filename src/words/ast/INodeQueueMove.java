package words.ast;

import java.util.Random;
import words.ast.ASTValue.ValueType;
import words.environment.*;
import words.exceptions.*;

public class INodeQueueMove extends INode {
	public INodeQueueMove(Object... children) {
		super(children);
	}

	@Override
	public ASTValue eval(WordsEnvironment environment) throws WordsRuntimeException {
		ASTValue referenceObject = children.get(0).eval(environment);
		ASTValue identifier = children.get(1).eval(environment);
		ASTValue direction = children.get(2).eval(environment);
		ASTValue distance = children.get(3) != null ? children.get(3).eval(environment) : new ASTValue(1);
		ASTValue doNow = children.get(4) != null ? children.get(4).eval(environment) : null;
		
		WordsObject object;
		if (referenceObject.type.equals(ASTValue.ValueType.OBJ)){
			WordsProperty property = referenceObject.objValue.getProperty(identifier.stringValue);
			if (property.type != WordsProperty.PropertyType.OBJECT) {
				throw new WordsInvalidTypeException(ASTValue.ValueType.OBJ.toString(), property.type.toString());
			}
			object = property.objProperty;
		} else {
			object = environment.getObject(identifier.stringValue);
			if (object == null) {
				throw new WordsObjectNotFoundException(identifier.stringValue);
			}
		}
		
		assert(direction.type == ASTValue.ValueType.DIRECTION) : "Expected direction";

		if (direction.directionValue.type == Direction.Type.ANYWHERE) {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(4);
			direction.directionValue.type = Direction.Type.explicit[randomInt];
		}

		if (distance.numValue < 0) {
			distance.numValue = distance.numValue * -1;
			switch(direction.directionValue.type) {
				case DOWN:
					direction.directionValue.type = Direction.Type.UP;
					break;
				case LEFT:
					direction.directionValue.type = Direction.Type.RIGHT;
					break;
				case RIGHT:
					direction.directionValue.type = Direction.Type.LEFT;
					break;
				case UP:
					direction.directionValue.type = Direction.Type.DOWN;
					break;
			} 
		}
		
		//TODO: Distance = 0 should create a wait method
		WordsMove action = new WordsMove(direction.directionValue, distance.numValue);
		
		if (doNow == null) {
			object.enqueueAction(action);
		} else {
			object.enqueueActionAtFront(action);
		}
		
		return null;
	}
}