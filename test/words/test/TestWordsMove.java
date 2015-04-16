package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsMove extends TestINode {
    @Test
    public void testWorkingWordsMove() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action = new WordsMove(new Direction(Direction.Type.LEFT), twoLeaf);
        action.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action1 = new WordsMove(new Direction(Direction.Type.LEFT), nothingLeaf);
        action1.expand(environment.getObject("Fred"), environment);
        WordsAction action2 = new WordsMove(new Direction(Direction.Type.LEFT), trueLeaf);
        action2.expand(environment.getObject("Fred"), environment);
    }

}
