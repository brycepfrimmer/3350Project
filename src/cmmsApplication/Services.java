package cmmsApplication;

import java.sql.SQLException;

import cmmsPersistence.DataAccessObject;;

public class Services
{
	private static DataAccessObject dataAccessService = null;

	public static DataAccessObject createDataAccess(String dbName, String db2Name)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessObject(dbName, db2Name);
			try {
				dataAccessService.create(Main.dbName);
				System.out.println("Creating table: " + db2Name);
				dataAccessService.create(Main.db2Name);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataAccessService;
	}

	public static DataAccessObject getDataAccess(String dbName)
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