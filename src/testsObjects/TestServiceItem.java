package testsObjects;

import java.util.GregorianCalendar;

import junit.framework.TestCase;
import cmmsObjects.ServiceItem;

import org.junit.Before;
import org.junit.Test;

public class TestServiceItem extends TestCase{

	private ServiceItem item1;
	private ServiceItem item2;
	
	@Before
	public void setUp() throws Exception {
		item1 = new ServiceItem("one", (long) 1.0, new GregorianCalendar() );
		item2 = new ServiceItem("two", 10, 5 );
	}
	
	@Test
	public void testServiceItem1() {
		ServiceItem item = new ServiceItem("item", (long)1.0, new GregorianCalendar());
		assertTrue( item != null );
	}

	@Test
	public void testServiceItem2() {
		ServiceItem item = new ServiceItem("item", 50, 40);
		assertTrue( item != null );
	}

	@Test
	public void testCheckNeedsService() {
		boolean result = item2.checkNeedsService(16);
		assertTrue(result);
		result = item2.checkNeedsService(10);
		assertFalse(result);
	}

	@Test
	public void testGetDescription() {
		String result = item1.getDescription();
		assertTrue( result != null );
	}

	@Test
	public void testGetServiceKm() {
			int result = item2.getServiceKm();
			assertTrue( result >= 0 );
	}

	@Test
	public void testGetServiceTime() {
		long result = item1.getServiceTime();
		assertTrue( result >= 0 );
	}

	@Test
	public void testGetDateLastServiced() {
		GregorianCalendar date = (GregorianCalendar) item1.getDateLastServiced();
		assertTrue( date != null );
		date = (GregorianCalendar) item2.getDateLastServiced();
		assertTrue( date == null );
	}

	@Test
	public void testGetKmLastServiced() {
		int result = item1.getKmLastServiced();
		assertTrue( result == 0 );
		result = item2.getKmLastServiced();
		assertTrue( result > 0 );
	}

}
