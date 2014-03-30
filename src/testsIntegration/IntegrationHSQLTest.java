package testsIntegration;

import java.util.ArrayList;
import java.sql.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmmsApplication.Services;
import cmmsApplication.Main;
import cmmsBusiness.AccessManFields;
import cmmsBusiness.AccessVehicle;
import cmmsObjects.ManFields;
import cmmsObjects.ServiceItem;
import cmmsObjects.Part;
import cmmsObjects.Vehicle.Vehicle;

public class IntegrationHSQLTest extends TestCase
{
	AccessVehicle accessVehicle;
	Vehicle vehicle;
	ArrayList<Vehicle> vehicles;
	AccessManFields accessFields;
	ManFields fields;
	GregorianCalendar date;
	ArrayList<Part> newPL;
	ServiceItem serviceKm;
	
	public IntegrationHSQLTest(String arg0)
	{
		super(arg0);
	}
	
	@Before
	public void setUp() throws Exception {
		Services.closeDataAccess();
		
		System.out.println("\nStarting Integration test of HSQLDB");
		
		Services.createDataAccess(Main.dbName);
		
		accessVehicle = new AccessVehicle();
		accessFields = new AccessManFields();
		
		newPL = new ArrayList<Part>();
		newPL.add(new Part("PA-149B Oil Filter"));
		date = new GregorianCalendar();
        date.setTime(Date.valueOf("2014-01-23"));
		
        vehicle = new Vehicle("ABC", "Truck", "Ford", "F150", 2009, true, "ABC 123", true, "abcd1234", "Commuter", 44444, 33333, date);
        serviceKm = new ServiceItem("Change", 11111, 33333);
        newPL.get(0).addServiceItem(serviceKm);
        vehicle.setPartsList(newPL);
        
        accessVehicle.addVehicle( vehicle );
	}
	
	@After
	public void tearDown() throws Exception {
		
		accessVehicle.removeVehicle( vehicle.getID() );
 		Services.closeDataAccess();
		
		System.out.println("Finished Integration test of HSQLDB");
	}
	
	@Test
	public void testGetSingle()
	{
		vehicle = accessVehicle.getVehicle("ABC");
		assertNotNull( vehicle );
	}
	
	@Test
	public void testAdd()
	{
		vehicle = new Vehicle();
		vehicle.setID("TEST");
		accessVehicle.addVehicle(vehicle);
		vehicle = accessVehicle.getVehicle("TEST");
		assertNotNull( vehicle );
		accessVehicle.removeVehicle(vehicle.getID());
	}

	@Test
	public void testGetAll()
	{		
		vehicles = accessVehicle.getAllVehicles();
		assertNotNull(vehicles);
		Vehicle testVehicle = vehicles.get(0);
		assertNotNull(testVehicle);
		testVehicle = accessVehicle.getVehicle( testVehicle.getID() );
		assertNotNull( testVehicle );
	}
	
	@Test
	public void testUpdate()
	{
		vehicle.setType("Test");
		accessVehicle.updateVehicle(vehicle);
		vehicle = accessVehicle.getVehicle(vehicle.getID());
		assertTrue( vehicle.getType().equals("Test") );
	}
	
	@Test
	public void testUpdateKM()
	{
		
		vehicle.setKmDriven(100);
		accessVehicle.updateVehicle(vehicle);
		accessVehicle.updateKm(vehicle.getID(), 200, 10.0);
		vehicle = accessVehicle.getVehicle( vehicle.getID() );
		assertTrue( vehicle.getFuelEcon() == 10.0 );
	}
	
	@Test
	public void testRemove()
	{
		Vehicle testVehicle = vehicle;
		testVehicle.setID("Test");
		accessVehicle.addVehicle(testVehicle);
		accessVehicle.removeVehicle(testVehicle.getID());
		testVehicle = accessVehicle.getVehicle("Test");
		assertNull( testVehicle );
	}
	
	@Test
	public void testManFields()
	{
		fields = accessFields.getManFields();
		assertNotNull(fields);
	}
}