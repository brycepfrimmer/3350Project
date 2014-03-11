package cmmsApplication;

import java.sql.SQLException;

import cmmsPersistence.DataAccessObject;;

public class Services
{
	private static DataAccessObject dataAccessService = null;

	public static DataAccessObject createDataAccess(String dbName, String dbName2)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessObject(dbName);
			try {
				dataAccessService.create(dbName);
				dataAccessService.create(dbName2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataAccessService;
	}

	public static DataAccessObject getDataAccess(String dbName, String dbName2)
	{
		if (dataAccessService == null)
		{
			System.out.println("Connection to data access has not been established.");
			createDataAccess(dbName, dbName2);
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