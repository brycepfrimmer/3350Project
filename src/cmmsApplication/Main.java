package cmmsApplication;

import cmmsPresentation.CMMS;

public class Main
{
	public static final String dbName="Vehicles";
	public static final String dbName2="ManFields";

	public static void main(String[] args)
	{
		startUp();
		new CMMS();
		shutDown();
		System.out.println("All done");
	}

	public static void startUp()
	{
		Services.createDataAccess(dbName, dbName2);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}