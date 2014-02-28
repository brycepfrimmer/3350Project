package CMMS;

import java.util.ArrayList;

public class Interface {
    final static private StubDB database = new StubDB();

    public void addVehicle(Vehicle vehicle) {
        database.addVehicle(vehicle);
    }

    public void removeVehicle(String id) {
        database.removeVehicle(id);
    }

    public Vehicle getVehicle(String id) {
        Vehicle temp = database.getVehicle(id);

        return temp;
    }
    
    public ManFields getManFields(){
    	return database.getManFields();
    }

    public double updateKm(String id, int km, double fuel) {
        double fuelEcon = 0.0;
        Vehicle vehicle = null;
        if (database.getVehicle(id) != null) {
            vehicle = database.getVehicle(id);
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
