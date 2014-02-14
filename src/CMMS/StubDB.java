package CMMS;

import java.util.ArrayList;

public class StubDB {
	
	private ArrayList<Vehicle> vehicles;
	
	public StubDB()
	{
		this.vehicles = new ArrayList<Vehicle>();
	}
	
	public void removeVehicle( String id )
	{
		Vehicle targetVehicle = null;
		for( Vehicle vehicle : vehicles )
		{
			if( vehicle != null && id.equals( vehicle.getID() ))
			{
				targetVehicle = vehicle;
			}
		}
		vehicles.remove(targetVehicle);
	}
	
	public void addVehicle(Vehicle vehicle)
	{
		vehicles.add(vehicle);
	}
	
	public Vehicle getVehicle(String id)
	{		
		Vehicle targetVehicle = null;
		for (Vehicle vehicle : vehicles)
		{
			if (vehicle != null && vehicle.getID().equals(id))
			{
				targetVehicle = vehicle;
			}
		}
		return targetVehicle;
	}
	
	public ArrayList<Vehicle> getAllVehicles() {
		return vehicles;
	}
	
	public void printVehicles()
	{
		System.out.println( "Vehicles currently in system...\n");
		for( Vehicle vehicle : vehicles)
		{
			if( vehicle != null ){ vehicle.print(); }
		}
	}
}
