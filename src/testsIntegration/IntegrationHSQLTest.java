package testsIntegration;

import junit.framework.TestCase;
import org.junit.Test;
import cmmsApplication.Services;
import cmmsApplication.Main;

import cmmsBusiness.AccessVehicle;

import cmmsObjects.Vehicle;

public class IntegrationHSQLTest extends TestCase
{
	public IntegrationHSQLTest(String arg0)
	{
		super(arg0);
	}

	@Test
	public void test()
	{
		AccessVehicle access;
		Vehicle vehicle;

		Services.closeDataAccess();

		System.out.println("\nStarting Integration test of HSQLDB");

		Services.createDataAccess(Main.dbName);

		vehicle = new Vehicle();

		access = new AccessVehicle();

		//vehicle = access.getSequential();
		assertNotNull(vehicle);
		assertTrue("abc123".equals(vehicle.getID()));

		//vehicle = access.getSequential();
		assertNotNull(vehicle);

		Services.closeDataAccess();

		System.out.println("Finished Integration test of HSQLDB");
	}
}