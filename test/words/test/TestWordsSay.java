package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsSay extends TestINode {
    @Test
    public void testWorkingWordsSay() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action = new WordsSay(stringLeaf);
        action.execute(environment.getObject("Fred"), environment);
        assertEquals("New message assigned", environment.getObject("Fred").getCurrentMessage(), "string");
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new WordsPosition(0, 0));
        WordsAction action1 = new WordsSay(nothingLeaf);
        action1.execute(environment.getObject("Fred"), environment);
        WordsAction action2 = new WordsSay(trueLeaf);
        action2.execute(environment.getObject("Fred"), environment);
    }

}
