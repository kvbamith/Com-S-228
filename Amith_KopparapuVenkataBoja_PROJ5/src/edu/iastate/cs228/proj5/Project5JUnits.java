package edu.iastate.cs228.proj5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Stack;

import org.junit.Test;

import edu.iastate.cs228.proj5.DFS;
import edu.iastate.cs228.proj5.DiGraph;
import edu.iastate.cs228.proj5.MaxPath;

/**
 * JUnits for this project. 
 * 
 * @author Steven Sheets
 */
public class Project5JUnits
{

	/**
	 * "If G is null throw an illegal argument exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest1()
	{
		MaxPath.findMaxPath(null, new Stack<>());
	}

	/**
	 * If maxPath is null throw an illegal argument exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest2()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		MaxPath.findMaxPath(G8, null);
	}

	/**
	 * If maxPath is not empty throw an illegal argument exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest3()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		Stack<String> path = new Stack<>();
		MaxPath.findMaxPath(G8, path);
		// Now the path is no longer empty, should throw an error if we do it again.
		MaxPath.findMaxPath(G8, path);
	}

	/**
	 * If topoOrder is empty, throw an illegal state exception.
	 */
	@Test(expected = IllegalStateException.class)
	public void findMaxPathTest4()
	{
		DiGraph<String> G8 = new DiGraph<>();
		Stack<String> path = new Stack<>();
		MaxPath.findMaxPath(G8, path);
	}

	/**
	 * Should throw an illegal argument exception if there is a cycle.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findMaxPathTest5()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("Z", "X", 5);
		G8.addEdge("W", "A", 1);
		G8.addEdge("A", "L", 1);
		G8.addEdge("L", "W", 1); // thank u mr. walt.
		G8.addEdge("U", "I", 22);
		Stack<String> path = new Stack<>();
		MaxPath.findMaxPath(G8, path);
	}

	/**
	 * From Canvas
	 */
	@Test
	public void findMaxPathTest6()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);

		Stack<String> path = new Stack<>();
		assertEquals(46 + "", MaxPath.findMaxPath(G8, path) + "");
	}

	/**
	 * Tis just a line. ______________________
	 */
	@Test
	public void findMaxPathTest7()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("S", "A", 0);
		G8.addEdge("S", "B", 1000);
		Stack<String> path = new Stack<>();
		assertEquals(1000 + "", MaxPath.findMaxPath(G8, path) + "");
	}

	/**
	 * Throw an illegal argument exception if aGraph is null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void depthFirstSearchTest1()
	{
		DFS.depthFirstSearch(null);
	}

	/**
	 * From Canvas.
	 */
	@Test
	public void depthFirstSearchTest2()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);

		Stack<String> order = DFS.depthFirstSearch(G8);
		assertEquals(order.pop(), "F");
		assertEquals(order.pop(), "C");
		assertEquals(order.pop(), "S");
		assertEquals(order.pop(), "B");
		assertEquals(order.pop(), "A");
		assertEquals(order.pop(), "E");
		assertEquals(order.pop(), "D");
		assertEquals(order.pop(), "G");

		// Might as well make sure empty.
		assertTrue(order.isEmpty());
	}

	/**
	 * Also from Canvas.
	 */
	@Test
	public void depthFirstSearchTest3()
	{
		DiGraph<String> G8 = new DiGraph<>();
		G8.addEdge("S", "A", 6);
		G8.addEdge("S", "B", 10);
		G8.addEdge("S", "G", -5);
		G8.addEdge("A", "D", 8);
		G8.addEdge("A", "E", 5);
		G8.addEdge("B", "A", -6);
		G8.addEdge("B", "E", 10);
		G8.addEdge("C", "S", 11);
		G8.addEdge("D", "G", 7);
		G8.addEdge("E", "D", 6);
		G8.addEdge("E", "G", 5);
		G8.addEdge("F", "B", -8);
		G8.addEdge("F", "C", 2);

		Stack<String> path = new Stack<>();
		MaxPath.findMaxPath(G8, path);
		assertEquals(path.pop(), "F");
		assertEquals(path.pop(), "C");
		assertEquals(path.pop(), "S");
		assertEquals(path.pop(), "B");
		assertEquals(path.pop(), "E");
		assertEquals(path.pop(), "D");
		assertEquals(path.pop(), "G");

		// Might as well make sure empty.
		assertTrue(path.isEmpty());
	}

}