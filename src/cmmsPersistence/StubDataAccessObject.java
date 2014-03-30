package cmmsPersistence;

import java.sql.SQLException;
import java.util.ArrayList;

import cmmsObjects.ManFields;
import cmmsObjects.Vehicle.Vehicle;

public class StubDataAccessObject implements DataAccessVehicle, DataAccessManFields {
	ArrayList<Vehicle> database;
	ManFields fields;
	Vehicle vehicle;
	
	public StubDataAccessObject()
	{
		database = new ArrayList<Vehicle>();
	}

	@Override
	public void open() {
		database.clear();
		fields = new ManFields();
		vehicle = new Vehicle();
		vehicle.setID("1234");
		database.add(vehicle);
		
	}

	@Override
	public void close() {
		System.out.println("Closing database connection (simulation; stub db)");
		database = null;		
	}

	@Override
	public Vehicle[] getVehicles(String field, String key) throws SQLException {
		return null;
	}

	@Override
	public Vehicle[] getAllVehicles() throws SQLException {
		Vehicle[] vehicles = new Vehicle[database.size()];
		for( int index = 0; index < database.size(); index ++ )
		{
			vehicles[index] = database.get(index);
		}
		return vehicles;
	}

	@Override
	public boolean addVehicle(Vehicle vehicle) throws SQLException {
		database.add(vehicle);
		return true;
		
	}

	@Override
	public void updateVehicle(Vehicle vehicle) throws SQLException {
		database.set(0, vehicle);		
	}

	@Override
	public boolean removeVehicle(String ID) throws SQLException {
		database.remove(0);
		return true;
	}

	@Override
	public void updateManFields(ManFields m) throws SQLException {
		this.fields = m;
		
	}

	@Override
	public ManFields getManFields() throws SQLException {
		return this.fields;
	}

	@Override
	public void create() throws SQLException {
		//Does not create database connection		
	}

}
