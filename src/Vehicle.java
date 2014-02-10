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

import javax.swing.JOptionPane;

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
		
		//Get information from the user
		setData();
	}//End vehicle()	
	
	public void updateKm( int km, double fuelUsed )
	{
		this.fuelEcon = ( fuelUsed / (km - kmDriven) ) * 100;
		this.kmDriven = km;
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
	
	//***set data methods***//
	private void setData()
	{
		/*Required method variables
		 *for temporary data storage*/
		int reply;
		
		this.setID();
		//Only continue getting information if a valid vehicle ID is entered
		if (this.getID() != null)
		{
			this.setType();
			this.setManufacturer();
			this.setModel();
			
			reply = JOptionPane.showConfirmDialog(null, "Do you wish to enter the kilometers on the vehicle?", "Odometer", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	        	this.setKmDriven();
	        }
	        
			reply = JOptionPane.showConfirmDialog(null, "Do you wish to enter the kilometers the vehicle was last serviced at?", "Last serviced", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	        	this.setKmLastServiced();
	        }
	        
	        this.setRoadWorthy();
	        this.setInsurance();
	        this.setOperational();
	        
	        reply = JOptionPane.showConfirmDialog(null, "Do you wish to enter the parts list for the vehicle?", "Parts list", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.YES_OPTION) {
	        	this.setPartsList();
	        }  
		}
	}//End setData()
	
	public void setID() {
		String input;
		boolean isValid;
		input = JOptionPane.showInputDialog("Enter the vehicle ID number.");
		if (input != null)
		{
	    	isValid = input.matches("[0-9a-zA-Z]+");// 1 or more characters long, only valid characters only
	    	while (!isValid)
	    	{
	    		input = JOptionPane.showInputDialog("Invalid input! Enter a valid vehicle ID number.");
	        	isValid = input.matches("[0-9a-zA-Z]+");
	    	}
			this.ID = input;
		}
	}

	public void setType() {
		this.type = JOptionPane.showInputDialog("Enter the vehicle type. Ex car, truck, forklift, etc." );
	}

	public void setManufacturer() {
		this.manufacturer = JOptionPane.showInputDialog("Please enter the vehicle manufacturer.");
	}

	public void setModel() {
		this.model = JOptionPane.showInputDialog("Please enter the vehicle model.");
	}

	public void setRoadWorthy() {
		int reply;
		reply = JOptionPane.showConfirmDialog(null, "Is the vehicle roadworthy?", "Roadworthiness", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	this.roadWorthy = true;
        	this.licensePlate = JOptionPane.showInputDialog("Please enter the vehicle license plate number.");
        }
        else
        {
        	this.roadWorthy = false;
        	this.licensePlate = null;
        }
	}

	public void setLicensePlate() {
		this.licensePlate = JOptionPane.showInputDialog("Please enter the vehicle license plate number.");
	}

	public void setOperational() {
		int reply;
		reply = JOptionPane.showConfirmDialog(null, "Is the vehicle operational?", "Operational", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	this.operational = true;
        }
        else
        {
        	this.operational = false;
        }
	}

	public void setInsurance() {
		String policyNum;
		String type;
		policyNum = JOptionPane.showInputDialog("Please enter the vehicle insurance policy number.");
        type = JOptionPane.showInputDialog("Please enter the type of insurance or a type about the insurance.");
        insurance = new InsurancePolicy( policyNum, type );
	}

	public void setKmDriven() {
		String input;
		boolean isNumeric;
		input = JOptionPane.showInputDialog("Enter the kilometers on the vehicle.");
    	isNumeric = input.matches("[0-9]+");// 1 or more characters long, numbers only
    	isNumeric = input.matches("[0-9]*");// 0 or more characters long, numbers only
    	while (!isNumeric)
    	{
    		input = JOptionPane.showInputDialog("Invalid input! Enter the number of kilometers on the vehicle.");
        	isNumeric = input.matches("[0-9]+");
        	isNumeric = input.matches("[0-9]*");
    	}
    	this.kmDriven = new Integer(input);
	}

	public void setKmLastServiced() {
		String input;
		boolean isNumeric;
		input = JOptionPane.showInputDialog("Enter the kilometers the vehicle was last serviced at");
    	isNumeric = input.matches("[0-9]+");// 1 or more characters long, numbers only
    	isNumeric = input.matches("[0-9]*");// 0 or more characters long, numbers only
    	while (!isNumeric)
    	{
    		input = JOptionPane.showInputDialog("Invalid input! Enter the number of kilometers the vehicle was last serviced at.");
        	isNumeric = input.matches("[0-9]+");
        	isNumeric = input.matches("[0-9]*");
    	}
    	this.kmLastServiced = new Integer(input);
	}

	public void setPartsList() {

		partsList = new PartsList();
		String input;
    	input = JOptionPane.showInputDialog("Enter a vehicle part. Enter 'quit' to stop entering parts.");
    	while ( !input.equals("quit") )
    	{
    		partsList.addPart(input);
    		input = JOptionPane.showInputDialog("Enter a vehicle part. Enter 'quit' to stop entering parts.");
    	}
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
	
	
	
	
	
	
