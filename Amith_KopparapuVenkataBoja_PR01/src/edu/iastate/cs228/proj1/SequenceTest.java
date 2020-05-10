package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class SequenceTest {
	private String temp;
	private char[] s;
	private Sequence a;
	@Before
	public void setUp() {
		this.temp="ABCDefgh";
		s=temp.toCharArray();
		a= new Sequence(s);
	}

	@Test
	public void SequenceUppercaseOrLowercase() {// checks if the constructor works for both uppercase and lowercase alphabets
		assertEquals(temp,a.toString());
	}
	@Test
	public void SequenceExceptionTest() {// checks if the Sequence constructor will return the right exception if the array with impossible values are sent
		String temp2="ABC**FGH";
		boolean checker=false;
		char[] s2= temp2.toCharArray();
		try {
			Sequence a2= new Sequence(s2);
		}
		catch(IllegalArgumentException e) {
			checker=true;
		}
		assertTrue(checker);
	}
	@Test
	public void SequenceLengthTest() {// checks if the method seqlength returns the correct length of the array
		assertEquals(8,this.a.seqLength());
	}
	@Test
	public void GetSeqTest() {// checks if the getSeq method returns the entire array seqarr 
		char[] temp2=a.getSeq();
		String str="";
		for(int i=0;i<temp2.length;i++) {
			str+=temp2[i];
		}
		assertEquals(temp,str);
	}
	@Test
	public void ToStringTest() {// checks if the toString method returns the array in the correct format
		assertEquals(temp,a.toString());
	}
	@Test
	public void IsValidLetterTest1() {// checks if the isValidLetter works in different scenarios
		assertTrue(a.isValidLetter(s[0]));//valid letters
		assertTrue(a.isValidLetter(s[3]));
		assertFalse(a.isValidLetter('8'));// not alphabets scenario
	}
	@Test
	public void IsValidLetterTest2() {// checks if the isValidLetter works in different scenarios
		s[8]='k';
		assertFalse(a.isValidLetter(s[8]));// invalid letters
	}
}
