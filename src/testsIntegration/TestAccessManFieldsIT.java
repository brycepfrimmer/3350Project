package testsIntegration;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmmsBusiness.AccessManFields;
import cmmsObjects.ManFields;
import cmmsPersistence.StubDataAccessObject;

public class TestAccessManFieldsIT extends TestCase{
	AccessManFields access;
	ManFields fields;
	@Before
	public void setUp() throws Exception {
		access = new AccessManFields( new StubDataAccessObject() );
		fields = new ManFields();
	}

	@Test
	public void testGetManFields() {
		fields = access.getManFields();
		assertNotNull(fields);
	}

	@Test
	public void testUpdateManFields() {
		access.updateManFields(null);
		assertNull(access.getManFields());
		access.updateManFields(new ManFields());
		assertNotNull(access.getManFields());
	}

}
