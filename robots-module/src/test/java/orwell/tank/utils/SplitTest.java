package orwell.tank.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
@RunWith(JUnit4.class)
public class SplitTest {
    private final static Logger logback = LoggerFactory.getLogger(SplitTest.class);
    private static final char SEPARATOR = ' ';

    @Before
    public void setUp() throws Exception {
        logback.debug(">>>>>>>>> IN");
    }

    @Test
    public void testSplit_cSEPc_limit3() throws Exception {
        final String string = "x y";
        final List<String> list = Split.split(SEPARATOR, string, 3);
        assertEquals(2, list.size());
        assertEquals("x", list.get(0));
        assertEquals("y", list.get(1));
    }

    @Test
    public void testSplit_cSEPc_limit2() throws Exception {
        final String string = "x y";
        final List<String> list = Split.split(SEPARATOR, string, 2);
        assertEquals(2, list.size());
        assertEquals("x", list.get(0));
        assertEquals("y", list.get(1));
    }

    @Test
    public void testSplit_cSEPc_limit1() throws Exception {
        final String string = "x y";
        final List<String> list = Split.split(SEPARATOR, string, 1);
        assertEquals(1, list.size());
        assertEquals("x", list.get(0));
    }

    @Test
    public void testSplit_cSEPc_limit0() throws Exception {
        final String string = "x y";
        final List<String> list = Split.split(SEPARATOR, string, 0);
        assertEquals(0, list.size());
    }

    @Test
    public void testSplit_SEPc_limit2() throws Exception {
        final String string = " y";
        final List<String> list = Split.split(SEPARATOR, string, 2);
        assertEquals(2, list.size());
        assertEquals("", list.get(0));
        assertEquals("y", list.get(1));
    }

    @Test
    public void testSplit_cSEP_limit2() throws Exception {
        final String string = "x ";
        final List<String> list = Split.split(SEPARATOR, string, 2);
        assertEquals(2, list.size());
        assertEquals("x", list.get(0));
        assertEquals("", list.get(1));
    }

    @Test
    public void testSplit_SEP_limit2() throws Exception {
        final String string = " ";
        final List<String> list = Split.split(SEPARATOR, string, 2);
        assertEquals(2, list.size());
        assertEquals("", list.get(0));
        assertEquals("", list.get(1));
    }

    @Test
    public void testSplit_cSEPSEP_limit3() throws Exception {
        final String string = "x  ";
        final List<String> list = Split.split(SEPARATOR, string, 3);
        assertEquals(3, list.size());
        assertEquals("x", list.get(0));
        assertEquals("", list.get(1));
        assertEquals("", list.get(2));
    }

    @Test
    public void testSplit_SEPcSEP_limit3() throws Exception {
        final String string = " x ";
        final List<String> list = Split.split(SEPARATOR, string, 3);
        assertEquals(3, list.size());
        assertEquals("", list.get(0));
        assertEquals("x", list.get(1));
        assertEquals("", list.get(2));
    }

    @Test
    public void testSplit_SEPSEP_limit3() throws Exception {
        final String string = "  ";
        final List<String> list = Split.split(SEPARATOR, string, 3);
        assertEquals(3, list.size());
        assertEquals("", list.get(0));
        assertEquals("", list.get(1));
        assertEquals("", list.get(2));
    }

    @Test
    public void testSplit_cSEPSEPc_limit3() throws Exception {
        final String string = "x  y";
        final List<String> list = Split.split(SEPARATOR, string, 3);
        assertEquals(3, list.size());
        assertEquals("x", list.get(0));
        assertEquals("", list.get(1));
        assertEquals("y", list.get(2));
    }

    @Test
    public void testSplit_cc_limit2() throws Exception {
        final String string = "xy";
        final List<String> list = Split.split(SEPARATOR, string, 2);
        assertEquals(1, list.size());
        assertEquals(string, list.get(0));
    }

    @Test
    public void testSplit_ccSEPcccSEPcc_limit3() throws Exception {
        final String string = "xy abc de";
        final List<String> list = Split.split(SEPARATOR, string, 3);
        assertEquals(3, list.size());
        assertEquals("xy", list.get(0));
        assertEquals("abc", list.get(1));
        assertEquals("de", list.get(2));
    }

    @After
    public void tearDown() throws Exception {
        logback.debug("<<<< OUT");
    }
}

