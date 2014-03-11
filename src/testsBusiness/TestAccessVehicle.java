package testsBusiness;

import junit.framework.TestCase;

import cmmsApplication.Services;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle;
import cmmsObjects.VehicleInfo;

public class TestAccessVehicle extends TestCase{

private static String dbName = cmmsApplication.Main.dbName;
private static String dbName2 = cmmsApplication.Main.dbName2;
	
	public TestAccessVehicle(String arg0)
	{
		super(arg0);
	}
	
	public void testAccess()
	{
		AccessVehicle access;
		Vehicle vehicle;

		System.out.println("\nStarting test AccessVehicle");

		Services.createDataAccess( dbName, dbName2 );

		vehicle = new Vehicle();

		access = new AccessVehicle();

		//vehicle = access.getVehicle();
		assertNotNull(vehicle);
		//assertTrue( "3692481".equals(vehicle.getID() ) );
		
		/* Test to change Vehicle ID TODO: for Itr 3
		VehicleInfo info = new VehicleInfo();
		info.setID("ABC");
		String oldID = vehicle.getID();
		access.updateVehicle( oldID , info );
		assertTrue( access.getVehicle(oldID) == null );
		assertTrue( access.getVehicle("ABC") != null );
		assertTrue( access.getVehicle("ABC").getID() == "ABC" );
		*/

		//Services.closeDataAccess();

		System.out.println("Finished test AccessManFields");
	}

}
