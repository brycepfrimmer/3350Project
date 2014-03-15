package testsObjects;

import java.sql.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmmsObjects.Vehicle;


public class TestVehicle extends TestCase {
    private Vehicle vehicle;
    
    @Before
	public void setUp() throws Exception {
    	GregorianCalendar date = new GregorianCalendar();
    	date.setTime(Date.valueOf("2014-01-23"));
    	vehicle = new Vehicle("vin236", "car", "Dodge", "Viper", 2004,
                true, "ADN 518", true, "Policy one", "General", 155000, 1200, date);
	}
    
    @Test
    public void testVehicle() {
        Vehicle vehicle = new Vehicle();
        assertTrue(vehicle != null);
    }

    @Test
    public void testVehicleParams() {
    	GregorianCalendar date = new GregorianCalendar();
    	date.setTime(Date.valueOf("2014-01-23"));
        Vehicle vehicle1 = new Vehicle("vin278", "truck", "Chev", "Colorado",
                2003, false, "GSL 269", true, "Policy two", "Rec", 150000,
                120000, date);
        assertTrue(vehicle1 != null);
    }

    @Test
    public void testUpdateKm() {
        double update = vehicle.updateKm(155100, 10.0);
        assertTrue(update > 0.0);
        update = vehicle.updateKm(10, 10.0);
        assertTrue(update == 0.0);
    }

    @Test
    public void testSetID() {
        boolean success = vehicle.setID("newID");
        assertTrue(success);
        success = vehicle.setID("    ");
        assertFalse(success);
        success = vehicle.setID(null);
        assertFalse(success);
    }

    @Test
    public void testSetType() {
        boolean success = vehicle.setType("truck");
        assertTrue(success);
        success = vehicle.setType("     ");
        assertFalse(success);
        success = vehicle.setType(null);
        assertFalse(success);
    }

    @Test
    public void testSetManufacturer() {
        boolean success = vehicle.setManufacturer("Saturn");
        assertTrue(success);
        success = vehicle.setManufacturer("   ");
        assertFalse(success);
        success = vehicle.setManufacturer(null);
        assertFalse(success);
    }

    @Test
    public void testSetModel() {
        boolean success = vehicle.setModel("Ion Redline");
        assertTrue(success);
        success = vehicle.setModel("   ");
        assertFalse(success);
        success = vehicle.setModel(null);
        assertFalse(success);
    }

    @Test
    public void testSetYear() {
        boolean success = vehicle.setYear(2004);
        assertTrue(success);
    }

    @Test
    public void testSetRoadWorthy() {
        boolean success = vehicle.setRoadWorthy(true);
        assertTrue(success);
        success = vehicle.setRoadWorthy(false);
        assertFalse(success);
    }

    @Test
    public void testSetLicensePlate() {
        boolean success = vehicle.setLicensePlate("FZL 747");
        assertTrue(success);
        success = vehicle.setLicensePlate("   ");
        assertFalse(success);
        success = vehicle.setLicensePlate(null);
        assertFalse(success);
    }

    @Test
    public void testSetOperational() {
        boolean success = vehicle.setOperational(true);
        assertTrue(success);
        success = vehicle.setOperational(false);
        assertFalse(success);
    }

    @Test
    public void testSetInsurance() {
        boolean success = vehicle.setInsurance("num123", "type");
        assertTrue(success);
        success = vehicle.setInsurance("   ", "type");
        assertFalse(success);
        success = vehicle.setInsurance("num123", "   ");
        assertFalse(success);
        success = vehicle.setInsurance(null, null);
        assertFalse(success);
    }

    @Test
    public void testSetKmDriven() {
        boolean success = vehicle.setKmDriven(160000);
        assertTrue(success);
    }

    @Test
    public void testSetKmLastServiced() {
        boolean success = vehicle.setKmLastServiced(145000);
        assertTrue(success);
    }

}
