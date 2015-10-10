package orwell.tank.elements;

import lejos.nxt.NXTRegulatedMotor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.easymock.EasyMock.*;

/**
 * Created by Michaël Ludmann on 6/15/15.
 */
@RunWith(JUnit4.class)
public class DrivingTracksRegulatedTest {
    private final static Logger logback = LoggerFactory.getLogger(DrivingTracksRegulatedTest.class);
    private static final Float MAX_SPEED = 900f;
    private NXTRegulatedMotor leftMotor;
    private NXTRegulatedMotor rightMotor;
    private DrivingTracksRegulated tracksRegulated;
    private final static double POWER_LEFT_POSITIVE = 50.5;
    private final static double POWER_RIGHT_POSITIVE = 100;
    private final static double POWER_LEFT_NEGATIVE = -50.5;
    private final static double POWER_RIGHT_NEGATIVE = -100;

    @Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
        leftMotor = createNiceMock(NXTRegulatedMotor.class);
        rightMotor = createNiceMock(NXTRegulatedMotor.class);
        expect(leftMotor.getMaxSpeed()).andReturn(MAX_SPEED).anyTimes();
        expect(rightMotor.getMaxSpeed()).andReturn(MAX_SPEED).anyTimes();

        tracksRegulated = new DrivingTracksRegulated(leftMotor, rightMotor, false, false);
    }

    @Test
    public void testSetPower_Positive() throws Exception {
        leftMotor.backward();
        expectLastCall().once();
        replay(leftMotor);

        rightMotor.backward();
        expectLastCall().once();
        replay(rightMotor);

        tracksRegulated.setPower(POWER_LEFT_POSITIVE, POWER_RIGHT_POSITIVE);
        verify(leftMotor);
        verify(rightMotor);
    }

    @Test
    public void testSetPower_Negative() throws Exception {
        leftMotor.forward();
        expectLastCall().once();
        replay(leftMotor);

        rightMotor.forward();
        expectLastCall().once();
        replay(rightMotor);

        tracksRegulated.setPower(POWER_LEFT_NEGATIVE, POWER_RIGHT_NEGATIVE);
        verify(leftMotor);
        verify(rightMotor);
    }

    @Test
    public void testSetPower_Zero() throws Exception {
        leftMotor.flt(true);
        expectLastCall().once();
        replay(leftMotor);

        rightMotor.flt(true);
        expectLastCall().once();
        replay(rightMotor);

        tracksRegulated.setPower(0, 0);
        verify(leftMotor);
        verify(rightMotor);
    }

    @Test
    public void testSimulateRecoil_Positive() throws Exception {
        leftMotor.backward();
        expectLastCall().once();
        leftMotor.setSpeed(MAX_SPEED);
        expectLastCall().once();
        leftMotor.rotate(DrivingTracksRegulated.RECOIL_ROTATION_DEGREES);
        expectLastCall().once();
        replay(leftMotor);

        rightMotor.backward();
        expectLastCall().once();
        rightMotor.setSpeed(MAX_SPEED);
        expectLastCall().once();
        rightMotor.rotate(DrivingTracksRegulated.RECOIL_ROTATION_DEGREES);
        expectLastCall().once();
        replay(rightMotor);

        tracksRegulated.setPower(POWER_LEFT_POSITIVE, POWER_RIGHT_POSITIVE);
        tracksRegulated.simulateRecoil();
        verify(leftMotor);
        verify(rightMotor);
    }


    @After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}

