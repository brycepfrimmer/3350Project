package cmmsApplication;

import cmmsPresentation.CMMS;

public class Main
{
	public static final String dbName="Vehicles";

	public static void main(String[] args)
	{
		startUp();
		new CMMS();
		shutDown();
		System.out.println("All done");
	}

	public static void startUp()
	{
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}