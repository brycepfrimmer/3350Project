package testsBusiness;

import java.util.ArrayList;

import junit.framework.TestCase;
import cmmsApplication.Services;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle.Vehicle;

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
		ArrayList<Vehicle> vehicles = null;

		System.out.println("\nStarting test AccessVehicle");

		Services.createDataAccess( dbName, dbName2 );

		vehicle = new Vehicle();

		access = new AccessVehicle();

		vehicles = access.getAllVehicles();
		vehicle = vehicles.get(0);
		assertNotNull(vehicle);
		assertTrue( "1234".equals(vehicle.getID() ) );
		
		vehicle.setID("ABC");
		access.updateVehicle( vehicle );
		vehicle = access.getVehicle("ABC");
		assertTrue( access.getVehicle("1234") == null );
		assertTrue( access.getVehicle("ABC") != null );
		assertTrue( access.getVehicle("ABC").getID() == "ABC" );
		

		Services.closeDataAccess();

		System.out.println("Finished test AccessManFields");
	}

}
