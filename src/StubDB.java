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
			if( id.equals( vehicle.getID() ))
			{
				targetVehicle = vehicle;
			}
		}
		vehicles.remove(targetVehicle);
	}
	
	public void addVehicle()
	{
		Vehicle Vehicle = new Vehicle();
		vehicles.add(Vehicle);
	}
	
	public void printVehicles()
	{
		System.out.println( "Vehicles currently in system...\n");
		for( Vehicle vehicle : vehicles)
		{
			vehicle.print();
		}
	}

}
