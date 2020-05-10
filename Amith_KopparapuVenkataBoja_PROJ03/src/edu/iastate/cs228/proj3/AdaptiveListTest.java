package edu.iastate.cs228.proj3;
/**
 * @author 
 * Amith Kopparapu Venkata Boja
 */
/**
 *	 A class with Junit tests cases for the AdaptiveList and its iterator classes. 
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import java.util.ListIterator;
public class AdaptiveListTest {

	private ArrayList<Integer> values;
	private AdaptiveList<Integer> temp;
	private ListIterator iter;
	@Before
	public void setUp() throws Exception {
		values=new ArrayList<>(Arrays.asList(1,2,3,4,5));
		temp=new AdaptiveList<>(values);
		iter = temp.listIterator();
	}
	/**
	 * tests the AdaptiveList constructor
	 */
	@Test
	public void testAdaptiveList() {
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 3, 4, 5)");
	}
	/**
	 * tests the clear() method
	 */
	@Test
	public void testClear() {
		temp.clear();
		assertEquals(temp.size(),0);
	}
	/**
	 * tests the getLinedUTD() method
	 */
	@Test
	public void testGetlinkedUTD() {
		temp.reverse();
		assertFalse(temp.getlinkedUTD());
		
	}
	/**
	 * tests the getArrayUTD() method
	 */
	@Test
	public void testGetarrayUTD() {
		temp.set(2,4);
		assertFalse(temp.getlinkedUTD());
	}
	/**
	 * tests the constructor that inputs a specified collection
	 */
	@Test
	public void testAdaptiveListCollectionOfQextendsE() {
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 3, 4, 5)");
	}
	/**
	 * tests the size() method 
	 */
	@Test
	public void testSize() {
		assertEquals(temp.size(),5);
	}
	/**
	 * tests the isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		AdaptiveList<Integer> temp2=new AdaptiveList<>();
		assertTrue(temp2.isEmpty());
		assertFalse(temp.isEmpty());
	}
	/**
	 * tests the add(Object obj) method
	 */
	@Test
	public void testAddE() {
		temp.add(6);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 3, 4, 5, 6)");
	}
	/**
	 * tests the addAll() method that inputs a collection
	 */
	@Test
	public void testAddAllCollectionOfQextendsE() {
		ArrayList<Integer> toAdd=new ArrayList<>(Arrays.asList(6,7,8,9));
		temp.addAll(toAdd);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 3, 4, 5, 6, 7, 8, 9)");
	}
	/**
	 * tests the remove(Object obj) method
	 */
	@Test
	public void testRemoveObject() {
		temp.remove((Object)2);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 3, 4, 5)");
	}
	/**
	 * tests the add(int pos, Object obj) method
	 */
	@Test
	public void testAddIntE() {
		temp.add(2, 11);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 11, 3, 4, 5)");
	}
	/**
	 * tests the addAll( int pos, Collection<? E> c) method
	 */
	@Test
	public void testAddAllIntCollectionOfQextendsE() {
		ArrayList<Integer> toAdd=new ArrayList<>(Arrays.asList(6,7,8,9));
		temp.addAll(2,toAdd);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 6, 7, 8, 9, 3, 4, 5)");
	}
	/**
	 * tests the remove(int pos) method
	 */
	@Test
	public void testRemoveInt() {
		temp.remove(0);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(2, 3, 4, 5)");
	}
	/**
	 * tests the get(int pos) method
	 */
	@Test
	public void testGet() {
		AdaptiveList<Integer> temp2=new AdaptiveList(values);
		assertEquals(temp2.get(2),(Object)3);
	}
	/**
	 * tests the set(int pos, Object obj) method
	 */
	@Test
	public void testSet() {
		temp.set(2, 15);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 15, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 3, 4, 5)");
	}
	/**
	 * tests the reverse() method
	 */
	@Test
	public void testReverse() {
		
		temp.reverse();
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[5, 4, 3, 2, 1]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 2, 3, 4, 5)");
	}
	/**
	 * tests the reorderOddEven() method
	 */
	@Test
	public void testReorderOddEven() {
		temp.reorderOddEven();
		assertEquals(temp.toStringArray(),"A sequence of items from the most recent array:\n" + 
				"[2, 1, 4, 3, 5]");
	}
	/**
	 * tests the contains method
	 */
	@Test
	public void testContains() {
		assertTrue(temp.contains((Object)3));
		
	}
	/**
	 * tests the containsAll(Collection<? E> c) method
	 */
	@Test
	public void testContainsAll() {
		ArrayList<Integer> tester=new ArrayList<Integer>(Arrays.asList(2,3));
		assertTrue(values.containsAll(tester));
		ArrayList<Integer> tester2=new ArrayList<Integer>(Arrays.asList(2,3,7));
		assertFalse(values.containsAll(tester2));
	}
	/**
	 * tests the indexOf() method
	 */
	@Test
	public void testIndexOf() {
		assertEquals(temp.indexOf(1),0);
	}
	/**
	 * tests the lastIndexOF() method
	 */
	@Test
	public void testLastIndexOf() {
		ArrayList<Integer> values2=new ArrayList<>(Arrays.asList(1,2,4,3,4,5,4));
		AdaptiveList<Integer> tester =  new AdaptiveList<>(values2);
		assertEquals(6,tester.lastIndexOf(4));
	}
	/**
	 * tests the removeAll(Collection<? E> c) method
	 */
	@Test
	public void testRemoveAll() {
		ArrayList<Integer> tester=new ArrayList<Integer>(Arrays.asList(2,3));
		temp.removeAll(tester);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 4, 5)");
	}
	/**
	 * tests the retainAll(Collection<? E> c) method
	 */
	@Test
	public void testRetainAll() {
		ArrayList<Integer> tester=new ArrayList<Integer>(Arrays.asList(2,3,4));
		values.retainAll(tester);
		assertEquals(values.toString(),"[2, 3, 4]");
		ArrayList<Integer> tester2=new ArrayList<Integer>(Arrays.asList(2,3,7));
		values.retainAll(tester2);
		assertEquals(values.toString(),"[2, 3]");
	}
	/**
	 * tests the toArray(T[] arr) method
	 */
	@Test
	public void testToArrayTArray() {
		String[] temp2= new String[]{"1", "2"};
		//String[] temp3=(String[])temp.toArray(temp2);
		assertEquals(temp.toArray(temp2).getClass().getSimpleName(),temp2.getClass().getSimpleName());
//		assertTrue(temp3[0] instanceof String);
		
	}
	/**
	 * tests the iterator hasNext() method
	 */
	@Test
	public void testIteratorHasNext() {
		assertTrue(iter.hasNext());
		
	}
	/**
	 * tests the iterator next() method
	 */
	@Test
	public void testIteratorNext() {
		assertEquals(iter.next(),(Object)1);
	}
	/**
	 * tests the iterator hasprevious() method
	 */
	@Test
	public void testIteratorHasPrevious() {
		assertFalse(iter.hasPrevious());
		iter.next();
		assertTrue(iter.hasPrevious());
		
	}
	/**
	 * tests the iterator previous() method
	 */
	@Test
	public void testIteratorPrevious() {
		iter.next();
		assertEquals(iter.previous(),(Object)1);
		
	}
	/**
	 * tests the iterator nextIndex() method
	 */
	@Test
	public void testIteratorNextIndex() {
		assertEquals(iter.nextIndex(),0);
		
	}
	/**
	 * tests the iterator previousIndex() method
	 */
	@Test
	public void testIteratorPreviousIndex() {
		assertEquals(iter.previousIndex(),-1);
		iter.next();
		assertEquals(iter.previousIndex(),0);
		
	}
	/**
	 * tests the iterator remove() method
	 */
	@Test
	public void testIteratorRemove() {
		iter.next();
		iter.remove();
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(2, 3, 4, 5)");
	}
	/**
	 * tests the iterator add() method
	 */
	@Test
	public void testIteratorAdd() {
		iter.next();
		iter.add(15);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 15, 2, 3, 4, 5)");
	}
	/**
	 * tests the iterator set() method
	 */
	@Test
	public void testIteratorSet() {
		iter.next();
		iter.next();
		iter.previous();
		iter.set(6);
		assertEquals(temp.toString(),"A sequence of items from the most recent array:\n" + 
				"[1, 2, 3, 4, 5]\n" + 
				"A sequence of items from the most recent linked list:\n" + 
				"(1, 6, 3, 4, 5)");
	}
	


}
