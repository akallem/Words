import static org.junit.Assert.*;

import org.junit.Test;

public class TestWordsPosition {

        @Test
        public void testPosition() {
                WordsPosition pos = new WordsPosition(3.5, 5.2);
                assertEquals(pos.x, 3);
                assertEquals(pos.y, 5);
        }
}
