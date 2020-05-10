package edu.iastate.cs228.hw03;

/**
 * 
 * @Amith Kopparapu Venkata Boja
 *
 */
public class HW03_Part2
{
 /*
  * Answers to short questions:
  * 
  * 1.Oh(n)
  * 
  * 2. Oh(log(n))
  * 
  * 3.Oh(n^2) - order is 2
  * 
  * 4.Oh(n^2)
  * 
  * 5.Oh(n^2)
  * 
  */
	
	/*
	 In all of the following methods you can assume that
	 array will always have elements (ints) in it.
	 And will have proper integers as defined in the 
	 description of HW03, i.e., in first two it will be
	 in the range, and in last two it will be composed of
	 negative and positive values only.
	*/
	
	public static int findMissingInt_a_On2(int [] array)
	{
		boolean isThere=false;
		for(int i=1;i<=array.length+1;i++) {
			for(int j=0;j<array.length;j++) {
				if(i==array[j]) {
					isThere=true;
					break;
				}
				isThere=false;
			}
			if(!isThere) {
				return i;
			}
		}
		return -1;
	}
	
	public static int findMissingInt_b_On1(int [] array)
	{
		int k=0;
		int j=1;
		int temp=-1;
		while(k<array.length && j<array.length) {
			if(array[k]>array[j]) {
				temp = array[k];
	            array[k] = array[j];
	            array[j] = temp;
	            k=0;
	            j=1;
			}
			else {
				j++;
				k++;
			}
		}

		int i=0;
		for(i=0;i<array.length;i++) {
			if(array[i]!= i+1) {
				return i+1;
			}
		}
		return i+1;		
	}
	
	public static void rearrange_a_On2(int [] array)
	{
		int[] temp=new int[array.length];
		int minVal=array[0];
		int k=0;
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array.length;j++) {
				minVal= Math.min(minVal, array[j]);
			}
			for(int j=0;j<array.length;j++) {
				if( array[j]==minVal) {
					k=j;
				}
			}
			array[k]=Integer.MAX_VALUE;
			temp[i]=minVal;
			minVal=Integer.MAX_VALUE;
		}
		for(int i=0;i<array.length;i++) {
			array[i]=temp[i];
		}
	}
	
	public static void rearrange_b_On1(int [] array)
	{
		int[] negArr= new int[array.length];
		int[] posArr= new int[array.length];
		int j=0;
		int k=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]<0) {
				negArr[j]=array[i];
				j++;
			}else {
				posArr[k]=array[i];
				k++;
			}
		}
		for(int i=0;i<j;i++) {
			array[i]=negArr[i];
		}
		int m=0;
		for(int i=j;i<array.length;i++) {
			array[i]=posArr[m];
			m++;
		}
	}
	
}
