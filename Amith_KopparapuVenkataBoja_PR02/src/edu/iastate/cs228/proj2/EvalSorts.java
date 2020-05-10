package edu.iastate.cs228.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author 
 *Amith Kopparapu Venkata Boja
 */
public class EvalSorts {
	public static final int kNumberOfWordsToSort = 10000;

	/**
	 main is responsible only for extracting fileNames from args,
     reading the files, and constructing an instance of the this 
     class configured with the input data.
	 FileNotFoundException and FileConfigurationException exceptions 
	 should be handled in main, i.e., print out appropriate message
	 to the user.
	 * @throws FileNotFoundException 
	*/
	public static void main(String args[]) throws FileNotFoundException, FileConfigurationException{
		char[] alphabet   = new char[70];  //ref to the Lexicon it creates. 
		String[] wordList = new String[kNumberOfWordsToSort];  //ref to the list of words to be sorted. 
		EvalSorts theApp  = null;  //ref to the app. 
		LexiconImpl comp  = null;  //the concrete lexicon your app uses. 
		File file;
		File file2;
		file=new File(args[0]);
		file2=new File(args[1]);
		Scanner in =new Scanner(file2);
		int i=0;
		while(in.hasNextLine()) {
			wordList[i]=in.nextLine();
			i++;
		}
		in=new Scanner(file);
		int k=0;
		while(in.hasNextLine()) {
			alphabet[k]=in.nextLine().charAt(0);
			k++;
		}
		in.close();
		comp=new LexiconImpl(alphabet);
		//configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		//now execute that instance
		theApp.runSorts();
		
		
	}

	
	private String[] words; //ref to the word lit
	private Lexicon lex;    //ef to the relevant lexicon	
	private int numWordsToSort = kNumberOfWordsToSort;
	
	/**
	 * This constructor configures an instance of EvalSorts to sort input read
	 * my main, using the character order read by main and now embedded in
	 * an instance of Lexicon
	 * @param lex the instance of lexicon to be used
	 * @param wordList the wordlist (as array of string)  to be sorted
	 * @param numWordsToSort each sort will be repeated until it has sorted
	 *                       this many words. 
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		words=wordList;
		this.lex=lex;
		this.numWordsToSort=numWordsToSort;
	}

	/**
	 * runSorts() performs the sort evaluation. 
	 * 
	 * Note: The three sorters extend a common base
	 * so they share the same interface for starting the sort and collecting statistics. 
	 * Thus, you should create instances of the sorter and save references to each in an 
	 * array of base type. This allows you to use a simple loop to run all the reports and 
	 * collect the statistics.   
	 */
	public void runSorts(){
		
		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		sorters[0]=new QuickSort();
		sorters[1]=new MergeSort();
		sorters[2]=new SelectionSort();
		for(int i=0;i<sorters.length;i++) {
			sorters[i].sort(words, lex);
			System.out.println(sorters[i].getReport());
		}
		

	}
	
	/**
	 * Reads the characters contained in filename and returns them as a character array.
	 * @param filename the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used 
	 *          or null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename) 
			throws FileNotFoundException, FileConfigurationException {
		ArrayList<Character> t= new ArrayList<Character>();
		File f=new File(filename);
		Scanner scanner= new Scanner(f);
		while(scanner.hasNextLine()) {
			String temp=scanner.nextLine();
			if(temp.length()>1 || t.contains(temp)) {
				throw new FileConfigurationException();
			}else {
				t.add(temp.charAt(0));
			}
		}
		scanner.close();
		char[] temp = new char[t.size()];
		for(int i=0;i<t.size();i++) {
			temp[i]=t.get(i);
		}
		return temp; 
	}
	
	/**
	 * Reads the words from the file and returns them as a String array.
	 * @param filename file containing words
	 * @return the words contained in the file or null if there was a FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		ArrayList<String> t= new ArrayList<String>();
		File f=new File(filename);
		Scanner scanner= new Scanner(f);
		while(scanner.hasNextLine()) {
			String temp=scanner.nextLine();
			if(comp.isValid(temp)) {
				t.add(temp);
			}else{
				throw new FileConfigurationException();
			}
		}
		scanner.close();
		String[] temp = new String[t.size()];
		for(int i=0;i<t.size();i++) {
			temp[i]=t.get(i);
		}
		return temp;
	}

}
