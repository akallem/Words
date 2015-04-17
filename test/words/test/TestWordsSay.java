package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsSay extends TestINode {
    @Test
    public void testWorkingWordsSay() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action = new SayAction(stringLeaf);
        action.execute(environment.getObject("Fred"), environment);
        assertEquals("New message assigned", environment.getObject("Fred").getCurrentMessage(), "string");
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new SayAction(nothingLeaf);
        action1.execute(environment.getObject("Fred"), environment);
        Action action2 = new SayAction(trueLeaf);
        action2.execute(environment.getObject("Fred"), environment);
    }

}
