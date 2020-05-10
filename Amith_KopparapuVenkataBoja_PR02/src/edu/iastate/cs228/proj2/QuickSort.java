package edu.iastate.cs228.proj2;

import java.util.Comparator;

public class QuickSort extends SorterWithStatistics {

	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		quickSort(words,comp,0,words.length-1);
	}
	/**
	 * does the quick sort algorithm for this scenario in a recursive way
	 * @param arr
	 * 		the array that needs to be sorted
	 * @param comp
	 * 		the Comparater used to compare to strings
	 * @param low
	 * 		the start index start the checking from
	 * @param high
	 * 		the last index to check
	 */
	private void quickSort(String[] arr, Comparator<String> comp, int low, int high) {
		if (arr != null && arr.length >0 &&low < high) {
			int middle = low + (high - low) / 2;
			String pivot = arr[middle];
			int i = low;
			int j = high;
			while (i <= j) {
				while (comp.compare(arr[i], pivot) > 0) {
					i++;
				}
	 
				while (comp.compare(arr[j], pivot) < 0) {
					j--;
				}
	 
				if (i <= j) {
					String temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
			}
			if (low < j) {
				quickSort(arr, comp, low, j);
			}
	 
			if (high > i) {
				quickSort(arr, comp, i, high);
			}
		}
	}
}
