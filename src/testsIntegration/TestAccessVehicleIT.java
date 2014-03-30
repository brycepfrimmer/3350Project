package testsIntegration;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmmsBusiness.AccessVehicle;
import cmmsObjects.Vehicle.Vehicle;
import cmmsPersistence.StubDataAccessObject;

public class TestAccessVehicleIT extends TestCase{
	AccessVehicle access;
	Vehicle vehicle;

	@Before
	public void setUp() throws Exception {
		access = new AccessVehicle( new StubDataAccessObject() );
		vehicle = new Vehicle();
		vehicle.setID("1234");
	}

	@After
	public void tearDown() throws Exception {
		access = null;
		vehicle = null;
	}

	@Test
	public void testAddVehicle() {
		vehicle.setID("ABC");
		boolean success = access.addVehicle(vehicle);
		assertTrue(success);
	}

	@Test
	public void testGetVehicle() {
		vehicle = access.getVehicle("1234");
		assertTrue(vehicle != null);
		assertTrue(vehicle.getID() == "1234");
	}

	@Test
	public void testUpdateVehicle() {
		vehicle.setType("test");
		access.updateVehicle(vehicle);
		vehicle = access.getVehicle("1234");
		assertTrue(vehicle.getType() == "test");
		
	}

	@Test
	public void testGetAllVehicles() {
		ArrayList<Vehicle> vehicles = access.getAllVehicles();
		assertTrue(vehicles.size() > 0);
		assertTrue(vehicles.get(0).getID() == "1234" );
	}

	@Test
	public void testRemoveVehicle() {
		boolean success = access.removeVehicle("1234");
		assertTrue(success);
	}

}
