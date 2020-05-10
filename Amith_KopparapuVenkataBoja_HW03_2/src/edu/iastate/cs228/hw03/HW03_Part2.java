package edu.iastate.cs228.hw03;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class HW03_Part2 {

	public HW03_Part2() {
		
	}
	public static int findMissingInt_a_On2(int[] arr) {
		boolean isThere=false;
		for(int i=1;i<=arr.length+1;i++) {
			for(int j=0;j<arr.length;j++) {
				if(i==arr[j]) {
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
	
	public static int findMissingInt_b_On1(int[] arr) {
		int temp=-1;
		 for(int i = 1 ; i < arr.length ;i++){
		        if(arr[i-1] > arr[i]){
		            temp = arr[i];
		            arr[i] = arr[i -1];
		            arr[i -1] = temp;
		            i--;
		        }
		    }
		int i=0;
		for(i=0;i<arr.length;i++) {
			if(arr[i]!= i+1) {
				return i+1;
			}
		}
		return i+1;
	}
	public static int[] rearrange_a_On2(int[] arr) {
		int[] temp=new int[arr.length];
		int minVal=arr[0];
		int k=0;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				minVal= Math.min(minVal, arr[j]);
			}
			for(int j=0;j<arr.length;j++) {
				if( arr[j]==minVal) {
					k=j;
				}
			}
			arr[k]=Integer.MAX_VALUE;
			temp[i]=minVal;
			minVal=Integer.MAX_VALUE;
		}
		return temp;
	}
	public static int rearrange_b_On1(int[] arr) {
		int[] negArr= new int[arr.length];
		int[] posArr= new int[arr.length];
		int j=0;
		int k=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]<0) {
				negArr[j]=arr[i];
				j++;
			}else {
				posArr[k]=arr[i];
				k++;
			}
		}
		for(int i=0;i<j;i++) {
			arr[i]=negArr[i];
		}
		int m=0;
		for(int i=j;i<arr.length;i++) {
			arr[i]=posArr[m];
			m++;
		}
		return -1;
	}
	
	/*
	 * Part 2 answers:
	 * 1. Oh(n)
	 * 2.Oh(n)
	 * 3. Oh(n^2) - order is 2
	 * 4. Oh(n^2)
	 * 5. Oh(n^2)
	 * 
	 */
}
