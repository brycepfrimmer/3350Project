package cmmsObjects.Vehicle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import cmmsObjects.InsurancePolicy;
import cmmsObjects.Part;
import cmmsObjects.ServiceItem;


/*
 ****************************************************
 *     Computerized Maintenance Management System 
 * 			   Designed and Developed By:
 *         		    Bryce Pfrimmer
 *                  Cody Edwards
 *                  Darwin  Froese
 *                  Delroy Hiebert
 *                  Zac Medeiros
 *              Copyright (c) 2014
 ****************************************************
 */

public class Vehicle {

    // Class variables, important information about each object.
    /* Mandatory Fields */
    private String ID;
    private String type;
    private String manufacturer;
    private String model;
    private boolean roadWorthy;
    private String licensePlate;
    private boolean operational;
    private InsurancePolicy insurance;
    private int year;

    /* OptionalFields */
    private int kmDriven;
    private int kmLastServiced;
    private GregorianCalendar dateLastServiced;
    private ArrayList<Part> partsList;

    /* Calculated fields */
    private double fuelEcon;
    private int currPartIndex;

    // ******Vehicle constructor******//
    public Vehicle() {
        // Initially set everything to null
        this.ID = " ";
        this.type = " ";
        this.manufacturer = " ";
        this.model = " ";
        this.kmDriven = 0;
        this.kmLastServiced = 0;
        this.setDateLastServiced(new GregorianCalendar());
        this.partsList = null;
        insurance = new InsurancePolicy();
        this.fuelEcon = 0.0;
        this.roadWorthy = false;
        this.licensePlate = " ";
        this.operational = false;
        this.year = 0;
        setPartsList();
    }// End vehicle()

    public Vehicle(String ID, String type, String man, String model, int year,
            boolean roadWorthy, String LPN, boolean op, String policyNum,
            String policyType, int km, int kmLS, GregorianCalendar dls) {
        this.ID = ID;
        this.type = type;
        this.manufacturer = man;
        this.model = model;
        this.roadWorthy = roadWorthy;
        this.licensePlate = LPN;
        this.operational = op;
        boolean ret = setInsurance(policyNum, policyType);
        if(!ret) { /*System.err.println("INVALID INSURANCE POLICY NUMBER OR TYPE.");*/ }
        this.kmDriven = km;
        this.kmLastServiced = kmLS;
        this.setDateLastServiced(dls);
        this.year = year;
        setPartsList();
    }

    public String[] ToStrings() {
        String[] vehicle = new String[14];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        vehicle[VehicleFields.ID.ordinal()] = ID;
        vehicle[VehicleFields.TYPE.ordinal()] = type;
        vehicle[VehicleFields.MANUFACTURER.ordinal()] = manufacturer;
        vehicle[VehicleFields.MODEL.ordinal()] = model;
        if(year == 0){
        	vehicle[VehicleFields.YEAR.ordinal()] = " ";
        }
        else{
        	vehicle[VehicleFields.YEAR.ordinal()] = Integer.toString(year);
        }
        if(kmDriven == 0){
        	vehicle[VehicleFields.KM_DRIVEN.ordinal()] = " ";
        }
        else{
        	vehicle[VehicleFields.KM_DRIVEN.ordinal()] = Integer.toString(kmDriven);
        }
        if(kmLastServiced == 0){
        	vehicle[VehicleFields.KM_LAST_SERVICE.ordinal()] = " ";
        }
        else{
        	vehicle[VehicleFields.KM_LAST_SERVICE.ordinal()] = Integer.toString(kmLastServiced);
        }
        if (dateLastServiced == null) {
            vehicle[VehicleFields.DATE_LAST_SERVICE.ordinal()] = " ";
        } else {
            vehicle[VehicleFields.DATE_LAST_SERVICE.ordinal()] = format.format(dateLastServiced.getTime());
        }
        if (roadWorthy) {
            vehicle[VehicleFields.ROADWORTHY.ordinal()] = "Yes";
        } else {
            vehicle[VehicleFields.ROADWORTHY.ordinal()] = "No";
        }
        vehicle[VehicleFields.LICENSE_PLATE.ordinal()] = licensePlate;
        vehicle[VehicleFields.POLICY_NUMBER.ordinal()] = insurance.getPolicyNum(); // Policy Number
        vehicle[VehicleFields.POLICY_TYPE.ordinal()] = insurance.getType(); // Policy Type
        if (operational) {
            vehicle[VehicleFields.OPERATIONAL.ordinal()] = "Yes";
        } else {
            vehicle[VehicleFields.OPERATIONAL.ordinal()] = "No";
        }
        vehicle[VehicleFields.FUEL_ECON.ordinal()] = Integer.toString((int)Math.round(fuelEcon));

        return vehicle;
    }
    
