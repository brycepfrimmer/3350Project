package objects;

import java.util.ArrayList;

public class StubDB {

    private ArrayList<Vehicle> vehicles;
    private static int countNew = 0;
    private ManFields manFields;

    public StubDB() {
        if (countNew == 0) {
            this.vehicles = new ArrayList<Vehicle>();

            PartsList newPL = new PartsList();
            newPL.addPart("PA-149B Oil Filter");
            newPL.addPart("PL-0-170 Fuel Filter");
            newPL.addPart("BreatheRight A29 Air Filter");
            
            Vehicle temp;
            temp = new Vehicle("3692481", "Truck", "Ford", "F150", 2009, true, "ABC 123", true, "abcd1234", "Commuter", 44444, 33333);
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
        manFields = new ManFields();
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

    public Vehicle searchByID(String id) {
    	Vehicle temp = null;
    	
    	for (Vehicle v : vehicles) {
    		if (v != null && id.equals(v.getID()))
    			temp = v;
    	}
    	
    	return temp;
    }
    
    public Vehicle[] search(String field, String key) {
    	ArrayList<Vehicle> found = new ArrayList<Vehicle>();
    	
    	for (Vehicle v : vehicles) {
    		if (v != null) {
    			if (field.equals("ID")) {
    				if (v.getID().startsWith(key)) {
    					found.add(v);
    				}
    			}
	        	else if (field.equals("Type")) {
	    			if (v.getType().startsWith(key)) {
	    				found.add(v);
	    			}
	        	}
		    	else if (field.equals("Manufacturer")) {
	    			if (v.getManufacturer().startsWith(key)) {
	    				found.add(v);
	    			}
		    	}
	        	else if (field.equals("Model")) {
	        		if (v.getModel().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Year")) {
	        		if (new Integer(v.getYear()).toString().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Kilometers")) {
	        		if (new Integer(v.getKmDriven()).toString().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Last service (KM)")) {
	        		if (new Integer(v.getKmLastServiced()).toString().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Is Roadworthy")) {
	        		if (new Boolean(v.isRoadWorthy()).toString().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("License Plate")) {
	        		if (v.getLicensePlate().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Insurance Policy Number")) {
	        		if (v.getInsurance().getPolicyNum().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Insurance Policy Type")) {
	        		if (v.getInsurance().getType().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Is Operational")) {
	        		if (new Boolean(v.isOperational()).toString().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
	        	else if (field.equals("Fuel Economy (L/100km)")) {
	        		if (new Double(v.getFuelEcon()).toString().startsWith(key)) {
	        			found.add(v);
	        		}
	        	}
    		}
    	}
    	
    	Vehicle[] returnList = new Vehicle[found.size()];
    	found.toArray(returnList);
    	
    	return returnList;
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

	public ManFields getManFields() {
		return manFields;
	}
	
}
