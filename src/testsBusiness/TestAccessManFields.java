package testsBusiness;

import junit.framework.TestCase;


import cmmsApplication.Services;
import cmmsBusiness.AccessManFields;
import cmmsObjects.ManFields;

public class TestAccessManFields extends TestCase{
	
	private static String dbName = cmmsApplication.Main.dbName;
	
	public TestAccessManFields(String arg0)
	{
		super(arg0);
	}

	public void testAccess()
	{
		AccessManFields access;
		ManFields fields;

		Services.closeDataAccess();

		System.out.println("\nStarting test AccessManFields");

		Services.createDataAccess( dbName );

		fields = new ManFields();

		access = new AccessManFields();

		fields = access.getManFields();
		assertNotNull(fields);
		assertTrue(fields.getId());

		Services.closeDataAccess();

		System.out.println("Finished test AccessManFields");
	}
}
