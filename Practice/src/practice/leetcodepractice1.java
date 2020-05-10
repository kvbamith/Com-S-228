package practice;

import java.awt.List;
import java.util.ArrayList;
public class leetcodepractice1 {

	public static void main(String[] args) {
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(1);
		temp.add(5);
		temp.add(25);
		temp.add(-1);
		temp.add(9);
		meanderingArray(temp);
	}
	public ArrayList<String> findItinerary(ArrayList<ArrayList<String>> tickets) {
        ArrayList<String> itenary = new ArrayList<>();
        String whatToFind= "JFK";
        for(int i =0;i<tickets.size();i++){
            ArrayList<String> ticketPair = tickets.get(i);
            if(ticketPair.get(0).equals(whatToFind)){
                itenary.add(whatToFind);
                whatToFind= ticketPair.get(1);
                i=0;
            }
        }
        
        return itenary;
        
    }
	
	public static int[][] merge(int[][] intervals) {
        int[][] finalIntervals=new int[intervals.length][intervals[0].length];
        int j=0;
        for(int i=0;i<intervals.length-1;i++){
            int[] a= intervals[i];
            int[] b=intervals[i+1];
            if(b[0]<=a[1] || b[0]>=a[0]){
                int[] temp = new int[a.length];
                if(a[0]>=b[0]){
                    temp[0]=b[0];
                }else{
                    temp[0]=a[0];
                }
                if(a[1]>=b[1]){
                    temp[1]=b[1];
                }else{
                    temp[1]=a[1];
                }
                finalIntervals[j]=temp;
                j++;
            }
        }
        return finalIntervals;
    }
    public static ArrayList<Integer> meanderingArray(ArrayList<Integer> unsorted) {
    // Write your code here
        ArrayList<Integer> temp = new ArrayList<>();
        
        while(!unsorted.isEmpty()){
            int max = unsorted.get(0);
            int min = unsorted.get(0);
            int maxIndex=0;
            int minIndex=0;
            for(int i =0;i<unsorted.size();i++){
                if(unsorted.get(i)>max){
                    max=unsorted.get(i);
                    maxIndex=i;
                }
                if(unsorted.get(i)<min){
                    min=unsorted.get(i);
                    minIndex=i;
                }
            }
            temp.add(max);
            temp.add(min);
            unsorted.remove(maxIndex);
            unsorted.remove(minIndex);
        }
        return temp;
    }

}

