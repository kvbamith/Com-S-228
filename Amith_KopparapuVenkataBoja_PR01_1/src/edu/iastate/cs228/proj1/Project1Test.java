package edu.iastate.cs228.proj1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Project1Test {

	@Test
	public void SequenceTest_UpperCase() {
		String temp="ABCDEFGH";
		char[] s= temp.toCharArray();
		Sequence a= new Sequence(s);
		assertEquals(s,a.getSeq());
	}

}
