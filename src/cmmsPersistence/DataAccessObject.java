package cmmsPersistence;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;

import cmmsBusiness.DBInterface;
import cmmsObjects.ManFields;
import cmmsObjects.Vehicle;

public class DataAccessObject implements DBInterface/*DataAccess*/ {
	String dbName;
	String db2Name;
	private Connection conn;
	
	private static String[] columns = {
    	"ID",
        "Type",
        "Manufacturer",
        "Model",
        "Year",
        "Kilometers",
        "LastServiceKM",
        "DateLastServiced",
        "IsRoadworthy",
        "LicensePlate",
        "InsurancePolicyNumber",
        "InsurancePolicyType",
        "IsOperational",
        "FuelEconomy"
    };
	
	private static String[] columns2 = {
	    "id",
	    "type",
	    "manufacturer",
	    "model",
	    "kmsDriven",
	    "kmsLastServiced",
	    "dateLastServiced",
	    "insInfo",
	    "year"
	};
	
	public DataAccessObject(String dbName, String db2Name) {
		this.dbName = dbName;
		this.db2Name = db2Name;
	}
	
	public void create(String dbName) throws SQLException
	{		
		// Setup for HSQLDB
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		String url = "jdbc:hsqldb:file:Vehicles"; // stored on disk mode
		conn = DriverManager.getConnection(url, "SA", "");
		
		Statement create = null;
		create = conn.createStatement();
		
		String createTable = "CREATE TABLE Vehicles ( "
				+ columns[0] + " VARCHAR(1024), "
				+ columns[1] + " VARCHAR(1024), "
				+ columns[2] + " VARCHAR(1024), "
				+ columns[3] + " VARCHAR(1024), "
				+ columns[4] + " VARCHAR(1024), "
				+ columns[5] + " VARCHAR(1024), "
				+ columns[6] + " VARCHAR(1024), "
				+ columns[7] + " VARCHAR(1024), "
				+ columns[8] + " VARCHAR(1024), "
				+ columns[9] + " VARCHAR(1024), "
				+ columns[10] + " VARCHAR(1024), "
				+ columns[11] + " VARCHAR(1024), "
				+ columns[12] + " VARCHAR(1024), "
				+ columns[13] + " VARCHAR(1024) "
				+ " )";
		
		String createTable2 = "CREATE TABLE ManFields ( "
		        + "TYPE VARCHAR(1024), "
		        + columns2[0] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[1] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[2] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[3] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[4] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[5] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[6] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[7] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[8] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + " )";
		
		try {
			int i = create.executeUpdate(createTable);
			int j = create.executeUpdate(createTable2);
			
			if (i == -1) {
				System.out.println("Error creating Vehicles Database.");
				System.exit(-1);
			}
			if (j == -1) {
			    System.out.println("Error create ManFields Database.");
			    System.exit(-1);
			}
		}
		catch (SQLException e) {
			
		}
	}
	
