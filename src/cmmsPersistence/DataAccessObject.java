package cmmsPersistence;

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
	

	/**********************************************
	private Statement st1, st2, st3;
	private Connection c1;
	private ResultSet rs2, rs3, rs4, rs5;

	private String dbName;
	private String dbType;

	private String cmdString;
	private int updateCount;
	private String result;
	private static String EOF = "  ";

	public DataAccessObject(String dbName)
	{
		this.dbName = dbName;
	}

	public void open(String dbName)
	{
		String url;
		try
		{
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:database/" + dbName; // stored on disk mode
			c1 = DriverManager.getConnection(url, "SA", "");
			st1 = c1.createStatement();
			st2 = c1.createStatement();
			st3 = c1.createStatement();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbName);
	}

	public void close()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			rs2 = st1.executeQuery(cmdString);
			c1.close();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}
	public Vehicle getVehicle( String ID )
	{
		Vehicle v;
		String vID , type, man, model, lpn , insurPolicy, dls, roadWorthy, op;
		int year, km , kmls;
		Date dls;
		try
		{
			cmdString = "Select * from Vehicles where VehicleID = " + ID;
			rs2 = st1.executeQuery(cmdString);

			vID = rs2.getString("VehicleID");
			type = rs2.getString("Type");
			man = rs2.getString("Manufacturer");
			model = rs2.getString("Model");
			lpn = rs2.getString("LicensePlate");
			insurPolicy = rs2.getString("Insurance Policy");
			year = rs2.getInt("Year");
			km = rs2.getInt("KM");
			kmls = rs2.getInt("KMLS");
			dls = rs2.getDate("DLS");
			roadWorthy = rs2.getBoolean("RoadWorthy");
			op = rs2.getBoolean("Operational");


			String[] policies = insurPolicy.split("\n");
			String policyNum = policies[0];
			String policyType = policies[1];
			

			v = new Vehicle(vID, type, man, model, yr, roadWorthy, lpn, op, policyNum, policyType, kilom, kilomLS, dls);

			//ResultSetMetaData md2 = rs2.getMetaData();
		}
		catch (Exception e)
		{
			processSQLError(e);
		}
	}
	public String addVehicle(Vehicle vehicle)
	{
		String values;

		result = null;

		try{
			values = vehicle.getID()
					+", '"+vehicle.getType()
					+"', '"+vehicle.getManufacturer()
					+"', '"+vehicle.getModel()
					+"', '"+Integer.toString(vehicle.getYear())
					+"', '"+String.valueOf(vehicle.isRoadWorthy())
					+"', '"+vehicle.getLicensePlate()
					+"', '"+String.valueOf(vehicle.isOperational())
					+"', '"+vehicle.getInsurance().toString()
					+"', '"+Integer.toString(vehicle.getKmDriven())
					+"', '"+Integer.toString(vehicle.getKmLastServiced())
					+"', '"+ vehicle.getDateLastServiced()
					+"'";
			cmdString = "Insert into Vehicles " + " Values(" + values +")";
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}catch (Exception e){
			result = processSQLError(e);
		}

		return result;
	}

	public String updateVehicle(Vechicle v)
	{
		String values, where;

		result = null;

		try
		{
			//should check for empty values and not update them?

			values = "Type='" +vehicle.getType()
					+"', Manufacturer='" +vehicle.getManufacturer()
					+"', Model='" +vehicle.getModel()
					+"', LicensePlate='" +vehicle.getLicensePlate()
					+"', Year='" +Integer.toString(vehicle.getYear())
					+"', RoadWorthy='" +String.valueOf(vehicle.isRoadWorthy())
					+"', InsurancePolicy='" +vehicle.getInsurance().toString()
					+"', Year='" +Integer.toString(vehicle.getYear())
					+"', KM='" +Integer.toString(vehicle.getKmDriven())
					+"', KMLS='" +Integer.toString(vehicle.getKmLastServiced())
					+"', DLS='" +vehicle.getDateLastServiced()
					+"', Operational='" +String.valueOf(vehicle.isOperational())
					+"'";

			where = "where VehicleID=" + v.getID();
			cmdString = "Update Vehicles " +" Set " +values +" " +where;
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}catch(Exception e) {
			result = processSQLError(e);
		}

		return result;
	}
	public String removeVehicle(Vehicle v)
	{
		String values;

		result = null;
		try
		{
			values = v.getID();
			cmdString = "Delete from Vehicles where VehicleID=" +values;
			//System.out.println(cmdString);
			updateCount = st1.executeUpdate(cmdString);
			result = checkWarning(st1, updateCount);
		}
		catch (Exception e)
		{
			result = processSQLError(e);
		}
		return result;
	}

	public String updateManFields(ManFields m)
	{

	}

	*****************************************************************/
	//Stub data access class
	
	//done
	public Vehicle getVehicle( String ID )
	{return dbInterface.getVehicle(ID);}
	
	//done
	public void addVehicle( Vehicle vehicle )
	{dbInterface.addVehicle( vehicle );}
	
	public Vehicle[] getVehicles( String field, String key)
	{return dbInterface.search( field, key );}
	
	public String searchByID( String ID )
	{
		return dbInterface.searchByID( ID );
	}
	
	public ArrayList<Vehicle> getAllVehicles()
	{return dbInterface.getVehicles();}
	
	//done
	public void updateVehicle( Vehicle vehicle )
	{
		dbInterface.removeVehicle(vehicle.getID() );
		dbInterface.addVehicle( vehicle );
	}
	
	//done
	public void removeVehicle( String id )
	{dbInterface.removeVehicle( id );}
	
	public void updateManFields( ManFields fields )
	{dbInterface.updateManFields( fields );}
	
	public ManFields getManFields()
	{return dbInterface.getManFields();}

	public Vehicle getVehicle() {
		return dbInterface.getVehicle();
	}
}//End DataAccessObject Class
