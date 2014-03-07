package testsObjects;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmmsObjects.VehicleInfo;

public class TestVehicleInfo extends TestCase{

	private VehicleInfo testInfo;
	@Before
	public void setUp() throws Exception {
		testInfo = new VehicleInfo();
	}

	@Test
	public void testVehicleInfo() {
		VehicleInfo info = new VehicleInfo();
		assertTrue( info != null );
	}

	@Test
	public void testSetID() {
		testInfo.setID("ID");
		assertTrue( testInfo.getID() != null );
		testInfo.setID(null);
		assertTrue( testInfo.getID() == null );
	}

	@Test
	public void testSetType() {
		testInfo.setType("car");
		assertTrue( testInfo.getType() != null );
		testInfo.setType(null);
		assertTrue( testInfo.getType() == null );
	}

	@Test
	public void testSetManufacturer() {
		testInfo.setManufacturer("Saturn");
		assertTrue( testInfo.getManufacturer() != null );
		testInfo.setManufacturer(null);
		assertTrue( testInfo.getManufacturer() == null );
	}

	@Test
	public void testSetModel() {
		testInfo.setModel("Ion");
		assertTrue( testInfo.getModel() != null );
		testInfo.setModel(null);
		assertTrue( testInfo.getModel() == null );
	}

	@Test
	public void testSetRoadWorthy() {
		testInfo.setRoadWorthy(true);
		assertTrue( testInfo.isRoadWorthy() );
		testInfo.setRoadWorthy(false);
		assertFalse( testInfo.isRoadWorthy() );
	}

	@Test
	public void testSetLicensePlate() {
		testInfo.setLicensePlate("ABC");
		assertTrue( testInfo.getLicensePlate() != null );
		testInfo.setLicensePlate(null);
		assertTrue( testInfo.getLicensePlate() == null );
	}

	@Test
	public void testSetOperational() {
		testInfo.setOperational(true);
		assertTrue( testInfo.isOperational() );
		testInfo.setOperational(false);
		assertFalse( testInfo.isOperational() );
	}

	@Test
	public void testSetInsurance() {
		testInfo.setInsurance("ID", "num");
		assertTrue( testInfo.getInsurance() != null );
		testInfo.setInsurance(null, null);
		assertTrue( testInfo.getInsurance() != null );
	}

	@Test
	public void testSetYear() {
		testInfo.setYear(2000);
		assertTrue( testInfo.getYear() != 0 );
		testInfo.setYear(0);
		assertTrue( testInfo.getYear() == 0 );
	}

	@Test
	public void testSetKmDriven() {
		testInfo.setKmDriven(100);
		assertTrue( testInfo.getKmDriven() != 0 );
		testInfo.setKmDriven(0);
		assertTrue( testInfo.getKmDriven() == 0 );
	}

	@Test
	public void testSetKmLastServiced() {
		testInfo.setKmLastServiced(100);
		assertTrue( testInfo.getKmLastServiced() != 0 );
		testInfo.setKmLastServiced(0);
		assertTrue( testInfo.getKmLastServiced() == 0 );
	}

	@Test
	public void testSetFuelEcon() {
		testInfo.setFuelEcon( (double)1.0 );
		assertTrue( testInfo.getFuelEcon() != 0.0 );
		testInfo.setFuelEcon(0.0);
		assertTrue( testInfo.getFuelEcon() == 0 );
	}

	@Test
	public void testGetID() {
		assertTrue( testInfo.getID() == null );
		testInfo.setID("ID");
		assertTrue( testInfo.getID() == "ID" );
	}

	@Test
	public void testGetType() {
		assertTrue( testInfo.getType() == null );
		testInfo.setType("type");
		assertTrue( testInfo.getType() == "type" );
	}

	@Test
	public void testGetManufacturer() {
		assertTrue( testInfo.getManufacturer() == null );
		testInfo.setManufacturer("Saturn");
		assertTrue( testInfo.getManufacturer() == "Saturn" );
	}

	@Test
	public void testGetModel() {
		assertTrue( testInfo.getModel() == null );
		testInfo.setModel("Ion");
		assertTrue( testInfo.getModel() != null );
	}

	@Test
	public void testIsRoadWorthy() {
		assertFalse( testInfo.isRoadWorthy() );
		testInfo.setRoadWorthy(true);
		assertTrue( testInfo.isRoadWorthy());
	}

	@Test
	public void testGetLicensePlate() {
		assertTrue( testInfo.getLicensePlate() == null );
		testInfo.setLicensePlate("ABC");
		assertTrue( testInfo.getLicensePlate() != null );
	}

	@Test
	public void testIsOperational() {
		assertFalse( testInfo.isOperational() );
		testInfo.setOperational(true);
		assertTrue( testInfo.isOperational() );
	}

	@Test
	public void testGetYear() {
		assertTrue( testInfo.getYear() == 0 );
		testInfo.setYear(2000);
		assertTrue( testInfo.getYear() == 2000 );
	}

	@Test
	public void testGetKmDriven() {
		assertTrue( testInfo.getKmDriven() == 0 );
		testInfo.setKmDriven(100);
		assertTrue( testInfo.getKmDriven() == 100 );
	}

	@Test
	public void testGetKmLastServiced() {
		assertTrue( testInfo.getKmLastServiced() == 0 );
		testInfo.setKmLastServiced(100);
		assertTrue( testInfo.getKmLastServiced() == 100 );
	}

	@Test
	public void testGetFuelEcon() {
		assertTrue( testInfo.getFuelEcon() == 0 );
		testInfo.setFuelEcon(10);
		assertTrue( testInfo.getFuelEcon() == 10 );
	}

}