	public void open(String dbName)
	{
		String url;
		try
		{
			// Setup for HSQLDB
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:Vehicles;ifexists=true"; // stored on disk mode
			conn = DriverManager.getConnection(url, "SA", "");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close()
	{
		try
		{
			Statement shutdown = conn.createStatement();
			shutdown.execute("SHUTDOWN");
			conn.close();
		}
		catch (Exception e)
		{
			
		}
	}
	
	public Vehicle[] getVehicles(String field, String key) throws SQLException {
		Vehicle[] v = null;
		
		Statement query = null;
		ResultSet searchResult = null;
		String fieldMod = "";
		
		if (field.equals("Last Service (KM)"))
			fieldMod = "LastServiceKM";
		else if (field.equals("Date Last Serviced"))
			fieldMod = "DateLastServiced";
		else if (field.equals("Is Roadworthy"))
			fieldMod = "IsRoadworthy";
		else if (field.equals("License Plate"))
			fieldMod = "LicensePlate";
		else if (field.equals("Insurance Policy Number"))
			fieldMod = "InsurancePolicyNumber";
		else if (field.equals("Insurance Policy Type"))
			fieldMod = "InsurancePolicyType";
		else if (field.equals("Is Operational"))
			fieldMod = "IsOperational";
		else if (field.equals("Fuel Economy (L/100km)"))
			fieldMod = "FuelEconomy";
		else
			fieldMod = field;
			
		
		query = conn.createStatement();
		searchResult = query.executeQuery("SELECT * FROM Vehicles WHERE " + fieldMod + "='" + key + "'");
		
		Object[] objects = ProcessSearch(searchResult);
		v = new Vehicle[objects.length];
		
		for (int i = 0; i < objects.length; i++) {
			v[i] = (Vehicle)objects[i];
		}
		
		return v;
	}
	
	public Vehicle[] getAllVehicles() throws SQLException
	{
		Vehicle[] vehicles = null;
		
		Statement query = null;
		ResultSet searchResult = null;
		
		query = conn.createStatement();
		searchResult = query.executeQuery("SELECT * FROM Vehicles");
		
		Object[] objects = ProcessSearch(searchResult);
		vehicles = new Vehicle[objects.length];
		
		for (int i = 0; i < objects.length; i++) {
			vehicles[i] = (Vehicle)objects[i];
		}
		
		query.close();
		
		return vehicles;
	}
	
	private Object[] ProcessSearch(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int numColumns = meta.getColumnCount();
		Object[] objects = new Object[numColumns];		
		
		for (; rs.next(); ) {
			for (int i = 1; i <= numColumns; i++) {
				objects[i-1] = rs.getObject(i);
			}
		}
		
		return objects;
	}
	
	public boolean addVehicle(Vehicle vehicle) throws SQLException
	{
		Statement add = null;
		add = conn.createStatement();
		
		String addCommand = "INSERT INTO Vehicles("
				+ columns[0] + columns[1] + columns[2]
				+ columns[3] + columns[4] + columns[5]
				+ columns[6] + columns[7] + columns[8]
				+ columns[9] + columns[10] + columns[11]
				+ columns[12] + columns[13] + ") VALUES('"
				+ vehicle.getID() + "', '"
				+ vehicle.getType() + "', '" + vehicle.getManufacturer() + "', '"
		        + vehicle.getModel() + "', '" + new Integer(vehicle.getYear()).toString() + "', '"
		        + new Integer(vehicle.getKmDriven()).toString() + "', '" 
		        + new Integer(vehicle.getKmLastServiced()).toString() + "', '"
		        + vehicle.getDateLastServiced().toString() + "', '"
		        + new Boolean(vehicle.isRoadWorthy()).toString() + "', '"
		        + vehicle.getLicensePlate() + "', '" + vehicle.getInsurance().getPolicyNum() + "', '" 
		        + vehicle.getInsurance().getType() + "', '" 
		        + new Boolean(vehicle.isOperational()).toString() + "', '"
		        + new Double(vehicle.getFuelEcon()).toString()
				+ "')";
		
		int i = add.executeUpdate(addCommand);
		
		if (i == -1) {
			System.out.println("Error inserting into Vehilces Database.");
			return false;
		} else {
		    return true;
		}
	}

	public void updateVehicle(Vehicle vehicle) throws SQLException
	{
		Statement update = null;
		update = conn.createStatement();
		
		String updateCommand = "UPDATE Vehicles SET "
				+ columns[1] + "='" + vehicle.getType() + "', "
				+ columns[2] + "='" + vehicle.getManufacturer() + "', "
		        + columns[3] + "='" + vehicle.getModel() + "', "
				+ columns[4] + "=" + vehicle.getYear() + ", "
				+ columns[5] + "=" + vehicle.getKmDriven() + ", " 
				+ columns[6] + "=" + vehicle.getKmLastServiced() + ", "
		        + columns[7] + "='" + vehicle.getDateLastServiced().toString() + "', "
		        + columns[8] + "='" + new Boolean(vehicle.isRoadWorthy()).toString() + "', "
		        + columns[9] + "='" + vehicle.getLicensePlate() + "', "
		        + columns[10] + "='" + vehicle.getInsurance().getPolicyNum() + "', " 
		        + columns[11] + "='" + vehicle.getInsurance().getType() + "', " 
		        + columns[12] + "='" + new Boolean(vehicle.isOperational()).toString() + "', "
		        + columns[13] + "+'" + new Double(vehicle.getFuelEcon()).toString()
				+ "' WHERE " + columns[0] + "='" + vehicle.getID() + "'";
		
		int i = update.executeUpdate(updateCommand);
		
		if (i == -1) {
			System.out.println("Error updating database entry " + vehicle.getID());
		}
	}
	
	public boolean removeVehicle(Vehicle v) throws SQLException
	{
		Statement delete = null;
		delete = conn.createStatement();
		
		String deleteCommand = "DELETE from Vehicles WHERE " + columns[0] + "='" + v.getID() + "'";
		
		int i = delete.executeUpdate(deleteCommand);
		
		if (i == -1) {
			System.out.println("Error removing database entry " + v.getID());
			return false;
		}
		
		return true;
	}

	private void addManFields() throws SQLException
	{
        Statement add = null;
        add = conn.createStatement();
        
        String addCommand = "INSERT INTO ManFields("
                + "TYPE"
                + columns[0] + columns[1] + columns[2]
                + columns[3] + columns[4] + columns[5]
                + columns[6] + columns[7] + columns[8]
                + ") VALUES('MAN', "
                + false + ", " + false + ", " + false + ", " + false + ", " + false + ", " + false + ", "
                + false + ", " + false + ", " + false
                + ")";
        
        int i = add.executeUpdate(addCommand);
        if (i == -1) {
            System.out.println("Error inserting into ManFields Database");
        }
	}
	
	public void updateManFields(ManFields m) throws SQLException
	{
	    addManFields();
        Statement update = null;
        update = conn.createStatement();
        
        String updateCommand = "UPDATE ManFields SET "
                + columns[0] + "=" + m.getId() + ", "
                + columns[1] + "=" + m.getType() + ", "
                + columns[2] + "=" + m.getManufacturer() + ", "
                + columns[3] + "=" + m.getModel() + ", "
                + columns[4] + "=" + m.getKmsDriven() + ", " 
                + columns[5] + "=" + m.getKmsLastServiced() + ", "
                + columns[6] + "=" + m.getDateLastServiced() + ", "
                + columns[7] + "=" + m.getInsInfo() + ", "
                + columns[8] + "=" + m.getYear() + ", "
                + " WHERE " + "TYPE" + "='MAN'";
        
        int i = update.executeUpdate(updateCommand);
        
        if (i == -1) {
            System.out.println("Error updating database ManFields entry.");
        }
	}
	
    public ManFields getManFields() throws SQLException
    {
        ManFields[] mand = null;
        
        Statement query = null;
        ResultSet searchResult = null;
        
        query = conn.createStatement();
        searchResult = query.executeQuery("SELECT * FROM ManFields");
        
        Object[] objects = ProcessSearch(searchResult);
        mand = new ManFields[objects.length];

        query.close();

        return mand[0];
    }
	
}//End DataAccessObject Class
