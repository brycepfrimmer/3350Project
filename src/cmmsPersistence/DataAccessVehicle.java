package cmmsPersistence;

import java.sql.SQLException;

import cmmsObjects.Vehicle.Vehicle;

public interface DataAccessVehicle{
	
	public void create() throws SQLException;	
	public void open();
	public void close();
	
	public Vehicle[] getVehicles(String field, String key) throws SQLException;
	public Vehicle[] getAllVehicles() throws SQLException;
	public boolean addVehicle(Vehicle vehicle) throws SQLException;
	public void updateVehicle(Vehicle vehicle) throws SQLException;
	public boolean removeVehicle(String ID) throws SQLException;	
	

}
