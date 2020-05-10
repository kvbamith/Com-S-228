package edu.iastate.cs228.hw06;

import java.util.Comparator;

/**
 * 
 * @author
 * Amith Kopparapu Venkata Boja
 * NOTE:
 * 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 2. No data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the source codes provided
 *    or shown under lecture notes, which do not violate any of above.
 *    
 *    
 * 
 */


public class SortingExercise
{
	/**
	 * Modified implementation of in class provided quick sort code.
	 * 
	 * 
	 * The implementation of our original quick sort needs to be
	 * revised as follows in this implementation. If the array has 
	 * 23 entries, choose the middle entry as the pivot. For arrays
	 * between 24 - 50 use the last element as the pivot value. For 
	 * arrays larger than 50 entries, use the median-of-three 
	 * pivot-selection scheme described below. For arrays fewer than 
	 * 23 entries, use insertion sort instead of quick sort.
	 * 
	 * Median-of-three pivot selection chooses as pivot the median of
	 * three entries in the array, i.e., the first entry, the middle 
	 * entry, and the last entry. We will use specific version of it
	 * as follows. 
	 * 
	 * For example, let's say original array is as follows
	 * 
	 *  5, 8, 6, 4, 9, 3, 7, 1, 2
	 * 
	 * first entry = 5
	 * middle entry = 9 // index is (0+8)/2=4
	 * last entry = 2
	 * 
	 * Median of 5, 9, 2, would be 5.
	 * Check: https://en.wikipedia.org/wiki/Median
	 * 
	 * Now our array would look as follows after positioning the pivot:
	 * 
	 *  2, 8, 6, 4, 5, 3, 7, 1, 9
	 * 
	 * Now our array would look as follows just before partitioning:
	 * 
	 *  2, 5, 6, 4, 8, 3, 7, 1, 9
	 *  
	 * Our pivot is at position 1 of array, i.e., value 5.
	 * Both low and high start as shown in source code of quick sort under
	 * lecture notes, i.e., 
	 * 
	 * int low = first + 1;
	 * int high = last;
	 * 
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void modifiedQuickSort(int[] arr)
 {
  if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  if(arr.length>= 23) {
	  quickSort(arr);
  }else {
	  insertionSort(arr);
  }
  
 }
 public static void quickSort(int[] list)
	{
		if (list == null || list.length == 0)
			throw new RuntimeException("Null pointer or zero size");
		if (list.length == 1)
			return;


		quickSort(list, 0, list.length - 1);
	}

	public static void quickSort(int[] list, int first, int last)
	{
		if (last > first)
		{
			int pivotIndex=list[first];
			int middle=(last + first) / 2;
			if(list.length==23) {
				pivotIndex=list[middle];
			}else if(list.length>23 && list.length<=50) {
				pivotIndex=list[last];
			}else {
				int median=(list[first]+list[middle]+list[last])/3;
				int i=0;
				for(i=0;i<list.length;i++) {
					if(list[i]==median) {
						pivotIndex=i;
						break;
					}
				}
				int temp=list[middle];
				list[middle]=median;
				list[i]=temp;
				if(list[first]>list[last]) {
					int t=list[first];
					list[first]=list[last];
					list[last]=t;
				}
			}
			pivotIndex = partition(list, first, last, pivotIndex);

			
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	
	
	public static int partition(int[] list, int first, int last, int pivot)
	{
		pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low)
		{
			// Search forward from left
			while (low <= high && list[low] <= pivot) low++;

			// Search backward from right
			while (low <= high && list[high] > pivot) high--;

			// Swap two elements in the list
			if (high > low)
			{
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high] >= pivot) high--;

		// Swap pivot with list[high]
		if (pivot > list[high])
		{
			list[first] = list[high];
			list[high] = pivot;			
			return high;
		} 
		else
		{
			return first;
		}
	} // end partition
 /**
  * Recursive implementation of insertion sort.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
private static void insertionSort(int[] arr){
 	if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  if(arr.length == 2) {
	  if(arr[0]>arr[1]) {
		  int temp=arr[1];
		  arr[1]=arr[0];
		  arr[0]=temp;
	  }
  }else { 
	  int[] t= new int[arr.length-1];
	  for(int k=0;k<t.length;k++) {
		  t[k]=arr[k];
	  }
	  insertionSort(t);
	  for(int i=0;i<t.length;i++) {
		  arr[i]=t[i];
	  }
	  for(int i=0;i<arr.length-1;i++) {
		  if(arr[arr.length-1]<arr[i]) {
			  int temp=arr[arr.length-1];
			  for(int j=arr.length-1;j>i;j--) {
				  arr[j]=arr[j-1];
			  }
			  arr[i]=temp;
		  }
	  }
  }
 }
}
