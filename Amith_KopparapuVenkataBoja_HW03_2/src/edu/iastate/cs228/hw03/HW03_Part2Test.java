package edu.iastate.cs228.hw03;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the HW03_Part2 class.
 * @author Kyle Vetsch
 */
public class HW03_Part2Test {
    /** Missing an element in range. */
    private int[] fm;

    /** Not missing an element in range. */
    private int[] fm2;

    /** Element missing from fm. */
    private int missing;

    /** Element missing from fm2. */
    private int missing2;

    /** Unsorted array with negatives and positives mixed. */
    private int[] unsorted;

    /**
     * Array with negatives before positives
     * (although not necessarily fully sorted).
     */
    private int[] semisorted;

    /**
     * Set up test fixture.
     */
    @Before
    public void setUp() {
        this.fm = new int[] {1, 2, 4, 5, 6};
        this.fm2 = new int[] {1, 2, 3};
        this.missing = 3;
        this.missing2 = 4;
        this.unsorted = new int[] {1, -5, -4, 2, 4, -13, 15, -2};
        this.semisorted = new int[] {-5, -4, -13, -2, 1, 2, 4, 15};
    }

    /**
     * Tear down test fixture.
     */
    @After
    public void tearDown() {
        this.fm = null;
        this.fm2 = null;
        this.missing = 0;
        this.missing2 = 0;
        this.unsorted = null;
        this.semisorted = null;
    }

    /**
     * Test findMissingInt_a_On2 method.
     */
    @Test
    public void findMissingInt_a_On2() {
        final int ret = HW03_Part2.findMissingInt_a_On2(this.fm);
        assertEquals(this.missing, ret);

        final int none = HW03_Part2.findMissingInt_a_On2(this.fm2);
        assertEquals(this.missing2, none);
    }

    /**
     * Test findMissingInt_b_On1 method.
     */
    @Test
    public void findMissingInt_b_On1() {
        final int ret = HW03_Part2.findMissingInt_b_On1(this.fm);
        assertEquals(this.missing, ret);

        final int none = HW03_Part2.findMissingInt_b_On1(this.fm2);
        assertEquals(this.missing2, none);
    }

    /**
     * Test rearrange_a_On2 method.
     */
    @Test
    public void rearrange_a_On2() {
        this.unsorted= HW03_Part2.rearrange_a_On2(this.unsorted);
        Arrays.sort(this.semisorted);
        assertArrayEquals(this.semisorted, this.unsorted);
    }

    /**
     * Test rearrange_b_On1 method.
     */
    @Test
    public void rearrange_b_On1() {
        HW03_Part2.rearrange_b_On1(this.unsorted);
        assertArrayEquals(this.semisorted, this.unsorted);
    }
}
