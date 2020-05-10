package edu.iastate.cs228.proj1;

public class Project_Tester {

	public static void main(String[] args) {
		String temp="ABCDEFGH";
		char[] s= temp.toCharArray();
		Sequence a= new Sequence(s);
		System.out.println(a.getSeq());

	}

}
