package edu.iastate.cs228.hw05;


/**
 * 
 * @author
 * Amith Kopparapu Venkata Boja
 * NOTE:
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. In all of these methods implementations you are allowed
 *    to use the StringBuilder class. 
 * 2. You are allowed to create and use your own private helper methods.
 * 3. No data fields can be introduced.
 * 4. No custom classes of your own can be introduced or used.
 * 5. Import statements are not allowed.
 * 6. Fully qualified class names usage is not allowed.
 * 7. You are allowed to reuse any part of the source codes provided
 *    or shown under lecture notes.
 * 
 */


public class SortingExercises
{
	/**
	 * Recursive implementation of selection sort.
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void selectionSort_Rec(int[] arr)
 {
  if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  if(arr.length >=2) {
	  int min=arr[0];
	  for(int i=0;i<arr.length;i++) {//finds the mininmum value
		  min=Math.min(min, arr[i]);
	  }
	  for(int i=0;i<arr.length;i++) {// swaps the min value and the first element
		  if(arr[i]==min) {
			  int temp=arr[0];
			  arr[0]=min;
			  arr[i]=temp;
		  }
	  }
	  int[] temp=new int[arr.length-1];
	  for(int i=1;i<arr.length;i++) {//makes an array containing elements from arr[1] to end
		  temp[i-1]=arr[i];
	  }
	  selectionSort_Rec(temp);//sorts just that piece
	  for(int i=1;i<arr.length;i++) {//adds it back to the original array
		  arr[i]=temp[i-1];
	  }
  }
  
    
 }
 
 /**
  * Recursive implementation of insertion sort.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
 public static void insertionSort_Rec(int[] arr)
 {
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
	  insertionSort_Rec(t);
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
 
 /**
	 * Iterative implementation of selection sort with modifications as follows.
	 * On each pass in this case the method finds both the largest and smallest
	 * values in the unsorted portion of the array, and places them in the correct
	 * locations.
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void selectionSort_Itr(int[] arr)
 {
  if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  if(arr.length >=2) {
	  int min=arr[0];
	  int max=arr[0];
	  for(int i=0;i<arr.length;i++) {//finds the minimum and maximum values
		  min=Math.min(min, arr[i]);
		  max=Math.max(max, arr[i]);
	  }
	  for(int i=0;i<arr.length;i++) {
		  if(arr[i]==min) {// swaps the minimum value and the first element
			  int temp=arr[0];
			  arr[0]=min;
			  arr[i]=temp;
		  }
		  if(arr[i]==max) {// swaps the maximum value and the last element
			  int temp=arr[arr.length-1];
			  arr[arr.length-1]=max;
			  arr[i]=temp;
		  }
	  }
	  int[] temp=new int[arr.length-1];
	  for(int i=1;i<arr.length;i++) {//makes an array containing elements from arr[1] to end
		  temp[i-1]=arr[i];
	  }
	  selectionSort_Itr(temp);//sorts just that piece
	  for(int i=1;i<arr.length;i++) {//adds it back to the original array
		  arr[i]=temp[i-1];
	  }
  }
 }
 
 /**
  * A bubble sort can sort an array of n entries into ascending order by 
  * makeing n-1 passes through the array. On each pass, it compares adjacent
  * entries and swaps them if they are out or order. For example, on the 
  * first pass, it compares the first and second entries, then the second and
  * third entries, and so on. At the end of the first pass, the largest entry
  * is in its proper position at the end of the array. We say that it has bubbled
  * to its correct spot. Each subsequent pass ignores the entries at the end of
  * the array, since they are sorted and are larger than any of the remaining
  * entries. Thus, each pass makes one fewer comparison than the previous pass.
  * Check the figure under HW05 assignment on Canvas.
  * 
  * This method implements bubble sort iteratively.
  * 
  * @param arr Array of objects (with specific bounds) to be sorted in nondecreasing order.
  */
 public static <T extends Comparable<? super T>> void bubbleSort_Itr(T[] arr)
 {
 	if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  else {
	  for(int j=0;j<arr.length;j++) {
		  T max=arr[0];
		  for(int i=0;i<arr.length-j;i++) {//finds the maximum value
			  if(arr[i].compareTo(max)>0) {
				  max=arr[i];
			  }
		  }
		  for(int i=0;i<arr.length-j-1;i++) {
			  if(arr[i]==max) {// sorts from the end of the array by shifting the rest to the left
				  T temp=arr[i];
				  for(int k=i;k<arr.length-1-j;k++) {
					  arr[k]=arr[k+1];
				  }
				  arr[arr.length-1-j]=temp;
			  }
		  }
		  
	  }
	  
  }
 }
 
 /**
  * A bubble sort can sort an array of n entries into ascending order by 
  * makeing n-1 passes through the array. On each pass, it compares adjacent
  * entries and swaps them if they are out or order. For example, on the 
  * first pass, it compares the first and second entries, then the second and
  * third entries, and so on. At the end of the first pass, the largest entry
  * is in its proper position at the end of the array. We say that it has bubbled
  * to its correct spot. Each subsequent pass ignores the entries at the end of
  * the array, since they are sorted and are larger than any of the remaining
  * entries. Thus, each pass makes one fewer comparison than the previous pass.
  * Check the figure under HW05 assignment on Canvas.
  * 
  * This method implements bubble sort recursively.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
 public static void bubbleSort_Rec(int[] arr)
 {
  if(arr == null) throw new NullPointerException();
  if(arr.length == 0) throw new IllegalArgumentException();
  if(arr.length == 1) return;
  if(arr.length >=2) {
	  int max=arr[0];
	  for(int i=0;i<arr.length;i++) {//finds the maximum value
		  max=Math.max(max, arr[i]);
	  }
	  for(int i=0;i<arr.length;i++) {
		  if(arr[i]==max) {// sorts from the end of the array by shifting the rest to the left
			  int temp=arr[i];
			  for(int j=i;j<arr.length-1;j++) {
				  arr[j]=arr[j+1];
			  }
			  arr[arr.length-1]=temp;
		  }
	  }
	  int[] temp=new int[arr.length-1];
	  for(int i=0;i<arr.length-1;i++) {//makes an array containing elements from arr[1] to end
		  temp[i]=arr[i];
	  }
	  bubbleSort_Rec(temp);//sorts just that piece
	  for(int i=0;i<arr.length-1;i++) {//adds it back to the original array
		  arr[i]=temp[i];
	  }
  } 
 }
}
