package testsObjects;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmmsObjects.Part;
import cmmsObjects.ServiceItem;

public class TestPart extends TestCase{

	private ServiceItem testItem;
	private Part testPart;
	
	@Before
	public void setUp() throws Exception {
		testItem = new ServiceItem("item", 5, 40 );
		testPart = new Part("part");
	}

	@Test
	public void testPart() {
		Part part = new Part("part");
		assertTrue( part != null );
	}

	@Test
	public void testAddServiceItem() {
		boolean result = testPart.addServiceItem(testItem);
		assertTrue( result );
	}

	@Test
	public void testGetPartDesc() {
		String result = testPart.getPartDesc();
		assertTrue( result != null );
	}

	@Test
	public void testToString() {
		String result = testPart.toString();
		assertTrue( result != null );
	}

	@Test
	public void testCheckNeedsService() {
		boolean result;
		testPart.addServiceItem( testItem );
		result = testPart.checkNeedsService(42);
		assertFalse( result );
		result = testPart.checkNeedsService(50);
		assertTrue( result );
	}

	@Test
	public void testEqualsObject() {
		boolean result = testPart.equals(testPart);
		assertTrue( result );
		result = testPart.equals(testItem);
		assertFalse( result );
	}

}