    public void removePart(String part){
    	if(searchPartsList(part)){
    		partsList.remove(currPartIndex);
    	}	
    }
    
    public boolean searchPartsList(String part){
    	boolean found = false;
    	String searchPart = "";
    	
    	part = part.replaceAll("\\s", "");
    	
    	for(int i = 0; i < part.length(); i++) {
    		if(part.charAt(i) != '|') {
    			searchPart += part.charAt(i);
    		}
    		else {
    			i = part.length();
    		}
    	}
    	
    	for(int i = 0; i < partsList.size(); i++){
    		if(searchPart.equals(partsList.get(i).getPartDesc().replaceAll("\\s", ""))){
    			found = true;
    			currPartIndex = i;
    			i = partsList.size();
    		}
    	}
    	return found;
    }

    public double updateKm(int km, double fuelUsed) {
        try {
            if (km > kmDriven && km == (int) km
                    && fuelUsed == (double) fuelUsed) {
                this.fuelEcon = (fuelUsed / (km - kmDriven)) * 100;
                this.kmDriven = km;
                return this.fuelEcon;
            }
        } catch (Exception e) {
            System.out.println("Input not a number!");
        }
        return 0.0;
    }

    public void addServiceEvent(ServiceItem i, Part p) {
        int index = partsList.indexOf(p);
        if (index >= 0) {
            Part temp = partsList.get(index);
            temp.addServiceItem(i);
        } else {
            System.err.println("Invalid part to add service event to. Part not in Vehicle parts list.");
        }
    }
    
