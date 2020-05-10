package edu.iastate.cs228.hw05;

public class SortingTester {// made to test different methods in sorting exercises with a smaller array and easier inputs

	public static void main(String[] args) {
		Integer[] array=new Integer[] {
                new Integer(5), new Integer(94), new Integer(55), new Integer(22), new Integer(47)};
		SortingExercises.bubbleSort_Itr(array);
		System.out.println(array);

	}

}
