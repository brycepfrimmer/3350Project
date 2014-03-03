package junitTest;

import junit.framework.TestCase;
import objects.Part;
import org.junit.Test;

public class TestPart extends TestCase {

    @Test
    public void testNewPart() {
        Part newPart = new Part("part");
        assertTrue(newPart != null);
        newPart = new Part(null);
        assertTrue(newPart == null);
    }

    /*@Test
    public void testRemovePart() {
        list.addPart("part");
        boolean success = list.removePart("part");
        assertTrue(success);
    }

    @Test
    public void testToString() {
        boolean success = (list.toString() != null);
        assertTrue(success);
    }

    @Test
    public void testIsEmpty() {
        PartsList newList = new PartsList();
        boolean success = newList.isEmpty();
        assertTrue(success);
    }

    @Test
    public void testGetPartsList() {
        boolean success = list.getPartsList() != null;
        assertTrue(success);
    }

    @Test
    public void testPrint() {
        boolean success = list.print();
        assertTrue(success);
    }*/

}
