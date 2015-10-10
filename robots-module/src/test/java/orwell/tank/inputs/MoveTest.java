package orwell.tank.inputs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by parapampa on 14/06/15.
 */
public class MoveTest {
    private final static Logger logback = LoggerFactory.getLogger(MoveTest.class);
    private final static double MESSAGE_MOVE_LEFT = 50.5;
    private final static double MESSAGE_MOVE_RIGHT = 100.0;

    @Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");

    }

    @Test
    public void testHasMove_true() throws Exception {
        List<String> list = new LinkedList<>();
        list.add(Double.toString(MESSAGE_MOVE_LEFT));
        list.add(Double.toString(MESSAGE_MOVE_RIGHT));
        Move move = new Move(list);
        assertTrue(move.hasMove());
        assertEquals(MESSAGE_MOVE_LEFT, move.getLeftMove(), 0);
        assertEquals(MESSAGE_MOVE_RIGHT, move.getRightMove(), 0);
    }

    @Test
    public void testHasMove_false() throws Exception {
        List<String> list = new LinkedList<>();
        list.add(Double.toString(MESSAGE_MOVE_LEFT));
        Move move = new Move(list);
        assertFalse(move.hasMove());
        assertNotEquals(MESSAGE_MOVE_LEFT, move.getLeftMove(), 0);
        assertNotEquals(MESSAGE_MOVE_RIGHT, move.getRightMove(), 0);
    }


    @After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}
