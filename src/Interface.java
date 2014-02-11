
public class Interface {
	private StubDB database;
	
	public Interface()
	{
		database = new StubDB();
	}
	
	public void addVehicle(Vehicle vehicle)
	{
		database.addVehicle(vehicle);
	}
	
	public void removeVehicle( String id)
	{
		database.removeVehicle(id);
	}
	
	public void updateKm( String id, int km, double fuel )
	{
		if (database.getVehicle(id) != null )
		{
			Vehicle vehicle = database.getVehicle(id);
			vehicle.updateKm(km, fuel);
		}
	}
	
	public void printVehicles()
	{
		database.printVehicles();
	}
}
