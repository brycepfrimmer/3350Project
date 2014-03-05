package testsObjects;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmmsObjects.ManFields;

public class TestManFields extends TestCase{

	ManFields testFields;
	
	@Before
	public void setUp() throws Exception {
		testFields = new ManFields();
	}

	@Test
	public void testManFields() {
		ManFields fields = new ManFields();
		assertTrue( fields != null );
	}

	@Test
	public void testGetId() {
		boolean result = testFields.getId();
		assertTrue( result );
	}

	@Test
	public void testSetId() {
		boolean result = testFields.setId(false);
		assertFalse( result );
		result = testFields.setId(true);
		assertTrue( result );
	}

	@Test
	public void testGetType() {
		boolean result = testFields.getType();
		assertTrue( result );
	}

	@Test
	public void testGetManufacturer() {
		boolean result = testFields.getManufacturer();
		assertTrue( result );
	}

	@Test
	public void testGetModel() {
		boolean result = testFields.getModel();
		assertTrue( result );
	}

	@Test
	public void testGetKmsDriven() {
		boolean result = testFields.getKmsDriven();
		assertTrue( result );
	}

	@Test
	public void testGetKmsLastServiced() {
		boolean result = testFields.getKmsLastServiced();
		assertTrue( result);
	}

	@Test
	public void testGetInsInfo() {
		boolean result = testFields.getInsInfo();
		assertTrue( result );
	}

	@Test
	public void testGetYear() {
		boolean result = testFields.getYear();
		assertTrue( result );
	}

}
