package edu.iastate.cs228.proj2;

import java.util.Comparator;

public abstract class SorterWithStatistics implements Sorter {

	private Stopwatch timer = new Stopwatch();
	/**
	 * records the time when the process is over
	 */
	private long endTime= 0L;
	/**
	 * calculates the total time for sorting
	 */
	private long totalTime=0L;
	/***
	 * Default constructor
	 */
	public SorterWithStatistics() {
		
	}

	/***
	 * Public interface to sortHelper that keeps track of performance
	 * statistics, including counting words sorted and timing sort instances.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	public void sort(String[] words, Comparator<String> comp) {
		timer.start();
		sortHelper(words,comp);
		timer.stop();
		endTime=timer.getElapsedTime();
		totalTime  += endTime;
	}

	/**
	 * Sorts the array words.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	protected abstract void sortHelper(String[] words, Comparator<String> comp);

	/**
	 * Returns number of words sorted in last sort. Throws IllegalStateException
	 * if nothing has been sorted.
	 * 
	 * @return number of words sorted in last sort.
	 */
	public int getWordsSorted() {
		// TODO
		return 0;
	}

	/**
	 * Returns time the last sort took. Throws IllegalStateException if nothing
	 * has been sorted.
	 * 
	 * @return time last sort took.
	 */
	public long getTimeToSortWords() {
		// TODO
		return 0L;
	}

	/**
	 * Returns total words sorted by this instance.
	 * 
	 * @return total number of words sorted.
	 */
	public int getTotalWordsSorted() {
		// TODO
		return -1;
	}

	/**
	 * Returns the total amount of time spent sorting by this instance.
	 * 
	 * @return total time spent sorting.
	 */
	public long getTotalTimeToSortWords() {
		return totalTime;
	}

	/**
	 * @return a summary of statistics for the last sorting run.
	 * 
	 *         See the project description for details about what to include.
	 *         This method should NOT generate output directly.
	 */
	public String getReport() {
		return this.getClass().getSimpleName() + ": " + totalTime/1000.0;
	}
}
