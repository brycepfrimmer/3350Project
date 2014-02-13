package junitTest;


import junit.framework.TestCase;
import CMMS.*;
import org.junit.Test;

public class TestPartsList extends TestCase{
	PartsList list = new PartsList();
	@Test
	public void testPartsList() {
		PartsList newList = new PartsList();
		assertTrue( newList != null );
	}

	@Test
	public void testAddPart() {
		boolean success = list.addPart("part");
		assertTrue(success);
		success = list.addPart("   ");
		assertFalse(success);
		success = list.addPart(null);
		assertFalse(success);
		success = list.addPart("part");
		assertTrue(success);
		success = list.addPart("part");
		assertTrue(success);
		success = list.addPart("part");
		assertTrue(success);
		success = list.addPart("part");
		assertTrue(success);
	}

	@Test
	public void testPrint() {
		boolean success = list.print();
		assertTrue(success);
	}

}
