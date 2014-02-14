package CMMS;

import java.util.ArrayList;

public class StubDB {

    private ArrayList<Vehicle> vehicles;
    private static int countNew = 0;

    public StubDB() {
        if (countNew == 0) {
            this.vehicles = new ArrayList<Vehicle>();

            PartsList newPL = new PartsList();
            newPL.addPart("PA-149B Oil Filter");
            newPL.addPart("PL-0-170 Fuel Filter");
            newPL.addPart("BreatheRight A29 Air Filter");
            
            Vehicle temp;
            temp = new Vehicle("3692481", "Truck", "Ford", "F-150", 2009, true, "ABC 123", true, "abcd1234", "Commuter", 44444, 33333);
            temp.setPartsList(newPL);
            addVehicle(temp);
            temp = new Vehicle("3692482", "Car", "Fiat", "500", 2012, true, "DEF 456", true, "efgh5678", "BusinessCommercial", 22222, 11111);
            temp.setPartsList(newPL);
            addVehicle(temp);
            temp = new Vehicle("3692483", "Crossover", "Chevrolet", "Envoy", 2010, true, "GHI 789", true, "ijkl9", "BusinessCommercial", 33333, 11411);
            temp.setPartsList(newPL);
            addVehicle(temp);
            temp = new Vehicle("3692484", "Forklift", "Hyatsu", "D-350", 2012, false, "7", true, "mnop0", "BusinessCommercial", 0, 0);
            temp.setPartsList(newPL);
            addVehicle(temp);
            countNew++;
        }
    }

    public void removeVehicle(String id) {
        Vehicle targetVehicle = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && id.equals(vehicle.getID())) {
                targetVehicle = vehicle;
            }
        }
        vehicles.remove(targetVehicle);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle getVehicle(String id) {
        Vehicle targetVehicle = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && vehicle.getID().equals(id)) {
                targetVehicle = vehicle;
            }
        }
        return targetVehicle;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public void printVehicles() {
        System.out.println("Vehicles currently in system...\n");
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                vehicle.print();
            }
        }
    }
}
