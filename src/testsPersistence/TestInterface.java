package testsPersistence;

import java.sql.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmmsObjects.Vehicle.Vehicle;
import cmmsPersistence.Interface;

public class TestInterface extends TestCase {

	Interface testInterface;
	Vehicle vehicle;
	@Before
	public void setUp() throws Exception {
		testInterface = new Interface();
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(Date.valueOf("2014-01-23"));
		vehicle = new Vehicle();
		vehicle.setID("444444444");
		vehicle.setDateLastServiced(date);
	}

	@Test
	public void testAddVehicle() {
		boolean success = testInterface.addVehicle(vehicle);
		assertTrue( success );
	}

	@Test
	public void testRemoveVehicle() {
		testInterface.addVehicle(vehicle);
		boolean success = testInterface.removeVehicle(vehicle);
		assertTrue( success );
	}

	@Test
	public void testGetVehicle() {
		testInterface.addVehicle( vehicle );
		Vehicle tempVehicle = testInterface.getVehicle( vehicle.getID() );
		//assertTrue( tempVehicle != null );
		assert( tempVehicle.equals(vehicle) );
		testInterface.removeVehicle( vehicle );
	}

	@Test
	public void testSearch() {
		testInterface.addVehicle(vehicle);
		assertTrue( testInterface.search( "id", vehicle.getID() ) != null );
		testInterface.removeVehicle( vehicle );
	}

	@Test
	public void testGetManFields() {
		assertTrue( testInterface.getManFields() != null );
	}

//	@Test
//	public void testUpdateKm() {
//		testInterface.addVehicle( vehicle );
//		double result = testInterface.updateKm( vehicle.getID(), 100000, 10 );
//		assertTrue( result >= 0 );
//		testInterface.removeVehicle( vehicle );
//	}

	@Test
	public void testGetVehicles() {
		testInterface.addVehicle(vehicle);
		assertTrue( testInterface.getVehicles() != null );
		testInterface.removeVehicle( vehicle );
	}

}
