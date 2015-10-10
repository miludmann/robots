package orwell.tank.elements;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Michaël Ludmann on 6/18/15.
 */
@RunWith(JUnit4.class)
public class SoundSpeakerTest {
    private final static Logger logback = LoggerFactory.getLogger(SoundSpeakerTest.class);
    private final static int PAUSE_MS = 1000;

    @Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
    }

    @Test
    public void testPause() throws InterruptedException {
        SoundSpeaker soundSpeaker = new SoundSpeaker();
        soundSpeaker.pause(PAUSE_MS);
        Thread.sleep(PAUSE_MS);
    }

    @After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}

