package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author 
 *Amith Kopparapu Venkata Boja
 */
public class SelectionSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
      if(words == null) throw new NullPointerException();
	  if(words.length == 0) throw new IllegalArgumentException();
	  if(words.length == 1) return;
	  if(words.length >=2) {
	        for (int i = 0; i < words.length-1; i++){
	            int min=i; 
	            for (int j=i+1; j < words.length; j++) {
	                if (comp.compare(words[j], words[min]) >0 ) {
	                    min = j; 
	                }
	            }
	            String temp=words[min]; 
	            words[min]=words[i]; 
	            words[i]=temp; 
	        } 
	  }
	}
}
