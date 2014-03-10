package cmmsPersistence;

import java.sql.SQLException;
import java.util.ArrayList;

import cmmsObjects.ManFields;
import cmmsObjects.Vehicle;
import cmmsApplication.Services;
import cmmsApplication.Main;

public class Interface {
    /*final static*/ private DataAccessObject database;

    public Interface()
    {
        database = Services.getDataAccess(Main.dbName);
    }

    public void addVehicle(Vehicle vehicle) {
        try {
			database.addVehicle(vehicle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public boolean removeVehicle(String id) {
        return database.removeVehicle(id);
    }

    public Vehicle getVehicle(String id) {
        Vehicle temp = database.searchByID(id);
        return temp;
    }
    
    public Vehicle[] search(String field, String key) {
    		return database.search(field, key);
    }
    
    public String searchByID(String id){
    	Vehicle currVehicle = database.searchByID(id);
    	String ID;
    	if(currVehicle == null){
    		ID = " ";
    	}
    	else{
    		ID = currVehicle.getID();
    	}
    	return ID;
    }
    
    public ManFields getManFields(){
    	return database.getManFields();
    }
    
    public void updateManFields( ManFields fields )
    {
    	database.setManFields( fields );
    }

    public double updateKm(String id, int km, double fuel) {
        double fuelEcon = 0.0;
        Vehicle vehicle = null;
        if (database.searchByID(id) != null) {
            vehicle = database.searchByID(id);
            fuelEcon = vehicle.updateKm(km, fuel);
        }
        return fuelEcon;
    }

    public ArrayList<Vehicle> getVehicles() {
        return database.getAllVehicles();
    }

    public void printVehicles() {
        database.printVehicles();
    }

	public Vehicle getVehicle() {
		return database.getVehicle();
	}
}
