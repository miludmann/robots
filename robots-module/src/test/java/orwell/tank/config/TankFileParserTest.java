package orwell.tank.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.File;

import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.easymock.EasyMock.expect;

import static org.junit.Assert.*;

/**
 * Created by Michaël Ludmann on 8/28/15.
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(File.class)
public class TankFileParserTest {
    private final static Logger logback = LoggerFactory.getLogger(TankFileParserTest.class);
    private TankFileParser tankFileParser;


    @Before
    public void setUp() throws Exception {
//        logback.debug(">>>>>>>>> IN");
//        mockStatic(File.class);
//        expect(File.listFiles()).andReturn(new File[]{new File("config.tank.ini")});
//        replayAll();
//        tankFileParser = new TankFileParser();
    }

    @Test
    public void testParse() throws Exception {
//        tankFileParser.parse();

    }

    @After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}

