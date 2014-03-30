package testsIntegration;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmmsObjects.Vehicle.Vehicle;
import cmmsPersistence.DataAccessObject;

public class TestDataAccessObjectIT extends TestCase {
	DataAccessObject data;
	Vehicle vehicle;

	@Before
	public void setUp() throws Exception {
		data = new DataAccessObject( "Stub" );
		data.open();
		vehicle  = new Vehicle();
	}

	@After
	public void tearDown() throws Exception {
		data.close();
		data = null;
	}

	@Test
	public void testGetVehicles() {
		try{
			Vehicle[] vehicles = data.getVehicles("ID", "ABC");
			assertNotNull(vehicles);
			assertTrue( vehicles[0].getID().equals("ABC") );
		}
		catch (Exception e)
		{
		}
	}

	@Test
	public void testGetAllVehicles() {
		try{
			Vehicle[] vehicles = data.getAllVehicles();
			assertNotNull(vehicles);
			assertTrue( vehicles[0].getID().equals("ABC") );
			assertTrue( vehicles[1].getID().equals("XYZ") );
		}
		catch (Exception e)
		{
		}
	}

	@Test
	public void testAddVehicle() {
		vehicle = new Vehicle();
		vehicle.setID("1234");
		try
		{
			data.addVehicle(vehicle);
			Vehicle[] vehicles = data.getAllVehicles();
			assertTrue( vehicles[3].getID().equals("1234") );
		}
		catch(Exception e)
		{
			
		}
		
	}

	@Test
	public void testUpdateVehicle() {
		vehicle.setType("Truck");
		vehicle.setID("ABC");
		try{
			Vehicle[] vehicles = data.getAllVehicles();
			assertTrue( vehicles[0].getType().equals("SUV") );
			data.updateVehicle(vehicle);
			vehicles = data.getAllVehicles();
			assertTrue( vehicles[0].getID().equals("ABC") );
			assertTrue( vehicles[0].getType().equals("Truck") );
		}
		catch (Exception e)
		{
		}
		
	}

	@Test
	public void testRemoveVehicle() {
		try{
			Vehicle[] vehicles = data.getAllVehicles();
			assertTrue( vehicles[0].getID().equals("ABC") );
			data.removeVehicle("ABC");
			vehicles = data.getAllVehicles();
			assertTrue( vehicles[0].getID().equals("XYZ") );
		}
		catch (Exception e)
		{
		}
	}

}
