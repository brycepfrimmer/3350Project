package cmmsPersistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import cmmsObjects.ManFields;
import cmmsObjects.Vehicle.Vehicle;
import cmmsApplication.Services;
import cmmsApplication.Main;

public class Interface {
    /*final static*/ private DataAccessObject database;

    public Interface()
    {
        database = Services.getDataAccess(Main.dbName, Main.dbName2);
    }

    public boolean addVehicle(Vehicle vehicle) {
//        boolean retBool = false;
//        try {
//			retBool = database.addVehicle(vehicle);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//        return retBool;
        return true;
    }

    public boolean removeVehicle(Vehicle v) {
        boolean retBool = false;
        try {
            retBool = database.removeVehicle(v.getID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retBool;
    }

    public Vehicle getVehicle(String id) {
        Vehicle[] temp = null;
        try {
            temp = database.getVehicles("ID", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (temp != null && temp.length >= 1) {
            return temp[0];
        } else {
            return null;
        }
    }
    
    public Vehicle[] search(String field, String key) {
        try {
            return database.getVehicles(field, key);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String searchByID(String id){
    	Vehicle[] currVehicle = null;
        try {
            currVehicle = database.getVehicles("ID", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	String ID;
    	if(currVehicle != null && currVehicle.length >= 1){
    		ID = currVehicle[0].getID();
    	} else {
    		ID = " ";
    	}
    	return ID;
    }
    
    public ManFields getManFields(){
    	try {
            return database.getManFields();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void updateManFields( ManFields fields )
    {
    	try {
            database.updateManFields( fields );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double updateKm(String id, int km, double fuel) {
        double fuelEcon = 0.0;
        Vehicle[] vehicles = null;
        
        try {
            vehicles = database.getVehicles("ID", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (vehicles != null && vehicles.length >= 1) {
            fuelEcon = vehicles[0].updateKm(km, fuel);
        }
        
        try {
            database.updateVehicle(vehicles[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return fuelEcon;
    }

    public ArrayList<Vehicle> getVehicles() {
        try {
            return new ArrayList<Vehicle>(Arrays.asList(database.getAllVehicles()));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public void printVehicles() {
//        database.printVehicles();
//    }
//
//	public Vehicle getVehicle() {
//		return database.getVehicle();
//	}
}
