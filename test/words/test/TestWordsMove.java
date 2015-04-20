package words.test;

import org.junit.Test;

import words.environment.*;
import words.exceptions.*;

public class TestWordsMove extends TestINode {
    @Test
    public void testWorkingWordsMove() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action = new MoveAction(Direction.LEFT, twoLeaf);
        action.expand(environment.getVariable("Fred").objProperty, environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new MoveAction(Direction.LEFT, nothingLeaf);
        action1.expand(environment.getVariable("Fred").objProperty, environment);
        Action action2 = new MoveAction(Direction.LEFT, trueLeaf);
        action2.expand(environment.getVariable("Fred").objProperty, environment);
    }

}
