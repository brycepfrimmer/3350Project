package testsBusiness;

import junit.framework.TestCase;


import cmmsApplication.Services;
import cmmsBusiness.AccessManFields;
import cmmsObjects.ManFields;

public class TestAccessManFields extends TestCase{
	
	private static String dbName = cmmsApplication.Main.dbName;
	private static String db2Name = cmmsApplication.Main.db2Name;
	
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

		Services.createDataAccess( dbName, db2Name );

		fields = new ManFields();

		access = new AccessManFields();

		fields = access.getManFields();
		assertNotNull(fields);
		assertTrue(fields.getId());

		Services.closeDataAccess();

		System.out.println("Finished test AccessManFields");
	}
}