    public void print() {
        System.out.println("Vehicle ID number: " + this.getID());
        System.out.println("\tType: " + this.getType());
        System.out.println("\tManufacturer: " + this.getManufacturer());
        System.out.println("\tModel: " + this.getModel());
        System.out.println("\tYear: " + this.getYear());

        System.out.println("\tCurrent kilometers: " + this.getKmDriven());
        System.out.println("\tKilometers when the vehicle was last serviced: "
                + this.getKmLastServiced());

        System.out.println(this.getInsurance().toString());

        System.out.println("\tCurrent fuel economy: " + this.getFuelEcon()
                + " Liters/100km");

        System.out.print("\tVehicle roadworthy: ");
        if (this.isRoadWorthy()) {
            System.out.println("Yes");
            System.out.println("\tLicense plate: " + this.getLicensePlate());
        } else {
            System.out.println("No");
        }

        System.out.print("\tVehicle operational: ");
        if (this.isOperational()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.println();
    }// End print()

    public boolean setID(String id) {
        boolean isValid = false;
        isValid = id != null && id != "" && id.matches("[0-9a-zA-Z]+");
        if (isValid) {
            this.ID = id;
        }
        return isValid;
    }

    public boolean setType(String type) {

        boolean isValid = false;
        isValid = type != null && type != "" && type.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !type.trim().isEmpty();
        if (isValid) {
            this.type = type;
        }
        return isValid;
    }

    public boolean setManufacturer(String man) {

        boolean isValid = false;
        isValid = man != null && man != "" && man.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !man.trim().isEmpty();
        if (isValid) {
            this.manufacturer = man;
        }
        return isValid;
    }

    public boolean setModel(String model) {

        boolean isValid = false;
        isValid = model != null && model != "" && model.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !model.trim().isEmpty();
        if (isValid) {
            this.model = model;
        }
        return isValid;
    }

    public boolean setYear(int year) {

        boolean isValid = false;
        try {
            if (year == (int) year) {
                this.year = year;
                isValid = true;
            }
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    public boolean setRoadWorthy(boolean rw) {
        this.roadWorthy = rw;
        return this.roadWorthy;
    }

    public boolean setLicensePlate(String LPN) {
        boolean isValid = false;
        isValid = LPN != null && LPN != "" && LPN.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !LPN.trim().isEmpty();
        if (isValid) {
            this.licensePlate = LPN;
        }
        return isValid;
    }

    public boolean setOperational(boolean op) {
        this.operational = op;
        return this.operational;
    }

    public boolean checkRequiresService() {
        boolean retVal = false;
        for(Part p : partsList) {
            if(p.checkNeedsService(kmDriven)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }
    
    public boolean setInsurance(String policyNum, String type) {
        boolean isValid = false;
        isValid = policyNum != null && policyNum != "" && type != null && type != ""
                && policyNum.matches("[0-9a-zA-Z]+")
                && type.matches("[0-9a-zA-Z.*\\s+.*]+")
                && !type.trim().isEmpty();
        if (isValid) {
            this.insurance = new InsurancePolicy(policyNum, type);
        }
        return isValid;
    }
    
    public boolean setInsurance( InsurancePolicy policy ) {
    	this.insurance = policy;
    	return true;
    }

    public boolean setKmDriven(int km) {
        boolean isValid = false;
        try {
            if (km == (int) km) {
                this.kmDriven = km;
                isValid = true;
            }
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }

    public boolean setKmLastServiced(int km) {
        boolean isValid = false;
        try {
            if (km == (int) km) {
                this.kmLastServiced = km;
                isValid = true;
            }
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
    
    public boolean setFuelEcon( double fuel )
    {
    	this.fuelEcon = fuel;
    	return true;
    }
    
    private void setPartsList() {
        this.partsList = new ArrayList<Part>();
    }

    public void setPartsList(ArrayList<Part> newPL) {
        this.partsList = newPL;
    }
    
    // ***End of set methods***//

    // ***get data methods***//    
    public String getID() {
        return ID;
    }

    public String getType() {
        return type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public int getKmLastServiced() {
        return kmLastServiced;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }

    public InsurancePolicy getInsurance() {
        return insurance;
    }

    public ArrayList<Part> getPartsList() {
        return partsList;
    }
    
    public Part getPart(String p) {
    	Part part = null;
    	p = p.replaceAll("\\s", "");
    	for(int i = 0; i < partsList.size(); i++) {
    		if(p.matches(partsList.get(i).getPartDesc().replaceAll("\\s", ""))) {
    			part = partsList.get(i);
    			i = partsList.size();
    		}
    	}
    	return part;
    }

    public boolean isOperational() {
        return operational;
    }

    public boolean isRoadWorthy() {
        return roadWorthy;
    }

    public double getFuelEcon() {
        return fuelEcon;
    }

    public GregorianCalendar getDateLastServiced() {
        return dateLastServiced;
    }

    public void setDateLastServiced(GregorianCalendar dateLastServiced) {
        this.dateLastServiced = dateLastServiced;
    }
    
    public String getTodaysTasks(){
    	String tasks = "";
    	
    	for(int i = 0; i < partsList.size(); i++) {
    			tasks = tasks.concat(partsList.get(i).getTodaysTasks(kmDriven));
    	}
    	return tasks;
    }
    
    public boolean partsListIsEmpty() {
    	return partsList.isEmpty();
    }
}// End Vehicle Class
