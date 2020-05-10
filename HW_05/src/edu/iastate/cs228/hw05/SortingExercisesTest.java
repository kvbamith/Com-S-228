package edu.iastate.cs228.hw05;

import static org.junit.Assert.assertArrayEquals;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * I don't bother checking the conditions such as array == null. Those should be
 * fine if you don't change them.
 *
 * @author Kyle Vetsch
 */
public class SortingExercisesTest {
    /** Unsorted array. */
    private int[] array;

    /** Sorted array. */
    private int[] sorted;

    /** Set up test fixture. */
    @Before
    public void setUp() {
        this.array = new int[] {
                5, 94, 55, 22, 47,
                86, 47, 67, 79, 95,
                67, 30, 54, 28, 71,
                49, 52, 49, 95, 28,
                71, 92, 96, 60, 93,
                84, 69, 81, 9, 12,
                29, 49, 54, 7, 29,
                33, 17, 63, 95, 78,
                74, 33, 42, 60, 41,
                27, 51, 3, 8, 92,
                78, 83, 9, 31, 10,
                100, 47, 3, 94, 95,
                32, 69, 17, 45, 60,
                58, 90, 76, 38, 92,
                72, 83, 24, 68, 75,
                15, 6, 55, 5, 5,
                12, 91, 21, 69, 47,
                71, 1, 67, 58, 80,
                46, 80, 11, 26, 98,
                20, 78, 65, 5, 27
        };
        this.sorted = new int[] {
                1, 3, 3, 5, 5,
                5, 5, 6, 7, 8,
                9, 9, 10, 11, 12,
                12, 15, 17, 17, 20,
                21, 22, 24, 26, 27,
                27, 28, 28, 29, 29,
                30, 31, 32, 33, 33,
                38, 41, 42, 45, 46,
                47, 47, 47, 47, 49,
                49, 49, 51, 52, 54,
                54, 55, 55, 58, 58,
                60, 60, 60, 63, 65,
                67, 67, 67, 68, 69,
                69, 69, 71, 71, 71,
                72, 74, 75, 76, 78,
                78, 78, 79, 80, 80,
                81, 83, 83, 84, 86,
                90, 91, 92, 92, 92,
                93, 94, 94, 95, 95,
                95, 95, 96, 98, 100
        };
    }

    /** Tear down test fixture. */
    @After
    public void tearDown() {
        this.array = null;
        this.sorted = null;
    }

    /** Test selectionSort_Rec(). */
    @Test
    public void selectionSort_Rec() {
        SortingExercises.selectionSort_Rec(this.array);
        assertArrayEquals(this.sorted, this.array);
    }

    /** Test insertionSort_Rec(). */
    @Test
    public void insertionSort_Rec() {
        SortingExercises.insertionSort_Rec(this.array);
        assertArrayEquals(this.sorted, this.array);
    }

    /** Test selectionSort_Itr(). */
    @Test
    public void selectionSort_Itr() {
        SortingExercises.selectionSort_Itr(this.array);
        assertArrayEquals(this.sorted, this.array);
    }

    /** Test bubbleSort_Itr(). */
    @Test
    public void bubbleSort_Itr() {
        LocalDate[] dates = new LocalDate[] {
                LocalDate.of(2018, 3, 4),
                LocalDate.of(2218, 3, 4),
                LocalDate.of(218, 3, 4),
                LocalDate.of(218, 4, 3),
                LocalDate.of(208, 3, 12),
                LocalDate.of(2017, 3, 4),
                LocalDate.of(218, 1, 22),
                LocalDate.of(581, 5, 11),
                LocalDate.of(841, 8, 23),
                LocalDate.of(941, 3, 13),
        };

        LocalDate[] datesSorted = dates.clone();

        Arrays.sort(datesSorted);
        SortingExercises.bubbleSort_Itr(dates);

        assertArrayEquals(datesSorted, dates);
    }

    /** Test bubbleSort_Rec(). */
    @Test
    public void bubbleSort_Rec() {
        SortingExercises.bubbleSort_Rec(this.array);
        assertArrayEquals(this.sorted, this.array);
    }
}