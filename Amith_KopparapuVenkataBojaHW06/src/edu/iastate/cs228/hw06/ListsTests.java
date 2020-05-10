package edu.iastate.cs228.hw06;

import edu.iastate.cs228.hw06.AList;
import edu.iastate.cs228.hw06.LList;
import edu.iastate.cs228.hw06.LListWithTail;
import edu.iastate.cs228.hw06.ListInterface;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * 
 * @author 
 *Amith Kopparapu Venkata Boja
 */
public class ListsTests {

    private static final String[] RANDOM_STRINGS = {"Hello", "Word", "Parent", "big", "Big"};

    @Test
    public void testAListLengthAndSize() {
        AList<String> list = new AList<>(40);
        populateAList(list);
        assertEquals(RANDOM_STRINGS.length, list.getLength());
        // Size is one more than the specified initial capacity for some reason
        assertEquals(41, list.getSize());
    }

    @Test
    public void testAListAddFirst() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.addFirst("then");
        list.addFirst("this");
        assertEquals("this", list.getEntry(1));
        assertEquals("then", list.getEntry(2));
    }

    @Test
    public void testLListAddFirst() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.addFirst("then");
        list.addFirst("this");
        assertEquals("this", list.getEntry(1));
        assertEquals("then", list.getEntry(2));
    }

    @Test
    public void testLListWithTailAddFirst() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.addFirst("then");
        list.addFirst("this");
        assertEquals("this", list.getEntry(1));
        assertEquals("then", list.getEntry(2));
    }

    @Test
    public void testAListAddLast() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.addLast("then");
        list.addLast("this");
        assertEquals("this", list.getEntry(list.getLength()));
        assertEquals("then", list.getEntry(list.getLength() - 1));
    }

    @Test
    public void testLListAddLast() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.addLast("then");
        list.addLast("this");
        assertEquals("this", list.getEntry(list.getLength()));
        assertEquals("then", list.getEntry(list.getLength() - 1));
    }

    @Test
    public void testLListWithTailAddLast() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.addLast("then");
        list.addLast("this");
        assertEquals("this", list.getEntry(list.getLength()));
        assertEquals("then", list.getEntry(list.getLength() - 1));
    }

    @Test
    public void testAListRemoveFirst() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.addFirst("then");
        list.addFirst("this");
        assertEquals("this", list.removeFirst());
        assertEquals("then", list.removeFirst());
    }

    @Test
    public void testLListRemoveFirst() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.addFirst("then");
        list.addFirst("this");
        assertEquals("this", list.removeFirst());
        assertEquals("then", list.removeFirst());
    }

    @Test
    public void testLListWithTailRemoveFirst() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.addFirst("then");
        list.addFirst("this");
        assertEquals("this", list.removeFirst());
        assertEquals("then", list.removeFirst());
    }

    @Test
    public void testAListRemoveLast() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.addLast("then");
        list.addLast("this");
        assertEquals("this", list.removeLast());
        assertEquals("then", list.removeLast());
    }

    @Test
    public void testLListRemoveLast() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.addLast("then");
        list.addLast("this");
        assertEquals("this", list.removeLast());
        assertEquals("then", list.removeLast());
    }

    @Test
    public void testLListWithTailRemoveLast() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.addLast("then");
        list.addLast("this");
        assertEquals("this", list.removeLast());
        assertEquals("then", list.removeLast());
    }

    @Test
    public void testAListMoveToEnd() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.addFirst("also");
        list.addFirst("then");
        list.addFirst("this");
        list.moveToEnd();
        list.moveToEnd();
        assertEquals("also", list.removeFirst());
        assertEquals("then", list.removeLast());
        assertEquals("this", list.removeLast());
    }

    @Test
    public void testLListMoveToEnd() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.addFirst("also");
        list.addFirst("then");
        list.addFirst("this");
        list.moveToEnd();
        list.moveToEnd();
        assertEquals("also", list.removeFirst());
        assertEquals("then", list.removeLast());
        assertEquals("this", list.removeLast());
    }

    @Test
    public void testLListWithTailMoveToEnd() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.addFirst("also");
        list.addFirst("then");
        list.addFirst("this");
        list.moveToEnd();
        list.moveToEnd();
        assertEquals("also", list.removeFirst());
        assertEquals("then", list.removeLast());
        assertEquals("this", list.removeLast());
    }

    @Test
    public void testAListRemove() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.add(3, "thisWord");
        list.add(3,"thisOtherWord");
        assertTrue(list.remove("thisOtherWord"));

        assertFalse(list.contains("thisOtherWord"));
        assertEquals("thisWord", list.getEntry(3));

        list.remove("thisWord");
        assertFalse(list.contains("thisOtherWord"));

        assertFalse(list.remove("NotInThere"));
    }

    @Test
    public void testLListRemove() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.add(3, "thisWord");
        list.add(3,"thisOtherWord");
        assertTrue(list.remove("thisOtherWord"));

        assertFalse(list.contains("thisOtherWord"));
        assertEquals("thisWord", list.getEntry(3));

        list.remove("thisWord");
        assertFalse(list.contains("thisOtherWord"));

        assertFalse(list.remove("NotInThere"));
    }

    @Test
    public void testLListWithTailRemove() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.add(3, "thisWord");
        list.add(3,"thisOtherWord");
        assertTrue(list.remove("thisOtherWord"));

        assertFalse(list.contains("thisOtherWord"));
        assertEquals("thisWord", list.getEntry(3));

        list.remove("thisWord");
        assertFalse(list.contains("thisOtherWord"));

        assertFalse(list.remove("NotInThere"));
    }

    @Test
    public void testAListGetPosition() {
        AList<String> list = new AList<>();
        populateAList(list);
        list.add(2, "Word1");
        list.add(2,"Word2");

        assertEquals(2, list.getPosition("Word2"));
        assertEquals(3, list.getPosition("Word1"));
        assertEquals(-1, list.getPosition("NotInThere"));
    }

    @Test
    public void testLListGetPosition() {
        LList<String> list = new LList<>();
        populateAList(list);
        list.add(2, "Word1");
        list.add(2,"Word2");

        assertEquals(2, list.getPosition("Word2"));
        assertEquals(3, list.getPosition("Word1"));
        assertEquals(-1, list.getPosition("NotInThere"));
    }

    @Test
    public void testLListWithTailGetPosition() {
        LListWithTail<String> list = new LListWithTail<>();
        populateAList(list);
        list.add(2, "Word1");
        list.add(2,"Word2");

        assertEquals(2, list.getPosition("Word2"));
        assertEquals(3, list.getPosition("Word1"));
        assertEquals(-1, list.getPosition("NotInThere"));
    }

    private void populateAList(ListInterface<String> list) {
        for (String s : RANDOM_STRINGS) {
            list.add(s);
        }
    }
}