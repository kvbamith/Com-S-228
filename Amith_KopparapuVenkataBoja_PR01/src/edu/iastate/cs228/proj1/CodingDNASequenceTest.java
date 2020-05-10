package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Amith Kopparapu Venkata Boja
 *
 */
public class CodingDNASequenceTest {
	private String temp;
	private char[] s;
	private CodingDNASequence a;

	@Before
	public void setUp(){
		this.temp="Atgaaaaacaagaa";
		s=temp.toCharArray();
		a= new CodingDNASequence(s);
	}

	@Test
	public void CodingDNASequenceExceptionTest() {// checks if CodingDNASequence will return the right exception if the array with impossible values are sent
		String temp2="ABCDefgh";
		boolean checker=false;
		char[] s2= temp2.toCharArray();
		try {
			CodingDNASequence a2= new CodingDNASequence(s2);
		}
		catch(IllegalArgumentException e) {
			checker=true;
		}
		assertTrue(checker);
	}
	@Test
	public void CheckStartCodonTestLength() {// checks if the if returns false when length is less than 3
		this.temp="AA";
		s=temp.toCharArray();
		a= new CodingDNASequence(s);
		assertFalse(a.checkStartCodon());
	}
	@Test
	public void CheckStartCodonTestContent_1() {// checks if it returns false when a, t and g don't exsist in the array
		temp="ATAAAA";
		s=temp.toCharArray();
		a= new CodingDNASequence(s);
		assertFalse(a.checkStartCodon());
	}
	@Test
	public void CheckStartCodonTestContent_2() {// checks if the method recognizes the atg in a random place in the array and in the same order and if lowerccase counts
		temp="tGAtgA";
		s=temp.toCharArray();
		a= new CodingDNASequence(s);
		assertTrue(a.checkStartCodon());
	}
	@Test
	public void TranslateExceptionTest() {// checks if the method translate will return the right exception if checkStartCodon fails
		String temp2="Attttggg";
		boolean checker=false;
		char[] s2= temp2.toCharArray();
		CodingDNASequence a2= new CodingDNASequence(s2);
		try {
			a2.translate();
		}
		catch(RuntimeException e) {
			checker=true;
		}
		assertTrue(checker);
	}
	@Test
	public void TranslateTest() {// checks if the translate method translates the given array into the appropriate codon values
		char[] tempArray=a.translate();
		String str="";
		for(int i=0;i<tempArray.length;i++) {
			str+=tempArray[i];
		}
		assertEquals(str,"MKNK");
	}
}
