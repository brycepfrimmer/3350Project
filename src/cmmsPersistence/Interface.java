package cmmsPersistence;

import java.util.ArrayList;

import cmmsObjects.ManFields;
import cmmsObjects.Vehicle;
import cmmsApplication.Services;
import cmmsApplication.Main;

public class Interface {
    /*final static*/ private StubDB database;

    public Interface()
    {
        database = (StubDB) Services.getDataAccess(Main.dbName);
    }

    public boolean addVehicle(Vehicle vehicle) {
        return database.addVehicle(vehicle);
    }

    public boolean removeVehicle(String id) {
        return database.removeVehicle(id);
    }

    public Vehicle getVehicle(String id) {
        Vehicle temp = database.searchByID(id);
        return temp;
    }
    
    public Vehicle[] search(String field, String key) {
    	if (key.contains("*")) {
    		return database.search(field, key.substring(0, key.indexOf('*')));
    	}
    	else {
    		return database.search(field, key);
    	}
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
}
