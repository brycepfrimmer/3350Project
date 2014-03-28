package cmmsPersistence;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import cmmsObjects.ManFields;
import cmmsObjects.ServiceItem;
import cmmsObjects.Vehicle.Vehicle;
import cmmsObjects.Vehicle.VehicleFields;
import cmmsObjects.Part;

public class DataAccessObject/*DataAccess*/ {
	String dbName;
	private Connection conn;
	
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
	
	public DataAccessObject(String dbName) {
		this.dbName = dbName;
	}
	
	public void create() throws SQLException
	{		
		CreateVehiclesTable();
        CreatePartsTable();
        CreateServiceItemsTable();
		CreateManFieldsTable();
	}
	
	private void CreateVehiclesTable() {
		String createTable = "CREATE TABLE Vehicles ( "
				+ VehicleFields.ID.toString() + " VARCHAR(1024), "
				+ VehicleFields.TYPE.toString() + " VARCHAR(1024), "
				+ VehicleFields.MANUFACTURER.toString() + " VARCHAR(1024), "
				+ VehicleFields.MODEL.toString() + " VARCHAR(1024), "
				+ VehicleFields.YEAR.toString() + " VARCHAR(1024), "
				+ VehicleFields.KM_DRIVEN.toString() + " VARCHAR(1024), "
				+ VehicleFields.KM_LAST_SERVICE.toString() + " VARCHAR(1024), "
				+ VehicleFields.DATE_LAST_SERVICE.toString() + " VARCHAR(1024), "
				+ VehicleFields.ROADWORTHY.toString() + " VARCHAR(1024), "
				+ VehicleFields.LICENSE_PLATE.toString() + " VARCHAR(1024), "
				+ VehicleFields.POLICY_NUMBER.toString() + " VARCHAR(1024), "
				+ VehicleFields.POLICY_TYPE.toString() + " VARCHAR(1024), "
				+ VehicleFields.OPERATIONAL.toString() + " VARCHAR(1024), "
				+ VehicleFields.FUEL_ECON.toString() + " VARCHAR(1024) "
				+ " )";
		
		// Setup for HSQLDB
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		String url = "jdbc:hsqldb:file:databases/Vehicles/Vehicles;ifexists=false"; // stored on disk mode
		try {
			conn = DriverManager.getConnection(url, "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement create = null;
		try {
			create = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			int i = create.executeUpdate(createTable);
			
			if (i == -1) {
				System.out.println("Error creating Vehicles Database.");
				System.exit(-1);
			}
		}
		catch (SQLException e) {
		}
	}

    private void CreatePartsTable() {
        String createTable = "CREATE TABLE Parts ( "
                + VehicleFields.ID.toString() + " VARCHAR(1024), "
                + VehicleFields.PARTS_DESCRIPTION.toString() + " VARCHAR(1024) "
                + " )";

        Statement create = null;
        try {
            create = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            int i = create.executeUpdate(createTable);
            
            if (i == -1) {
                System.out.println("Error creating Parts Table.");
                System.exit(-1);
            }
        }
        catch (SQLException e) {
        }
    }
    
    private void CreateServiceItemsTable() {
        String createTable = "CREATE TABLE ServiceItems ( "
                + VehicleFields.ID.toString() + " VARCHAR(1024), "
                + VehicleFields.PARTS_DESCRIPTION.toString() + " VARCHAR(1024), "
                + VehicleFields.SI_DESCRIPTION.toString() + " VARCHAR(1024), "
                + VehicleFields.SI_SERVICETIME.toString() + " VARCHAR(1024), "
                + VehicleFields.SI_SERVICEKM.toString() + " VARCHAR(1024), "
                + VehicleFields.SI_DATELASTSERVICED.toString() + " VARCHAR(1024), "
                + VehicleFields.SI_KMLASTSERVICED.toString() + " VARCHAR(1024) "
                + " )";

        Statement create = null;
        try {
            create = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            int i = create.executeUpdate(createTable);
            
            if (i == -1) {
                System.out.println("Error creating ServiceItems Table.");
                System.exit(-1);
            }
        }
        catch (SQLException e) {
        }
    }
	
	private void CreateManFieldsTable() {
		String createTable2 = "CREATE TABLE ManFields ( "
		        + "TYPE_KEY VARCHAR(1024), "
		        + columns2[0] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[1] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[2] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[3] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[4] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[5] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[6] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[7] + " BOOLEAN DEFAULT FALSE NOT NULL, "
		        + columns2[8] + " BOOLEAN DEFAULT FALSE NOT NULL "
		        + " )";
		
		Statement create = null;
		try {
			create = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			int j = create.executeUpdate(createTable2);
			
			if (j == -1) {
			    System.out.println("Error create ManFields Table.");
			    System.exit(-1);
			}
		}
		catch (SQLException e) {
			
		}
	}
	
	public void open()
	{
		String url;
		try
		{
			// Setup for HSQLDB
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:databases/" + dbName + "/" + dbName + ";ifexists=true"; // stored on disk mode
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
			shutdown.execute("SHUTDOWN COMPACT");
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
		
		query = conn.createStatement();
		searchResult = query.executeQuery("SELECT * FROM Vehicles WHERE " + field + "='" + key + "'");
		
		Object[] objects = ProcessVehicleSearch(searchResult);
		v = new Vehicle[objects.length];		
		
		for (int i = 0; i < objects.length; i++)
			v[i] = (Vehicle)objects[i];
		
		searchResult.close();
		query.close();
		
		return v;
	}
	
	public Vehicle[] getAllVehicles() throws SQLException {
		Statement query = conn.createStatement();
		ResultSet searchResult = query.executeQuery("SELECT * FROM Vehicles");
		
		Object[] objects = ProcessVehicleSearch(searchResult);
		Vehicle[] vehicles = new Vehicle[objects.length];		
		
		for (int i = 0; i < objects.length; i++)
			vehicles[i] = (Vehicle)objects[i];
		
		searchResult.close();
		query.close();
		
		return vehicles;
	}
	
	private Object[] ProcessVehicleSearch(ResultSet rs) throws SQLException {
		ArrayList<Object> list = new ArrayList<Object>();
		
		while(rs.next()) {
			Date date = null;	
			GregorianCalendar cal = null;
			try {
				date = Date.valueOf(rs.getObject(8).toString());
				cal = new GregorianCalendar();
				cal.setTime(date);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Vehicle v = new Vehicle((String)rs.getObject(1), (String)rs.getObject(2), (String)rs.getObject(3),
									  (String)rs.getObject(4), new Integer(rs.getObject(5).toString()), new Boolean(rs.getObject(9).toString()),
									  (String)rs.getObject(10), new Boolean(rs.getObject(13).toString()), (String)rs.getObject(11),
									  (String)rs.getObject(12), new Integer(rs.getObject(6).toString()), new Integer(rs.getObject(7).toString()),
									  cal);
			v.setFuelEcon(new Double(rs.getObject(14).toString()));
			v.setPartsList(getParts(v.getID()));
			list.add(v);
		}
		
		Object[] objects = new Object[list.size()];
		list.toArray(objects);
		return objects;
	}
	
	private ArrayList<Part> getParts(String vehicleID) throws SQLException {
        ArrayList<Part> retList = new ArrayList<Part>();
	    
	    Statement query = conn.createStatement();
        ResultSet res = query.executeQuery("SELECT * FROM Parts WHERE " + VehicleFields.ID.toString() + "='" + vehicleID + "'");
        
        while(res.next()) {
            String partDesc = (String)res.getObject(2);
            Part newP = new Part(partDesc);
            newP.setServiceItems(getServiceItems(vehicleID, partDesc));
            retList.add(newP);
        }
        
        res.close();
        query.close();
	    return retList;
	}
	
	private ArrayList<ServiceItem> getServiceItems(String vehicleID, String partDesc) throws SQLException {
	    ArrayList<ServiceItem> retList = new ArrayList<ServiceItem>();
        Statement query = conn.createStatement();
        ResultSet r = query.executeQuery("SELECT * FROM ServiceItems WHERE "
        + VehicleFields.ID.toString() + "='" + vehicleID + "' AND "
        + VehicleFields.PARTS_DESCRIPTION.toString() + "='" + partDesc + "'");
        
        while (r.next()) {
            ServiceItem si;
            String sidesc = (String)r.getObject(3);
            long serviceTime = new Long(r.getObject(4).toString());
            int serviceKm = new Integer(r.getObject(5).toString());
            String dsl = (String)r.getObject(6);
            int kmls = new Integer(r.getObject(7).toString());
            if (serviceKm == 0 && kmls == 0) {
                try {
                    Date tempDate = Date.valueOf(dsl);
                    GregorianCalendar dslCal = new GregorianCalendar();
                    dslCal.setTime(tempDate);
                    si = new ServiceItem(sidesc, serviceTime, dslCal);
                    retList.add(si);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (serviceTime == 0 && dsl.isEmpty()) {
                si = new ServiceItem(sidesc, serviceKm, kmls);
                retList.add(si);
            } else {
                System.out.println("ERROR: stored Service Item does not conform.");
            }
        }
        
        r.close();
        query.close();
        return retList;
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
		Statement add = null;
		add = conn.createStatement();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String addCommand = "INSERT INTO Vehicles("
				+ VehicleFields.ID.toString() + ", "
				+ VehicleFields.TYPE.toString() + ", " 
				+ VehicleFields.MANUFACTURER.toString()	+ ", "
				+ VehicleFields.MODEL.toString() + ", "
				+ VehicleFields.YEAR.toString() + ", "
				+ VehicleFields.KM_DRIVEN.toString() + ", "
				+ VehicleFields.KM_LAST_SERVICE.toString() + ", "
				+ VehicleFields.DATE_LAST_SERVICE.toString() + ", "
				+ VehicleFields.ROADWORTHY.toString() + ", "
				+ VehicleFields.LICENSE_PLATE.toString() + ", "
				+ VehicleFields.POLICY_NUMBER.toString() + ", "
				+ VehicleFields.POLICY_TYPE.toString() + ", "
				+ VehicleFields.OPERATIONAL.toString() + ", "
				+ VehicleFields.FUEL_ECON.toString() + ") VALUES('"
				+ vehicle.getID() + "', '"
				+ vehicle.getType() + "', '" + vehicle.getManufacturer() + "', '"
		        + vehicle.getModel() + "', '" + new Integer(vehicle.getYear()).toString() + "', '"
		        + new Integer(vehicle.getKmDriven()).toString() + "', '" 
		        + new Integer(vehicle.getKmLastServiced()).toString() + "', '"
		        + format.format(vehicle.getDateLastServiced().getTime()) + "', '"
		        + new Boolean(vehicle.isRoadWorthy()).toString() + "', '"
		        + vehicle.getLicensePlate() + "', '" + vehicle.getInsurance().getPolicyNum() + "', '" 
		        + vehicle.getInsurance().getType() + "', '" 
		        + new Boolean(vehicle.isOperational()).toString() + "', '"
		        + new Double(vehicle.getFuelEcon()).toString()
				+ "')";
		
		int i = add.executeUpdate(addCommand);
		
		if (i == -1) {
			System.out.println("Error inserting into Vehicles Table.");
		}
		
		addParts(vehicle);
	}

	private void addParts(Vehicle vehicle) throws SQLException {
	    Statement add = conn.createStatement();
        ArrayList<Part> temp = vehicle.getPartsList();
        for (Part p : temp) {
            String addCommand = "INSERT INTO Parts ("
                    + VehicleFields.ID.toString() + ", "
                    + VehicleFields.PARTS_DESCRIPTION.toString()
                    + ") VALUES('"
                    + vehicle.getID() + "', '"
                    + p.getPartDesc() + "')";
            int i = add.executeUpdate(addCommand);
            if (i == -1) {
                System.out.println("Error inserting into Parts Table.");
            }
            addServiceItems(vehicle, p);
        }
	}
	
	private void addServiceItems(Vehicle vehicle, Part p) throws SQLException {
	    Statement add = conn.createStatement();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<ServiceItem> service = p.getServiceItems();
        for (ServiceItem s : service) {
            String d;
            if (s.getDateLastServiced() != null) {
                d = format.format(s.getDateLastServiced().getTime());
            } else {
                d = "";
            }
            String addCommand = "INSERT INTO ServiceItems ("
                    + VehicleFields.ID.toString() + ", "
                    + VehicleFields.PARTS_DESCRIPTION.toString() + ", "
                    + VehicleFields.SI_DESCRIPTION.toString() + ", "
                    + VehicleFields.SI_SERVICETIME.toString() + ", "
                    + VehicleFields.SI_SERVICEKM.toString() + ", "
                    + VehicleFields.SI_DATELASTSERVICED.toString() + ", "
                    + VehicleFields.SI_KMLASTSERVICED.toString() + ") VALUES('"
                    + vehicle.getID() + "', '"
                    + p.getPartDesc() + "', '"
                    + s.getDescription() + "', '"
                    + new Long(s.getServiceTime()).toString() + "', '"
                    + new Integer(s.getServiceKm()).toString() + "', '"
                    + d + "', '"
                    + new Integer(s.getKmLastServiced()).toString() + "')";
            int i = add.executeUpdate(addCommand);
            if (i == -1) {
                System.out.println("Error inserting into ServiceItems Table.");
            }
        }
	}
	
	public void updateVehicle(Vehicle vehicle) throws SQLException {
		Statement update = conn.createStatement();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String updateCommand = "UPDATE Vehicles SET "
				+ VehicleFields.TYPE.toString() + "='" + vehicle.getType() + "', "
				+ VehicleFields.MANUFACTURER.toString() + "='" + vehicle.getManufacturer() + "', "
		        + VehicleFields.MODEL.toString() + "='" + vehicle.getModel() + "', "
				+ VehicleFields.YEAR.toString() + "=" + vehicle.getYear() + ", "
				+ VehicleFields.KM_DRIVEN.toString() + "=" + vehicle.getKmDriven() + ", " 
				+ VehicleFields.KM_LAST_SERVICE.toString() + "=" + vehicle.getKmLastServiced() + ", "
		        + VehicleFields.DATE_LAST_SERVICE.toString() + "='" + format.format(vehicle.getDateLastServiced().getTime()) + "', "
		        + VehicleFields.ROADWORTHY.toString() + "='" + new Boolean(vehicle.isRoadWorthy()).toString() + "', "
		        + VehicleFields.LICENSE_PLATE.toString() + "='" + vehicle.getLicensePlate() + "', "
		        + VehicleFields.POLICY_NUMBER.toString() + "='" + vehicle.getInsurance().getPolicyNum() + "', " 
		        + VehicleFields.POLICY_TYPE.toString() + "='" + vehicle.getInsurance().getType() + "', " 
		        + VehicleFields.OPERATIONAL.toString() + "='" + new Boolean(vehicle.isOperational()).toString() + "', "
		        + VehicleFields.FUEL_ECON.toString() + "='" + new Double(vehicle.getFuelEcon()).toString()
				+ "' WHERE " + VehicleFields.ID.toString() + "='" + vehicle.getID() + "'";
		
		int i = update.executeUpdate(updateCommand);
		
		if (i == -1) {
			System.out.println("Error updating database entry " + vehicle.getID());
		}
		
		updateParts(vehicle);
	}
	
	private void updateParts(Vehicle vehicle) throws SQLException {
        removeParts(vehicle.getID());
        removeServiceItems(vehicle.getID());
        
        addParts(vehicle);
	}

	public boolean removeVehicle(String ID) throws SQLException {
		Statement delete = conn.createStatement();
		
		String deleteCommand = "DELETE from Vehicles WHERE " + VehicleFields.ID.toString() + "='" + ID + "'";
		
		int i = delete.executeUpdate(deleteCommand);
		
		if (i == -1) {
			System.out.println("Error removing database entry " + ID);
			return false;
		}
		
		return true;
	}

	private void removeParts(String ID) throws SQLException {
	    Statement delete = conn.createStatement();
        
        String deleteCommand = "DELETE from Parts WHERE " + VehicleFields.ID.toString() + "='" + ID + "'";
        int i = delete.executeUpdate(deleteCommand);
        if (i == -1) {
            System.out.println("Error removing Parts entry " + ID);
        }
	}
	
    private void removeServiceItems(String ID) throws SQLException {
        Statement delete = conn.createStatement();
        
        String deleteCommand = "DELETE from ServiceItems WHERE " + VehicleFields.ID.toString() + "='" + ID + "'";
        int i = delete.executeUpdate(deleteCommand);
        if (i == -1) {
            System.out.println("Error removing ServiceItems entry " + ID);
        }
    }
	
	private void addManFields() throws SQLException {
        Statement add = null;
        add = conn.createStatement();
        
        String addCommand = "INSERT INTO ManFields("
                + "TYPE_KEY, "
                + columns2[0] + ", " + columns2[1] + ", " + columns2[2] + ", "
                + columns2[3] + ", " + columns2[4] + ", " + columns2[5] + ", "
                + columns2[6] + ", " + columns2[7] + ", " + columns2[8]
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
                + columns2[0] + "=" + m.getId() + ", "
                + columns2[1] + "=" + m.getType() + ", "
                + columns2[2] + "=" + m.getManufacturer() + ", "
                + columns2[3] + "=" + m.getModel() + ", "
                + columns2[4] + "=" + m.getKmsDriven() + ", " 
                + columns2[5] + "=" + m.getKmsLastServiced() + ", "
                + columns2[6] + "=" + m.getDateLastServiced() + ", "
                + columns2[7] + "=" + m.getInsInfo() + ", "
                + columns2[8] + "=" + m.getYear()
                + " WHERE TYPE_KEY='MAN'";
        
        int i = update.executeUpdate(updateCommand);
        
        if (i == -1) {
            System.out.println("Error updating database ManFields entry.");
        }
	}
	
    public ManFields getManFields() throws SQLException
    {
        ManFields mand = null;
        
        Statement query = null;
        ResultSet searchResult = null;
        
        query = conn.createStatement();
        searchResult = query.executeQuery("SELECT * FROM ManFields");
        
        Object[] objects = ProcessManFieldSearch(searchResult);
        
        mand = (ManFields)objects[0];
            
        query.close();

        return mand;
    }

	private Object[] ProcessManFieldSearch(ResultSet rs) throws SQLException {
		ArrayList<Object> list = new ArrayList<Object>();
		
		while(rs.next()) {
			ManFields mand = new ManFields(new Boolean(rs.getObject(1).toString()), new Boolean(rs.getObject(2).toString()),
	        		new Boolean(rs.getObject(3).toString()), new Boolean(rs.getObject(4).toString()),
	        		new Boolean(rs.getObject(5).toString()), new Boolean(rs.getObject(6).toString()),
	        		new Boolean(rs.getObject(7).toString()), new Boolean(rs.getObject(8).toString()),
	        		new Boolean(rs.getObject(9).toString()));
			list.add(mand);
		}
		
		Object[] objects = new Object[list.size()];
		list.toArray(objects);
		return objects;
	}
	
}//End DataAccessObject Class
