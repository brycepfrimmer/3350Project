package testsBusiness;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import cmmsApplication.Services;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle.Vehicle;

public class TestAccessVehicle extends TestCase{

private static String dbName = cmmsApplication.Main.dbName;

	AccessVehicle access;
	Vehicle vehicle;
	ArrayList<Vehicle> vehicles;
	
	public TestAccessVehicle(String arg0)
	{
		super(arg0);
	}
	
	@Before
	public void setUp() throws Exception
	{
		Services.closeDataAccess();
		System.out.println("\nStarting test AccessVehicle");
		Services.createDataAccess( dbName );
		access = new AccessVehicle();
		vehicles = null;
		vehicle = new Vehicle();
		vehicle.setID("1234");
		access.addVehicle(vehicle);		
	}
	
	@After
	public void tearDown() throws Exception {
		Services.closeDataAccess();
		System.out.println("Finished test AccessManFields");
	}
	
	@Test
	public void testAccess()
	{
		vehicle = new Vehicle();		
		
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
	}

}
