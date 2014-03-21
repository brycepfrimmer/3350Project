package testsIntegration;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import cmmsApplication.Services;
import cmmsApplication.Main;
import cmmsBusiness.AccessManFields;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.ManFields;
import cmmsObjects.Vehicle.Vehicle;

public class IntegrationHSQLTest extends TestCase
{
	public IntegrationHSQLTest(String arg0)
	{
		super(arg0);
	}

	@Test
	public void testVehicle()
	{
		AccessVehicle access;
		Vehicle vehicle;
		ArrayList<Vehicle> vehicles;

		Services.closeDataAccess();
		
		System.out.println("\nStarting Integration test of HSQLDB Vehicles");
		
		Services.createDataAccess(Main.dbName, Main.dbName2);

		access = new AccessVehicle();

		vehicles = access.getAllVehicles();
		
		assertNotNull(vehicles);
		
		vehicle = vehicles.get(0);
		
		assertNotNull(vehicle);

		Services.closeDataAccess();

		System.out.println("Finished Integration test of HSQLDB Vehicles");
	}
	
	@Test
	public void testManFields()
	{
		AccessManFields access;
		ManFields fields;

		Services.closeDataAccess();
		
		System.out.println("\nStarting Integration test of HSQLDB ManFields");
		
		Services.createDataAccess(Main.dbName, Main.dbName2);

		access = new AccessManFields();

		fields = access.getManFields();
		
		assertNotNull(fields);

		Services.closeDataAccess();

		System.out.println("Finished Integration test of HSQLDB ManFields");
	}
}