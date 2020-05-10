package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author 
 * Amith Kopparapu Venkata Boja
 * 
 * An application class that uses EntryTree class to process a file of
 * commands (one per line). Each command line consists of the name of
 * a public method of the EntryTree class followed by its arguments in
 * string form if the method has arguments. The name of the file is 
 * available to the main method from its String[] parameter at index 0.
 * You can assume that the command file is always in correct format. The 
 * main method creates an object of the EntryTree class with K being 
 * Character and V being String, reads each line from the command file, 
 * decodes the line by splitting into String parts, forms the corresponding 
 * arguments, and calls the public method from the EntryTree object with 
 * the arguments, and prints out the result on the console. Note that the 
 * name of a public method in the EntryTree class on each command line 
 * specifies that the public method should be called from the EntryTree 
 * object. A sample input file of commands and a sample output file are 
 * provided. 
 * 
 * The sample output file was produced by redirecting the console output
 * to a file, i.e.,
 * 
 * java Dictionary infile.txt > outfile.txt
 *  
 * 
 * NOTE that all methods of EntryTree class can be present as commands
 * except for getAll method inside the input file.
 * 
 * 
 */
public class Dictionary 
{
 public static void main(String[] args) throws FileNotFoundException 
 {
	 EntryTree<Character,String> tree=new EntryTree<Character,String>();
	 File file=new File(args[0]);
	 Scanner in=new Scanner(file);
	 while(in.hasNextLine()) {
		 String str=in.nextLine();
		 String[] jobs=str.split("\\s+");
		 if(jobs[0].equals("showtree")) {
			 tree.showTree();
		 }else if(jobs[0].equals("add")) {
			 Character[] keys=new Character[jobs[1].length()];
			 for(int i=0;i<keys.length;i++) {
				 keys[i]=jobs[1].charAt(i);
			 }
			 System.out.println("Command: " + charArrayToString(jobs));
			 System.out.println("Result from an add: "+tree.add(keys, jobs[2]));
			 
		 }else if(jobs[0].equals("remove")) {
			 Character[] keys=new Character[jobs[1].length()];
			 for(int i=0;i<keys.length;i++) {
				 keys[i]=jobs[1].charAt(i);
			 }
			 System.out.println("Command: " + charArrayToString(jobs));
			 System.out.println("Result from a remove: "+tree.remove(keys));
		 }else if(jobs[0].equals("showTree")) {
			 System.out.println("Result from a showTree: ");
			 tree.showTree();
		 }else if(jobs[0].equals("search")) {
			 Character[] keys=new Character[jobs[1].length()];
			 for(int i=0;i<keys.length;i++) {
				 keys[i]=jobs[1].charAt(i);
			 }
			 System.out.println("Command: " + charArrayToString(jobs));
			 System.out.println("Result from a search: "+tree.search(keys));
		 }else {
			 
		 }
	 }
	 in.close();
 }
	/**
	 *  A private helper method create to convert a character array to string
	 * 
	 * @param arr	
	 * 			Character array to be converted
	 * @return temp	
	 * 			a string representation of the charter array
	 */
	private static String charArrayToString(String[] arr) {
		String temp="";
		for(int i =0;i<arr.length;i++) {
			temp += (arr[i] + " ");
		}
		return temp;
	}
}
