package words.test;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsWait extends TestINode {
	
    @Test
    public void testWorkingWordsWait() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new WaitAction(new LNodeNum(2));
        action1.expand(environment.getObject("Fred"), environment);
        Action action2 = new WaitAction(new LNodeString("2.3"));
        action2.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyWaitPositiveNumberOfTurns() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action2 = new WaitAction(new LNodeNum(-1));
        action2.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyWaitPositiveIntegerNumberOfTurns() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new WaitAction(new LNodeNum(0.2));
        action1.expand(environment.getObject("Fred"), environment);
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new WaitAction(nothingLeaf);
        action1.expand(environment.getObject("Fred"), environment);
        Action action2 = new WaitAction(trueLeaf);
        action2.expand(environment.getObject("Fred"), environment);
    }

}
