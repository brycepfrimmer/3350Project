package testsBusiness;

import junit.framework.TestCase;

import cmmsApplication.Services;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle;
import cmmsObjects.VehicleInfo;

public class TestAccessVehicle extends TestCase{

private static String dbName = cmmsApplication.Main.dbName;
	
	public TestAccessVehicle(String arg0)
	{
		super(arg0);
	}
	
	public void testAccess()
	{
		AccessVehicle access;
		Vehicle vehicle;

		Services.closeDataAccess();

		System.out.println("\nStarting test AccessVehicle");

		Services.createDataAccess( dbName );

		vehicle = new Vehicle();

		access = new AccessVehicle();

		//vehicle = access.getVehicle();
		assertNotNull(vehicle);
		assertTrue( "3692481".equals(vehicle.getID() ) );
		
		/*
		VehicleInfo info = new VehicleInfo();
		info.setID("ABC");
		String oldID = vehicle.getID();
		access.updateVehicle( oldID , info );
		assertTrue( access.getVehicle(oldID) == null );
		assertTrue( access.getVehicle("ABC") != null );
		assertTrue( access.getVehicle("ABC").getID() == "ABC" );
		*/

		Services.closeDataAccess();

		System.out.println("Finished test AccessManFields");
	}

}
