package cmmsBusiness;

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
		dataAccess = new DataAccessObject();
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
		dataAccess.addVehicle(vehicle);
		return null;
	}
	
	public Vehicle getVehicle(String ID )
	{return dataAccess.getVehicle(ID);}
	
	public String addPart( String ID, String part )
	{
		vehicle = dataAccess.getVehicle( ID );
		vehicle.getPartsList().add(new Part(part));
		dataAccess.updateVehicle(vehicle);
		return null;
	}
	
	public String updateVehicle( String ID, VehicleInfo info )
	{
		dataAccess.removeVehicle(ID);
		addVehicle(info);
		return null;
	}
	
	public String searchByID( String ID )
	{
		return dataAccess.searchByID( ID );
	}
	
	public ArrayList<Vehicle> getAllVehicles()
	{return dataAccess.getAllVehicles();}
	
	public Vehicle[] getVehicles(String field, String key)
	{return dataAccess.getVehicles(field, key);}
	
	public String removeVehicle(String id)
	{
		dataAccess.removeVehicle(id);
		return null;
	}

	public String setPartsList(String id, ArrayList<Part> list) {
		vehicle = dataAccess.getVehicle( id );
		vehicle.setPartsList( list );
		dataAccess.updateVehicle(vehicle);
		return null;
	}

	public String updateKm(String ID, Integer km, Double fuel)
	{
		vehicle = dataAccess.getVehicle(ID);
		vehicle.updateKm(km, fuel);
		dataAccess.updateVehicle(vehicle);
		return null;
	}
	
	public ArrayList<Part> getPartsList( String ID )
	{return dataAccess.getVehicle(ID).getPartsList();}
	
	public void removePart(Vehicle vehicle, String part)
	{
		vehicle.removePart(part);
		dataAccess.updateVehicle(vehicle);
	}
	
	public String addServiceEvent( Vehicle vehicle, ServiceItem i, Part p )
	{
		vehicle.addServiceEvent(i, p);
		dataAccess.updateVehicle( vehicle );
		return null;
	}
}//End AccessVehicle Class
