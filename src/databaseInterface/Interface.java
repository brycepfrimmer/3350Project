package databaseInterface;

import java.util.ArrayList;

import objects.ManFields;
import objects.StubDB;
import objects.Vehicle;

public class Interface {
    final static private StubDB database = new StubDB();

    public void addVehicle(Vehicle vehicle) {
        database.addVehicle(vehicle);
    }

    public void removeVehicle(String id) {
        database.removeVehicle(id);
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
    
    public ManFields getManFields(){
    	return database.getManFields();
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
