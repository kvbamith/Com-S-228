package edu.iastate.cs228.hw07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;


/**
 * @author Kyle Vetsch
 * Test SearchingExercises.
 */
public class SearchingExercisesTest {
    /**
     * Test findMinInterval().
     */
    @Test
    public void findMinInterval() {
        final Integer[] sorted = new Integer[]{5, 8, 10, 13, 15, 20, 22, 26};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(8);
        values.add(2);
        values.add(9);
        values.add(17);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 5}, ret);
    }

    /**
     * Test find2D().
     */
    @Test
    public void find2D() {
        final Integer[][] arr = new Integer[][]{
                {1, 4, 55, 88},
                {7, 15, 61, 91},
                {14, 89, 90, 99},
        };
        assertTrue(SearchingExercises.find2D(arr, 61));
        assertFalse(SearchingExercises.find2D(arr, 95));

        // corners
        assertTrue(SearchingExercises.find2D(arr, 1));
        assertTrue(SearchingExercises.find2D(arr, 88));
        assertTrue(SearchingExercises.find2D(arr, 14));
        assertTrue(SearchingExercises.find2D(arr, 99));
    }
}