package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class ProteinSequenceTest {
	private String temp;
	private char[] s;
	private ProteinSequence a;
	private int[] exons;
	@Before
	public void setUp(){
		this.temp="Atgaaaaacaag";
		s=temp.toCharArray();
		exons=new int[]{1,2,3,6};
		a= new ProteinSequence(s);
	}

	@Test
	public void ProteinSequenceExceptionTest() {// checks if ProteinSequence will return the right exception if the array with impossible values are sent
		String temp2="ABCDefgh";
		boolean checker=false;
		char[] s2= temp2.toCharArray();
		try {
			ProteinSequence a2= new ProteinSequence(s2);
		}
		catch(IllegalArgumentException e) {
			checker=true;
		}
		assertTrue(checker);
	}
	@Test
	public void IsValidLetterTest() {
		assertTrue(a.isValidLetter('a'));// checks all the possible scenarios of the lower case letters
		assertFalse(a.isValidLetter('b'));
		assertFalse(a.isValidLetter('j'));
		assertFalse(a.isValidLetter('o'));
		assertFalse(a.isValidLetter('u'));
		assertFalse(a.isValidLetter('x'));
		assertFalse(a.isValidLetter('z'));
		assertFalse(a.isValidLetter('*'));// checks if a character other than a letter can be sent in
		assertTrue(a.isValidLetter('A'));// checks if uppercase letters are treated the same
		assertFalse(a.isValidLetter('B'));
	}

}
