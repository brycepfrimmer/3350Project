package cmmsBusiness;

import java.util.ArrayList;
import java.util.List;

import cmmsObjects.Part;
import cmmsObjects.Vehicle;
import cmmsObjects.VehicleInfo;
import cmmsPersistence.DataAccessObject;

public class AccessVehicle {
	
	private Vehicle vehicle;
	private DataAccessObject dataAccess;
	private List<Vehicle> vehicles;
	
	public AccessVehicle()
	{
		vehicle = null;
		dataAccess = new DataAccessObject();
		vehicles = null;
	}
	
	public Vehicle[] getAllVehicles()
	{
		//db.getAllVehicles();
		return null;
	}
	
	public boolean addVehicle(VehicleInfo info)
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
		vehicle.setPartsList( info.getPartsList() );
		//dataAccess.addVehicle(vehicle);
		return true;
	}
	
	public boolean addPart( String ID, String part )
	{
		vehicle = dataAccess.getVehicle( ID );
		vehicle.getPartsList().add(new Part(part));
		//dataAccess.updateVehicle(vehicle);
		return true;
	}
	
	public boolean updateVehicle( String ID, VehicleInfo info )
	{
		//dataAccess.updateVehicle()
		return true;
	}
	
	public boolean removeVehicle()
	{
		return true;
	}

	public void setPartsList(String id, ArrayList<Part> list) {
		vehicle = dataAccess.getVehicle( id );
		vehicle.setPartsList( list );
		//dataAccess.updateVehicle(vehicle);	
	}

	public void updateKm(Vehicle vehicle, Integer km, Double fuel) {
		//vehicle.updateKm(km, fuel)
		//db.updateVehicle(vehicle);
		
	}
}
