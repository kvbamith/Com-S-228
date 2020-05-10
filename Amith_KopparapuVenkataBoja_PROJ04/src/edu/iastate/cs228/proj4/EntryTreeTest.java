package edu.iastate.cs228.proj4;
/**
 * @author
 * Amith Kopparapu Venkata Boja
 * 		A jUnit test cases class used to test all the necessary methods in the EntryTree class
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntryTreeTest {
	private EntryTree<Character,String> tree;
	@Before
	public void setUp() throws Exception {
		tree=new EntryTree();
		Character[] keys=new Character[] {'I', 'A'};
		tree.add(keys, "state");
	}

	@Test
	public void testEntryTree() {
		assertEquals(tree.root.child,null);
	}

	@Test
	public void testSearch() {
		Character[] keys2=new Character[] {'I'};
		assertEquals(tree.search(keys2),null);
		keys2=new Character[] {'I','A'};
		assertEquals(tree.search(keys2),"state");
	}

	@Test
	public void testPrefix() {
		tree.showTree();
		Character[] keys2=new Character[] {'I', 'S', 'U'};
		tree.add(keys2, "university");
		tree.showTree();
	}

	@Test
	public void testAdd() {
		Character[] keys2=new Character[] {'I', 'S', 'U'};
		tree.add(keys2, "university");
		assertEquals(tree.search(keys2),"university");
		tree.add(keys2, "college");
		assertEquals(tree.search(keys2),"college");
		keys2=new Character[] {'I','A'};
		tree.add(keys2, "state");
		assertEquals(tree.search(keys2),"state");
		/*The code snippet below is testing the exception scenario. Since the exception 
		 * originates from the method findNode and all the methods with the exception use that
		 * method, this does'nt need to be tested again
		 */
		boolean checker=false;
		EntryTree<Character,String> tree2=new EntryTree();
		try {
			keys2=new Character[] {'I',null,'A'};
			tree2.add(keys2, "state");
		}
		catch(NullPointerException e) {
			checker=true;
		}
		assertTrue(checker);
		
	}

	@Test
	public void testRemove() {
		Character[] keys2=new Character[] {'I','A'};
		assertEquals(tree.remove(null),null);
		assertEquals(tree.remove(keys2),"state");
		keys2=new Character[] {'I', 'S', 'U'};
		tree.add(keys2, "university");
		Character[] keys3=new Character[] {'I', 'S'};
		tree.remove(keys3);
	}

	@Test
	public void testGetAll() {
		String[][] vals=tree.getAll();
		assertEquals(vals[0][0], "IA, state");
	}

}
