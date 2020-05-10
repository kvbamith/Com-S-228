package edu.iastate.cs228.hw08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Kyle Vetsch
 */
public class LinkedDictionaryTest {
    /**
     * LinkedDictionary.
     */
    private LinkedDictionary<String, Integer> map;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        map = new LinkedDictionary<>();
    }

    /**
     * Tear down test fixture.
     */
    @After
    public void tearDown() {
        map = null;
    }

    /**
     * Test add.
     */
    @Test
    public void add() {
        assertNull(map.add("red", 1 << 1));
        assertNull(map.add("ora", 1 << 2));
        assertNull(map.add("gre", 1 << 4));
        assertNull(map.add("yel", 1 << 3));

        assertTrue(map.contains("red"));
        assertTrue(map.contains("ora"));
        assertTrue(map.contains("gre"));
        assertTrue(map.contains("yel"));

        assertEquals(Integer.valueOf(2), map.add("red", 1 << 5));

        assertEquals(Integer.valueOf(32), map.getValue("red"));

        assertEquals(4, map.getSize());
    }

    /**
     * Test remove.
     */
    @Test
    public void remove() {
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);
        map.add("yel", 1 << 3);
        map.add("gre", 1 << 4);

        assertEquals(Integer.valueOf(8), map.remove("yel"));
        assertNull(map.remove("blu"));
    }

    /**
     * Test getValue.
     */
    @Test
    public void getValue() {
        map.add("yel", 1 << 3);
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);

        assertEquals(Integer.valueOf(8), map.getValue("yel"));
        assertEquals(Integer.valueOf(2), map.getValue("red"));
        assertEquals(Integer.valueOf(4), map.getValue("ora"));
    }

    /**
     * Test contains.
     */
    @Test
    public void contains() {
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);

        assertTrue(map.contains("red"));
        assertFalse(map.contains("blu"));
    }

    /**
     * Test isEmpty.
     */
    @Test
    public void isEmpty() {
        assertTrue(map.isEmpty());

        map.add("red", 1 << 1);

        assertFalse(map.isEmpty());
    }

    /**
     * Test getSize.
     */
    @Test
    public void getSize() {
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);

        assertEquals(2, map.getSize());
    }

    /**
     * Test clear.
     */
    @Test
    public void clear() {
        map.add("red", 1 << 1);

        map.clear();

        assertTrue(map.isEmpty());
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString() {
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);

        assertTrue(map.toString().equals("[(ora:4), (red:2)]")
                || map.toString().equals("[(red:2), (ora:4)]"));

        map.add("red", 1 << 3);

        assertTrue(map.toString().equals("[(ora:4), (red:8)]")
                || map.toString().equals("[(red:8), (ora:4)]"));
    }

    /**
     * Test getKeyIterator.
     */
    @Test
    public void getKeyIterator() {
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);
        map.add("yel", 1 << 3);
        map.add("gre", 1 << 4);

        final String[] keys = new String[]{"gre", "ora", "red", "yel"};

        final Iterator iter = map.getKeyIterator();

        while (iter.hasNext()) {
            assertTrue(Arrays.stream(keys).anyMatch(iter.next()::equals));
        }
    }

    /**
     * Test getValueIterator.
     */
    @Test
    public void getValueIterator() {
        map.add("red", 1 << 1);
        map.add("ora", 1 << 2);
        map.add("yel", 1 << 3);
        map.add("gre", 1 << 4);

        final Integer[] values = new Integer[]{16, 4, 2, 8};

        final Iterator it = map.getValueIterator();

        while (it.hasNext()) {
            assertTrue(Arrays.stream(values).anyMatch(it.next()::equals));
        }
    }
}