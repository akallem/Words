package words.test;
import static org.junit.Assert.*;

import org.junit.Test;

import words.ast.*;
import words.environment.*;
import words.exceptions.*;

public class TestWordsPropertyAssignment extends TestINode {
    @Test
    public void testWorkingWordsPropertyAssignment() throws WordsRuntimeException, WordsProgramException {
        environment.createObject("Fred", "thing", new Position(0, 0));
        Action action = new PropertyAssignAction(new INodeQueueAssignPropertyList(new INodeQueueAssignProperty(stringLeaf, numLeaf)));
        action.execute(environment.getObject("Fred"), environment);
    }
}
