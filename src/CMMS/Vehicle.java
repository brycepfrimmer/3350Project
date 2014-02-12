package CMMS;
/*
 ****************************************************
 *     Computerized Maintenance Management System 
 * 				Designed and Developed By:
 *         		      Bryce Pfrimmer
 *                    Cody Edwards
 *                    Darwin  Froese
 *                    Delroy Hiebert
 *                    Zac Medeiros
 *                  Copyright (c) 2014
 ****************************************************
 */

import java.util.ArrayList;

public class Vehicle
{
	
	//Class variables, important information about each object.
	/*Mandatory Fields*/
	private String ID;
	private String type;
	private String manufacturer;
	private String model;
	private boolean roadWorthy;
	private String licensePlate;
	private boolean operational;
	private InsurancePolicy insurance;
	
	/*OptionalFields*/
	private int kmDriven;
	private int kmLastServiced;
	private PartsList partsList;
	
	/*Calculated fields*/
	private double fuelEcon;
		
	
	//******Vehicle constructor******//
	public Vehicle()
	{
		//Initially set everything to null
		this.ID = null;
		this.type = null;
		this.manufacturer = null;
		this.model = null;
		this.kmDriven = 0;
		this.kmLastServiced = 0;
		this.partsList = null;
		this.insurance = null;
		this.fuelEcon = 0.0;
		this.roadWorthy = false;
		this.licensePlate = null;
		this.operational = false;
	}//End vehicle()	
	
	public Vehicle(String ID, String type, String man, String model, boolean roadWorthy,
			String LPN, boolean op, String policyNum, String policyType, int km, int kmLS) {
		this.ID = ID;
		this.type = type;
		this.manufacturer = man;
		this.model = model;
		this.roadWorthy = roadWorthy;
		this.licensePlate = LPN;
		this.operational = op;
		setInsurance(policyNum, policyType);
		this.kmDriven = km;
		this.kmLastServiced = kmLS;
	}
	
	public String[] ToString() {
		String[] test;
		
		return test;
	}
	
	public double updateKm(int km, double fuelUsed)
	{
		this.fuelEcon = ( fuelUsed / (km - kmDriven) ) * 100;
		this.kmDriven = km;
		return this.fuelEcon;
	}
	
	public void print()
	{
		System.out.println("Vehicle ID number: " + this.getID());
		System.out.println("\tType: " + this.getType());
		System.out.println("\tManufacturer: " + this.getManufacturer());
		System.out.println("\tModel: " + this.getModel());
		
		System.out.println("\tCurrent kilometers: " + this.getKmDriven());
		System.out.println("\tKilometers when the vehicle was last serviced: " + this.getKmLastServiced());
		
		this.getInsurance().print();
		
		System.out.println("\tCurrent fuel economy: " + this.getFuelEcon() + " Liters/100km" );
		
		System.out.print("\tVehicle roadworthy: ");
		if (this.isRoadWorthy()){ 
			System.out.println("Yes");
			System.out.println("\tLicense plate: " + this.getLicensePlate());
		}
		else{ System.out.println("No"); }
		
		System.out.print("\tVehicle operational: ");
		if (this.isOperational()){ System.out.println("Yes"); }
		else{ System.out.println("No"); }
		
		System.out.println();
	}//End print()

	public void setID(String id) {
		this.ID = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setManufacturer(String man) {
		this.manufacturer = man;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setRoadWorthy(boolean rw) {
        this.roadWorthy = rw;
	}

	public void setLicensePlate(String LPN) {
		this.licensePlate = LPN;
	}

	public void setOperational(boolean op) {
        this.operational = op;
	}

	public void setInsurance(String policyNum, String type) {
        this.insurance = new InsurancePolicy( policyNum, type );
	}

	public void setKmDriven(int km) {
    	this.kmDriven = km;
	}

	public void setKmLastServiced(int km) {
    	this.kmLastServiced = km;
	}

	public void setPartsList() {
		this.partsList = new PartsList();
	}
	//***End of set methods***//


	
	//***get data methods***//
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

	public PartsList getPartsList() {
		return partsList;
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
	//***End of get data methods***
	
	
	//*******************************************************************************************************
	//*****Internal private classes*****//
	private class InsurancePolicy
	{
		private String policyNum;
		private String type;
		
		private InsurancePolicy( String pNum, String message )
		{
			this.policyNum = pNum;
			this.type = message;
		}
		//Print out the insurance object details
		private void print()
		{
			System.out.println("\tInsurance policy number: " + this.policyNum);
			System.out.println("\tInsurance type: " + this.type);
		}
	}//End InsurancePolicy Class
	
	private class PartsList
	{
		private ArrayList<String> partsList;
		
		private PartsList()
		{
			partsList = new ArrayList<String>();
		}
		
		private void addPart(String part)
		{
			partsList.add(part);
		}
		
		private void print()
		{
			for( String part : partsList)
			{
				System.out.println(part);
			}
		}
		
	}//End PartsList Class

	//*******************************************************************************************************	
}//End Vehicle Class
	
	
	
	
	
	
