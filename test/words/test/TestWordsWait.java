package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsWait extends TestINode {
    @Test
    public void testWorkingWordsWait() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action1 = new WordsWait(new LNodeNum(2));
        action1.expand(environment.getObject("Fred"), environment);
        WordsAction action2 = new WordsWait(new LNodeString("2.3"));
        action2.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyWaitPositiveNumberOfTurns() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action2 = new WordsWait(new LNodeNum(-1));
        action2.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyWaitPositiveIntegerNumberOfTurns() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action1 = new WordsWait(new LNodeNum(0.2));
        action1.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action1 = new WordsWait(nothingLeaf);
        action1.expand(environment.getObject("Fred"), environment);
        WordsAction action2 = new WordsWait(trueLeaf);
        action2.expand(environment.getObject("Fred"), environment);
    }

}
