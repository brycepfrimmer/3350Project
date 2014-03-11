package cmmsApplication;

import cmmsPresentation.CMMS;

public class Main
{
	public static final String dbName="Vehicles";
	public static final String db2Name = "ManFields";

	public static void main(String[] args)
	{
		startUp();
		new CMMS();
		shutDown();
		System.out.println("All done");
	}

	public static void startUp()
	{
		Services.createDataAccess(dbName, db2Name);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}
}