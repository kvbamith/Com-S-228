package edu.iastate.cs228.hw05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Kyle Vetsch
 */
public class ArrayPriorityQueueTest {
    /**
     * Priority queue.
     */
    private ArrayPriorityQueue<Integer> pq;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        pq = new ArrayPriorityQueue<>();

        pq.add(1);
        pq.add(3);
        pq.add(5);
        pq.add(2);
        pq.add(-1);
        pq.add(7);
    }

    /**
     * Tear down test fixture.
     */
    @After
    public void tearDown() {
        pq = null;
    }

    /**
     * Test add() method.
     * Relies upon correctness of toString().
     */
    @Test
    public void add() {
	// NOTE: This behavior is not defined in the assignment
	// I just use it to test my implementation.
        pq.add(null); // no-op

        assertEquals("[-1, 1, 2, 3, 5, 7]", pq.toString());
    }

    /**
     * Test remove() method.
     */
    @Test
    public void remove() {
        assertEquals(Integer.valueOf(7), pq.remove());
        assertEquals(Integer.valueOf(5), pq.remove());
        assertEquals(Integer.valueOf(3), pq.remove());
        assertEquals(Integer.valueOf(2), pq.remove());
        assertEquals(Integer.valueOf(1), pq.remove());
        assertEquals(Integer.valueOf(-1), pq.remove());
        assertEquals(null, pq.remove());
    }

    /**
     * Test peek() method.
     * Relies upon correctness of remove() method.
     */
    @Test
    public void peek() {
        assertEquals(Integer.valueOf(7), pq.peek());
        pq.remove();
        assertEquals(Integer.valueOf(5), pq.peek());
        pq.remove();
        assertEquals(Integer.valueOf(3), pq.peek());
        pq.remove();
        assertEquals(Integer.valueOf(2), pq.peek());
        pq.remove();
        assertEquals(Integer.valueOf(1), pq.peek());
        pq.remove();
        assertEquals(Integer.valueOf(-1), pq.peek());
        pq.remove();
        assertEquals(null, pq.peek());
    }

    /**
     * Test toString() method.
     * Relies upon correctness of clear() and add().
     */
    @Test
    public void toStringTest() {
        assertEquals("[-1, 1, 2, 3, 5, 7]", pq.toString());
        pq.clear();
        assertEquals("[]", pq.toString());
        pq.add(-15);
        assertEquals("[-15]", pq.toString());
    }

    /**
     * Test isEmpty() method.
     * Relies upon correctness of clear() and add().
     */
    @Test
    public void isEmpty() {
        assertFalse(pq.isEmpty());
        pq.clear();
        assertTrue(pq.isEmpty());
    }

    /**
     * Test getSize() method.
     * Relies upon correctness of add() and remove().
     */
    @Test
    public void getSize() {
        pq.add(-15);
        assertEquals(7, pq.getSize());
        pq.remove();
        assertEquals(6, pq.getSize());
    }

    /**
     * Test clear() method.
     * Relies upon correctness of isEmpty().
     */
    @Test
    public void clear() {
        pq.clear();
        assertTrue(pq.isEmpty());
    }
}