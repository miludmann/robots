package orwell.tank.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Michaël Ludmann on 8/26/15.
 */
@RunWith(JUnit4.class)
public class IniFileTest {
    private final static String INI_FILE_NAME = "/tank.test.ini";
    private final static String RESOURCE_DIRECTORY = "src/test/resources";
    private final static Logger logback = LoggerFactory.getLogger(IniFileTest.class);
    private static final String LEFT_MOTOR_PORT_FIELD = "leftMotorPort";
    private static final String LEFT_MOTOR_PORT_VALUE = "C";
    private static final String IS_LEFT_MOTOR_INVERTED_FIELD = "isLeftMotorInverted";
    private static final boolean IS_LEFT_MOTOR_INVERTED_VALUE = true;
    private static final String RFID_SENSOR_PORT_FIELD = "rfidSensorPort";
    private static final int RFID_SENSOR_PORT_VALUE = 2;
    private static final String DEFAULT_STRING = "DefaultString";
    private static final boolean DEFAULT_BOOLEAN = false;
    private static final int DEFAULT_INT = 7;
    private IniFile iniFile;

    @Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
        File resourcesDirectory = new File(RESOURCE_DIRECTORY);
        iniFile = new IniFile(resourcesDirectory.toString().concat(INI_FILE_NAME));
    }

    @Test
    public void testGetProperty() throws Exception {
        assertEquals(LEFT_MOTOR_PORT_VALUE, iniFile.getProperty(LEFT_MOTOR_PORT_FIELD));
    }

    @Test
    public void testGetProperty_Default() throws Exception {
        assertNotEquals(LEFT_MOTOR_PORT_FIELD + " exists in the file", DEFAULT_STRING, iniFile.getProperty(LEFT_MOTOR_PORT_FIELD, DEFAULT_STRING));
        assertEquals(DEFAULT_STRING, iniFile.getProperty("fieldDoesNotExist", DEFAULT_STRING));
    }

    @Test
    public void testGetBoolean() throws Exception {
        assertEquals(IS_LEFT_MOTOR_INVERTED_VALUE, iniFile.getBoolean(IS_LEFT_MOTOR_INVERTED_FIELD));
    }

    @Test
    public void testGetBoolean_Default() throws Exception {
        assertNotEquals(IS_LEFT_MOTOR_INVERTED_FIELD + " exists in the file", DEFAULT_BOOLEAN, iniFile.getBoolean(IS_LEFT_MOTOR_INVERTED_FIELD, DEFAULT_BOOLEAN));
        assertEquals(DEFAULT_BOOLEAN, iniFile.getBoolean("fieldDoesNotExist", DEFAULT_BOOLEAN));
    }

    @Test
    public void testGetInt() throws Exception {
        assertEquals(RFID_SENSOR_PORT_VALUE, iniFile.getInt(RFID_SENSOR_PORT_FIELD));
    }

    @Test
    public void testGetInt_Default() throws Exception {
        assertEquals(DEFAULT_INT, iniFile.getInt("fieldDoesNotExist", DEFAULT_INT));
    }

    @After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}
