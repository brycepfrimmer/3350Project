package cmmsBusiness;

import java.sql.SQLException;
import java.util.ArrayList;

import cmmsObjects.Part;
import cmmsObjects.ServiceItem;
import cmmsObjects.Vehicle;
import cmmsObjects.VehicleInfo;
import cmmsPersistence.DataAccessObject;

public class AccessVehicle {
	
	private Vehicle vehicle;
	private DataAccessObject dataAccess;
	
	public AccessVehicle()
	{
		vehicle = null;
		dataAccess = new DataAccessObject("Vehicles");
		dataAccess.open("Vehicles");
	}
	
	public String addVehicle(VehicleInfo info)
	{
		vehicle = new Vehicle();
		vehicle.setID( info.getID() );
		vehicle.setType( info.getType() );
		vehicle.setManufacturer( info.getManufacturer() );
		vehicle.setModel( info.getModel() );
		vehicle.setRoadWorthy( info.isRoadWorthy() );
		vehicle.setLicensePlate( info.getLicensePlate() );
		vehicle.setOperational( info.isOperational() );
		vehicle.setInsurance( info.getInsurance() );
		vehicle.setYear( info.getYear() );
		vehicle.setKmDriven( info.getKmDriven() );
		vehicle.setKmLastServiced( info.getKmLastServiced() );
		vehicle.setDateLastServiced( info.getDateLastServiced() );
		
		try {
			dataAccess.addVehicle(vehicle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vehicle getVehicle(String ID )
	{
		Vehicle v;
		
		try {
			v = dataAccess.getVehicles("ID", ID)[0];
		} catch (SQLException e) {
			e.printStackTrace();
			v = null;
		}
		
		return v;
	}
	
	public String addPart( String ID, String part )
	{
		try {
			vehicle = dataAccess.getVehicles("ID",ID)[0];
			vehicle.getPartsList().add(new Part(part));
			dataAccess.updateVehicle(vehicle);
		}
		catch (SQLException e) {
			
		}
		
		return null;
	}
	
	public void updateVehicle( Vehicle v )
	{
		try {
			dataAccess.updateVehicle(v);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public Vehicle searchByID( String ID )
	{
	    Vehicle[] vehicles = null;
	    try {
            vehicles = dataAccess.getVehicles("ID", ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    
	    if (vehicles != null && vehicles.length >= 1) {
	        return vehicles[0];
	    } else {
	        return null;
	    }
	}

	public ArrayList<Vehicle> getAllVehicles()
	{
		ArrayList<Vehicle> list = null;
		try {
			Vehicle[] vehicles = dataAccess.getAllVehicles();
			
			list = new ArrayList<Vehicle>();
			for (Vehicle v : vehicles) {
				list.add(v);
				if (v == null)
					System.out.println("ERRRRRR");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Vehicle[] getVehicles(String field, String key)
	{
		Vehicle[] vehicles;
		try {
			vehicles = dataAccess.getVehicles(field, key);
		} catch (SQLException e) {
			e.printStackTrace();
			vehicles = null;
		}
		
		return vehicles;
	}
	
	public String removeVehicle(Vehicle v)
	{
		try {
			dataAccess.removeVehicle(v);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String setPartsList(String id, ArrayList<Part> list) {
		try {
			vehicle = dataAccess.getVehicles("ID", id)[0];
			vehicle.setPartsList( list );
			dataAccess.updateVehicle(vehicle);
		}
		catch (SQLException e) {
			
		}
		return null;
	}

	public String updateKm(String ID, Integer km, Double fuel)
	{
		try {
			vehicle = dataAccess.getVehicles("ID", ID)[0];
			vehicle.updateKm(km, fuel);
			dataAccess.updateVehicle(vehicle);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<Part> getPartsList( String ID )
	{
	    Vehicle[] vehicles = null;
		try {
            vehicles = dataAccess.getVehicles("ID", ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		if (vehicles != null && vehicles.length >= 1) {
		    return vehicles[0].getPartsList();
		} else {
		    return null;
		}
	}
	
	public void removePart(Vehicle vehicle, String part)
	{
		vehicle.removePart(part);
		updateVehicle(vehicle);
	}
	
	public String addServiceEvent( Vehicle vehicle, ServiceItem i, Part p )
	{
		vehicle.addServiceEvent(i, p);
		updateVehicle(vehicle);
		return null;
	}
}//End AccessVehicle Class
