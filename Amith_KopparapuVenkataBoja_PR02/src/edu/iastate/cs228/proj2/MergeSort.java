package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author 
 * Amith Kopparapu Venkata Boja
 *	After compiling my code once for 100 words, I found my times for sorts as follows:
 *		QuickSort(Oh(n^2)): 3926470
		MergeSort(n*log(n)): 580362
		SelectionSort(Oh(n^2)): 6906243
	When I said 3927470 as time modeled with a Oh(n^2) for quick sort, I meant that this time is modeled 
	as a funtion of ax^2 +bx+c and the similar goes for Merge Sort and Selection sorts for their respective 
	Big Ohs and their times.
 */
public class MergeSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		if(words.length>1) {
	        int length = words.length;
	        String[] a = new String[length/2];
	        String[] b = new String[length - length/2];
	        for (int i = 0; i < a.length; i++) {
	            a[i] = words[i];
	        }
	        for (int i = 0; i < b.length; i++) {
	            b[i] = words[i + length/2];
	        }
	        sortHelper(a,comp);
	        sortHelper(b,comp);
	        merge(a,b,words,comp);
	        
		}
	}
	/**
	 *  merges the two arrays by making a brand new sorted array
	 * @param a
	 * 		first half that is sorted
	 * @param b
	 * 		second half that is sorted
	 * @return
	 * 		an array of strings that have the contents in a and b merged and sorted
	 */
	private void merge(String[] a, String[] b, String[] merged, Comparator<String> comp) {
		int j = 0;   
        int k = 0;   
        
        for (int i = 0; i < merged.length; i++) {
            if (k >= b.length || (j < a.length && comp.compare(a[j], b[k])>= 0)) {
                merged[i] = a[j];   
                j++;
            } else {
                merged[i] = b[k];  
                k++;
            }
        }
	}

}
