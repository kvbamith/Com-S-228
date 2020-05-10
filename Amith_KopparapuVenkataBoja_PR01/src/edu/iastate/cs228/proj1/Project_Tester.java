package edu.iastate.cs228.proj1;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class Project_Tester {

	public static void main(String[] args) {
		String temp="ABCDEFGH";
		char[] s= temp.toCharArray();
		Sequence a= new Sequence(s);
		System.out.println(a.getSeq());

		String temp2="ABCDefgh";
		char[] s2= temp2.toCharArray();
		try {// testing what different methods print for exceptions scenarios in their constructors
			//GenomicDNASequence a2= new GenomicDNASequence(s2);
			CodingDNASequence a2=new CodingDNASequence(s2);
			//DNASequence a2=new DNASequence(s2);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		
		//tests what the exceptions print when there is error in markCoding and extractExons methods
		String temp3="AAAAAgggg";
		char[] s3=temp3.toCharArray();
		GenomicDNASequence a3= new GenomicDNASequence(s3);
		try {
			a3.markCoding(5, 4);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		
		int[] test=new int[3];
		try {
			a3.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			
		}
		
		//tests what the exceptions print when there is error in extractExons methods for unordered exonpos
		test=new int[]{4,2,3,5};
		try {
			a3.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			
		}
		//tests what the exceptions print when there is error in extractExons method when a value in the given exon position is false
		test=new int[] {1,2,3,4};
		try {
			a3.extractExons(test);
		}
		catch(IllegalStateException e) {
			System.out.println(e);
			
		}
	}

}
