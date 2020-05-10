package edu.iastate.cs228.hw03;

import static org.junit.Assert.assertArrayEquals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the DoublyLinkedBag class.
 * @author Kyle Vetsch
 */
public class DoublyLinkedBagTest {
    /** Standard bag of Integers. */
    private DoublyLinkedBag<Integer> b;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        this.b = new DoublyLinkedBag<>();
    }

    /**
     * Tear down test fixture.
     */
    @After
    public void tearDown() {
        this.b = null;
    }

    /**
     * Test constructor.
     */
    @Test
    public void doublyLinkedBag() {
        new DoublyLinkedBag<Integer>();
    }

    /**
     * Test add method.
     */
    @Test
    public void add() {
        this.b.add(1);
        this.b.add(2);
        this.b.add(2);

        assertEquals(1, this.b.getFrequencyOf(1));
        assertEquals(2, this.b.getFrequencyOf(2));
    }

    /**
     * Test toArray method.
     */
    @Test
    public void toArray() {
        final Integer[] array = new Integer[] {1, 1, 1};

        this.b.add(1);
        this.b.add(1);
        this.b.add(1);

        assertArrayEquals(array, this.b.toArray());
    }

    /**
     * Test isEmpty method.
     */
    @Test
    public void isEmpty() {
        assertTrue(this.b.isEmpty());

        b.add(1);

        assertFalse(this.b.isEmpty());
    }

    /**
     * Test getCurrentSize method.
     */
    @Test
    public void getCurrentSize() {
        b.add(1);
        b.add(2);

        assertEquals(2, this.b.getCurrentSize());
    }

    /**
     * Test getFrequenceOf method.
     */
    @Test
    public void getFrequencyOf() {
        this.b.add(1);
        this.b.add(1);
        this.b.add(2);
        this.b.add(3);
        this.b.add(3);
        this.b.add(3);

        assertEquals(0, this.b.getFrequencyOf(0));
        assertEquals(2, this.b.getFrequencyOf(1));
        assertEquals(1, this.b.getFrequencyOf(2));
        assertEquals(3, this.b.getFrequencyOf(3));
    }

    /**
     * Test contains method.
     */
    @Test
    public void contains() {
        assertFalse(this.b.contains(1));

        this.b.add(1);
        this.b.add(2);

        assertTrue(this.b.contains(1));
        assertTrue(this.b.contains(2));
    }

    /**
     * Test clear method.
     */
    @Test
    public void clear() {
        this.b.add(1);
        this.b.add(1);
        this.b.add(2);

        this.b.clear();

        assertTrue(b.isEmpty());
    }

    /**
     * Test remove() method.
     */
    @Test
    public void remove() {
        this.b.add(1);
        this.b.add(1);
        final Integer i = this.b.remove();

        assertEquals(1, this.b.getFrequencyOf(1));
        assertEquals(1, this.b.getCurrentSize());
        assertEquals(Integer.valueOf(1), i);
    }

    /**
     * Test remove(entry) method.
     */
    @Test
    public void removeEntry() {
        this.b.add(1);
        this.b.add(1);
        this.b.add(2);

        assertTrue(this.b.remove(1));
        assertFalse(this.b.remove(3));
    }

    /**
     * Test replace method.
     */
    @Test
    public void replace() {
        assertEquals(null, this.b.replace(1));
        this.b.add(1);
        this.b.add(1);
        this.b.add(1);

        final Integer i = this.b.replace(2);

        assertEquals(Integer.valueOf(1), i);
        assertEquals(2, this.b.getFrequencyOf(1));
        assertEquals(1, this.b.getFrequencyOf(2));
    }

    /**
     * Test removeEvery method.
     */
    @Test
    public void removeEvery() {
        this.b.add(1);
        this.b.add(1);
        this.b.add(2);

        this.b.removeEvery(1);

        assertEquals(0, this.b.getFrequencyOf(1));
        assertEquals(1, this.b.getFrequencyOf(2));
    }

    /**
     * Test equals method.
     */
    @Test
    public void equals() {
        assertFalse(this.b.equals(new char[] {}));
        assertFalse(this.b.equals(null));

        assertTrue(this.b.equals(this.b));

        final DoublyLinkedBag<Integer> bag = new DoublyLinkedBag<>();

        this.b.add(1);
        bag.add(1);
        bag.add(1);

        assertFalse(this.b.equals(bag));
        assertFalse(bag.equals(this.b));

        this.b.add(1);
        this.b.add(3);
        bag.add(3);

        assertTrue(this.b.equals(bag));
        assertTrue(bag.equals(this.b));

        this.b.remove(1);
        bag.remove(3);

        assertFalse(this.b.equals(bag));
        assertFalse(bag.equals(this.b));
    }

    /**
     * Test toString method.
     */
    @Test
    public void toStringTest() {
        assertEquals("[]", this.b.toString());

        this.b.add(1);

        assertEquals("[1]", this.b.toString());

        this.b.add(3);

        final String str = this.b.toString();

        assertTrue(str.equals("[1, 3]")
                || str.equals("[3, 1]"));
    }
}
