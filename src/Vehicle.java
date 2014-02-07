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
	private String ID;
	private String type;
	private String manufacturer;
	private String model;
	private int kmDriven;
	private int kmLastServiced;
	
	private PartsList partsList;
	
	private InsurancePolicy insurance;
	
	private double fuelEcon;
	private boolean roadWorthy;
	private String licensePlate;
	private boolean operational;	
	
	//Vehicle constructor
	public Vehicle()
	{
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
	
	private void setData()
	{
		//Required method variables
		int reply;
		String policyNum;
		String type;
		String input;
		boolean isNumeric;
		
		this.ID = JOptionPane.showInputDialog( "Please enter the vehicle ID number" );
		this.type = JOptionPane.showInputDialog("Enter the vehicle type. Ex car, truck, forklift, etc." );
		this.manufacturer = JOptionPane.showInputDialog("Please enter the vehicle manufacturer.");
		this.model = JOptionPane.showInputDialog("Please enter the vehicle model.");
		
		reply = JOptionPane.showConfirmDialog(null, "Do you wish to enter the kilometers on the vehicle?", "Odometer", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
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
        
		reply = JOptionPane.showConfirmDialog(null, "Do you wish to enter the kilometers the vehicle was last serviced at?", "Last serviced", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
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
        
        reply = JOptionPane.showConfirmDialog(null, "Do you wish to enter the parts list for the vehicle?", "Parts list", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	//JOptionPane.showInputDialog("Enter the kilometers the vehicle was last serviced at");
        }
        
        policyNum = JOptionPane.showInputDialog("Please enter the vehicle insurance policy number.");
        type = JOptionPane.showInputDialog("Please enter the type of insurance or a type about the insurance.");
        insurance = new InsurancePolicy( policyNum, type );
        
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
        
        reply = JOptionPane.showConfirmDialog(null, "Is the vehicle operational?", "Operational", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	this.operational = true;
        }
        else
        {
        	this.operational = false;
        }
	}//End setData()
	
	public void print()
	{
		System.out.println("Vehicle ID number: " + this.ID);
		System.out.println("\tType: " + this.type);
		System.out.println("\tManufacturer: " + this.manufacturer);
		System.out.println("\tModel: " + this.model);
		
		System.out.println("\tCurrent kilometers: " + this.kmDriven);
		System.out.println("\tKilometers when the vehicle was last serviced: " + this.kmLastServiced);
		
		this.insurance.print();
		
		System.out.println("\tCurrent fuel economy: " + this.fuelEcon);
		
		System.out.print("\tVehicle roadworthy: ");
		if (this.roadWorthy){ 
			System.out.println("Yes");
			System.out.println("\tLicense plate: " + this.licensePlate);
		}
		else{ System.out.println("No"); }
		
		System.out.print("\tVehicle operational: ");
		if (this.operational){ System.out.println("Yes"); }
		else{ System.out.println("No"); }
		
		System.out.println();
	}//End print()
	
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
	}//End InsurancePolicy
	
	private class PartsList
	{
		private ArrayList<String> partsList;
		
		private PartsList()
		{
			partsList = null;
		}
		
		private void print()
		{
			//Print code here
		}
		
	}//End PartsList
		
}//End Vehicle Class
	
	
	
	
	
	
