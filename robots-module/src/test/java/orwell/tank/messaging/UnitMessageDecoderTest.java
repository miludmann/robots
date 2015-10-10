package orwell.tank.messaging;

import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import orwell.tank.IInputVisitor;
import orwell.tank.inputs.Move;
import orwell.tank.inputs.StopTank;

import static org.junit.Assert.*;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
@RunWith(JUnit4.class)
public class UnitMessageDecoderTest {
    private final static Logger logback = LoggerFactory.getLogger(UnitMessageDecoderTest.class);
    private final static String MESSAGE_STOP = "stop";
    private final static double MESSAGE_MOVE_LEFT = 50.5;
    private final static double MESSAGE_MOVE_RIGHT = 100.0;
    private final static String MESSAGE_MOVE = "move " + MESSAGE_MOVE_LEFT + " " + MESSAGE_MOVE_RIGHT;
    private final static String MESSAGE_FIRE = "fire true false";
    private final static String MESSAGE_STOP_PRG = "stopPrg";


    @org.junit.Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
    }

    @Test
    public void testParseFrom_stop() throws Exception {
        UnitMessage message = new UnitMessage(UnitMessageType.Command, MESSAGE_STOP);
        IInputVisitor actionVisitor = UnitMessageDecoder.parseFrom(message);
        assertTrue(actionVisitor instanceof StopTank);
    }

    @Test
    public void testParseFrom_move() throws Exception {
        UnitMessage message = new UnitMessage(UnitMessageType.Command, MESSAGE_MOVE);
        IInputVisitor actionVisitor = UnitMessageDecoder.parseFrom(message);
        assertTrue(actionVisitor instanceof Move);

        Move move = (Move) actionVisitor;
        assertTrue(move.hasMove());
        assertEquals(MESSAGE_MOVE_LEFT, move.getLeftMove(), 0);
        assertEquals(MESSAGE_MOVE_RIGHT, move.getRightMove(), 0);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}

