
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
	
	public void printVehicles()
	{
		database.printVehicles();
	}
}
