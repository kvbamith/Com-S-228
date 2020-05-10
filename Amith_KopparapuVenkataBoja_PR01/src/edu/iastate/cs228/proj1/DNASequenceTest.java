package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class DNASequenceTest {
	private String temp;
	private char[] s;
	private DNASequence a;
	@Before
	public void setUp(){
		this.temp="AAAAAgggg";
		s=temp.toCharArray();
		a= new DNASequence(s);
	}
	@Test
	public void DNASequenceUppercaseOrLowercase() {// checks if the constructor works for both uppercase and lowercase alphabets
		assertEquals(temp,a.toString());	
	}
	@Test
	public void DNASequenceExceptionTest() {// checks if DNASequence will return the right exception if the array with impossible values are sent
		String temp2="ABCDefgh";
		boolean checker=false;
		char[] s2= temp2.toCharArray();
		try {
			DNASequence a2= new DNASequence(s2);
		}
		catch(IllegalArgumentException e) {
			checker=true;
		}
		assertTrue(checker);
	}
	@Test
	public void IsValidLetterTest1() {// checks if the isValidLetter works in different scenarios
		assertTrue(a.isValidLetter(s[0]));//valid letters
		assertTrue(a.isValidLetter(s[6]));
		assertFalse(a.isValidLetter('8'));// not alphabets scenario
	}
	@Test
	public void IsValidLetterTest2() {// checks if the isValidLetter works in different scenarios
		s[8]='k';
		assertFalse(a.isValidLetter(s[8]));// invalid letters
	}
	
	
	

}
