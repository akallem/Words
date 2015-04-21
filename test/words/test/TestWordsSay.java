package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.environment.*;
import words.exceptions.*;

public class TestWordsSay extends TestINode {
    @Test
    public void testWorkingWordsSay() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action = new SayAction(environment.getCurrentScope(), stringLeaf);
        action.execute(environment.getVariable("Fred").objProperty, environment);
        assertEquals("New message assigned", environment.getVariable("Fred").objProperty.getCurrentMessage(), "string");
    }

    @Test (expected = WordsProgramException.class)
    public void onlyOperatesOnStringsAndNumbers() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action1 = new SayAction(environment.getCurrentScope(), nothingLeaf);
        action1.execute(environment.getVariable("Fred").objProperty, environment);
        Action action2 = new SayAction(environment.getCurrentScope(), trueLeaf);
        action2.execute(environment.getVariable("Fred").objProperty, environment);
    }

}
