package cmmsObjects;

import java.sql.Date;
import java.util.ArrayList;

public class VehicleInfo {
	
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
    private Date dateLastServiced;
    private ArrayList<Part> partsList;

    /* Calculated fields */
    private double fuelEcon;
    
    public VehicleInfo()
    {}

	public void setID(String iD) {
		this.ID = iD;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setRoadWorthy(boolean roadWorthy) {
		this.roadWorthy = roadWorthy;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public void setOperational(boolean operational) {
		this.operational = operational;
	}

	public void setInsurance(String polNum, String type) {
		this.insurance = new InsurancePolicy(polNum, type);
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setKmDriven(int kmDriven) {
		this.kmDriven = kmDriven;
	}

	public void setKmLastServiced(int kmLastServiced) {
		this.kmLastServiced = kmLastServiced;
	}

	public void setDateLastServiced(Date dateLastServiced) {
		this.dateLastServiced = dateLastServiced;
	}

	public void setPartsList(ArrayList<Part> partsList) {
		this.partsList = partsList;
	}

	public void setFuelEcon(double fuelEcon) {
		this.fuelEcon = fuelEcon;
	}

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

	public boolean isRoadWorthy() {
		return roadWorthy;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public boolean isOperational() {
		return operational;
	}

	public InsurancePolicy getInsurance() {
		return insurance;
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

	public Date getDateLastServiced() {
		return dateLastServiced;
	}

	public ArrayList<Part> getPartsList() {
		return partsList;
	}

	public double getFuelEcon() {
		return fuelEcon;
	}

}
