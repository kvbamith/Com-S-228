package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class GenomicDNASequenceTest {
	private String temp;
	private char[] s;
	private GenomicDNASequence a;
	private int[] exons;

	@Before
	public void setUp() {
		this.temp="AAAAAgggg";
		s=temp.toCharArray();
		exons=new int[]{1,2,3,6};
		a= new GenomicDNASequence(s);
	}
	@Test
	public void GenomicDNASequenceExceptionTest() {// checks if GenomicDNASequence will return the right exception if the array with impossible values are sent
		String temp2="ABCDefgh";
		boolean checker=false;
		char[] s2= temp2.toCharArray();
		try {
			GenomicDNASequence a2= new GenomicDNASequence(s2);
		}
		catch(IllegalArgumentException e) {
			checker=true;
		}
		assertTrue(checker);
	}
	@Test
	public void MarkCodingExceptionTest_1() {// checks if the markCoding method will return the right exception if the array with impossible values are sent
		boolean checker =false;
		try {
			a.markCoding(4, 3);// scenario where begin>end
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
	}
	@Test
	public void MarkCodingExceptionTest_2() {// checks if the markCoding method will return the right exception if the array with impossible values are sent
		boolean checker =false;
		try {
			a.markCoding(-1, 3);// scenrio when the index value is a negative number
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
	}
	@Test
	public void MarkCodingExceptionTest_3() {// checks if the markCoding method will return the right exception if the array with impossible values are sent
		boolean checker =false;
		try {
			a.markCoding(1, 15);// scenario when the length of the end index is greater than the length of the array
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
	}
	@Test
	public void ExtractExonsTest() {// checks if it extract only the exons that what iscoding as true for them and checks whether the markCoding method returns the right values for the iscoding array
		char[] expected= ("AAAg").toCharArray();
		a.markCoding(1,3);
		a.markCoding(6, 6);
		char[] found=a.extractExons(exons);
		for(int i=0;i<expected.length;i++) {
			assertEquals(expected[i],found[i]);
		}
		
	}
	@Test
	public void ExtractExonsExceptionTest_1() {// checks if the extractExons method will return the right exception if the array with impossible values are sent
		// checks if the scenario of odd number elements array
		boolean checker =false;
		a.markCoding(0, 5);
		int[] test=new int[] {1,2,3};
		try {
			a.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
		
		// checks the scenario of empty array
		checker=false;
		test=new int[3];
		try {
			a.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
		
		//checks the scenario of extractExons with a negative integer element
		checker=false;
		test=new int[] {-1,2,3,4};
		try {
			a.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
		
		//checks the scenario of extractExons with a number greater than length of the dna array
		checker=false;
		test[0]=100;
		try {
			a.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
		
		//checks the scenario of an unordered int array
		checker=false;
		test[0]=4;
		try {
			a.extractExons(test);
		}
		catch(IllegalArgumentException e) {
			checker=true;
			
		}
		assertTrue(checker);
	}
	@Test
	public void ExtractExonsExceptionTest_2() {// checks if the extract exons method will return the right exception if the array with impossible values are sent
		// checks the scenario where the exons to be extracted are false in the isCoding array
		boolean checker =false;
		int[] test=new int[] {1,2,3,4};
		try {
			a.extractExons(test);
		}
		catch(IllegalStateException e) {
			checker=true;
			
		}
		assertTrue(checker);
	}

}
