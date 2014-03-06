package cmmsApplication;

import cmmsPersistence.StubDB;

public class Services
{
	private static StubDB dataAccessService = null;

	public static StubDB createDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new StubDB(dbName);
			dataAccessService.open(Main.dbName);
		}
		return dataAccessService;
	}

	public static StubDB getDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			System.out.println("Connection to data access has not been established.");
			System.exit(1);
		}
		return dataAccessService;
	}

	public static void closeDataAccess()
	{
		if (dataAccessService != null)
		{
			dataAccessService.close();
		}
		dataAccessService = null;
	}
}