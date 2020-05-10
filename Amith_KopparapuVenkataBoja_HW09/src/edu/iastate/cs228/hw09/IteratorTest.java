package edu.iastate.cs228.hw09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

/**
 * Test for all three order iterators
 * @author Cristina Marquez
 *
 */
class IteratorTest {

	@Test
	void preorderIteratorTest() {
		BinaryTree<String> left1Tree = new BinaryTree<String>("C");
		BinaryTree<String> right1Tree = new BinaryTree<String>("D");
		BinaryTree<String> firstTree = new BinaryTree<String>();
		firstTree.setTree("B", left1Tree, right1Tree);
        
        BinaryTree<String> left2Tree = new BinaryTree<String>("F");
		BinaryTree<String> right2Tree = new BinaryTree<String>("G");
		BinaryTree<String> secondTree = new BinaryTree<String>();
		secondTree.setTree("E", left2Tree, right2Tree);
        
        BinaryTree<String> thirdTree = new BinaryTree<String>();
        thirdTree.setTree("A", firstTree, secondTree); 
        
        Iterator<String> iter = thirdTree.getPreorderIterator();
        assertTrue(iter.hasNext());
        assertEquals("A", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("B", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("C", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("D", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("E", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("F", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("G", iter.next());
        assertFalse(iter.hasNext());        
	}
	
	@Test
	void levelorderIteratorTest() {
		BinaryTree<String> left1Tree = new BinaryTree<String>("C");
		BinaryTree<String> right1Tree = new BinaryTree<String>("D");
		BinaryTree<String> firstTree = new BinaryTree<String>();
		firstTree.setTree("B", left1Tree, right1Tree);
        
        BinaryTree<String> left2Tree = new BinaryTree<String>("F");
		BinaryTree<String> right2Tree = new BinaryTree<String>("G");
		BinaryTree<String> secondTree = new BinaryTree<String>();
		secondTree.setTree("E", left2Tree, right2Tree);
        
        BinaryTree<String> thirdTree = new BinaryTree<String>();
        thirdTree.setTree("A", firstTree, secondTree); 
        
        Iterator<String> iter = thirdTree.getLevelOrderIterator();
        assertTrue(iter.hasNext());
        assertEquals("A", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("B", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("E", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("C", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("D", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("F", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("G", iter.next());
        assertFalse(iter.hasNext());        
	}
	
	@Test
	void postorderIteratorTest() {
		BinaryTree<String> left1Tree = new BinaryTree<String>("C");
		BinaryTree<String> right1Tree = new BinaryTree<String>("D");
		BinaryTree<String> firstTree = new BinaryTree<String>();
		firstTree.setTree("B", left1Tree, right1Tree);
        
        BinaryTree<String> left2Tree = new BinaryTree<String>("F");
		BinaryTree<String> right2Tree = new BinaryTree<String>("G");
		BinaryTree<String> secondTree = new BinaryTree<String>();
		secondTree.setTree("E", left2Tree, right2Tree);
        
        BinaryTree<String> thirdTree = new BinaryTree<String>();
        thirdTree.setTree("A", firstTree, secondTree); 
        
        Iterator<String> iter = thirdTree.getPostorderIterator();
        assertTrue(iter.hasNext());
        assertEquals("C", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("D", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("B", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("F", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("G", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("E", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("A", iter.next());
        assertFalse(iter.hasNext());        
	}

}