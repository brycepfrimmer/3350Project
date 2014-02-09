
public class Interface {
	private StubDB database;
	
	public Interface()
	{
		database = new StubDB();
	}
	
	public void addVehicle()
	{
		database.addVehicle();
	}
	
	public void removeVehicle( String id)
	{
		database.removeVehicle(id);
	}
	
	public void updateKm( String id, int km, double fuel )
	{
		Vehicle vehicle = database.getVehicle(id);
		if (vehicle != null)
		{
			vehicle.updateKm(km, fuel);
	
		}
	}
	
	public void printVehicles()
	{
		database.printVehicles();
	}
}
