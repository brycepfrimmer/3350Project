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
		assertFalse(success);
	}
	
	@Test
	public void testRemovePart()
	{
		list.addPart("part");
		boolean success = list.removePart("part");
		assertTrue(success);
	}
	
	@Test
	public void testToString()
	{
		boolean success = ( list.toString() != null );
		assertTrue(success);
	}
	
	@Test
	public void testIsEmpty()
	{
		PartsList newList = new PartsList();
		boolean success = newList.isEmpty();
		assertTrue(success);
	}
	
	@Test
	public void testGetPartsList()
	{
		boolean success = list.getPartsList() != null;
		assertTrue(success);
	}

	@Test
	public void testPrint() {
		boolean success = list.print();
		assertTrue(success);
	}

}
