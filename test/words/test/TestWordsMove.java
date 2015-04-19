package words.test;

import org.junit.Test;

import words.environment.*;
import words.exceptions.*;

public class TestWordsMove extends TestINode {
    @Test
    public void testWorkingWordsMove() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action = new MoveAction(Direction.Type.LEFT, twoLeaf);
        action.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new MoveAction(Direction.Type.LEFT, nothingLeaf);
        action1.expand(environment.getObject("Fred"), environment);
        Action action2 = new MoveAction(Direction.Type.LEFT, trueLeaf);
        action2.expand(environment.getObject("Fred"), environment);
    }

}
