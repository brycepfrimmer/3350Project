package cmmsBusiness;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import cmmsObjects.Part;
import cmmsObjects.ServiceItem;
import cmmsObjects.Vehicle.Vehicle;
import cmmsObjects.Vehicle.VehicleCompareDateLastService;
import cmmsObjects.Vehicle.VehicleCompareFuelEconomy;
import cmmsObjects.Vehicle.VehicleCompareID;
import cmmsObjects.Vehicle.VehicleCompareKMDriven;
import cmmsObjects.Vehicle.VehicleCompareKMLastService;
import cmmsObjects.Vehicle.VehicleCompareLicensePlate;
import cmmsObjects.Vehicle.VehicleCompareManufacturer;
import cmmsObjects.Vehicle.VehicleCompareModel;
import cmmsObjects.Vehicle.VehicleCompareOperational;
import cmmsObjects.Vehicle.VehicleComparePolicyNumber;
import cmmsObjects.Vehicle.VehicleComparePolicyType;
import cmmsObjects.Vehicle.VehicleCompareRoadworthy;
import cmmsObjects.Vehicle.VehicleCompareType;
import cmmsObjects.Vehicle.VehicleCompareYear;
import cmmsObjects.Vehicle.VehicleFields;
import cmmsPersistence.DataAccessObject;

public class AccessVehicle {
	private static ArrayList<Vehicle> dbVehicles = new ArrayList<Vehicle>();
	private DataAccessObject dataAccess;
	
	public AccessVehicle()
	{
		dataAccess = new DataAccessObject("Vehicles");
		dataAccess.open("Vehicles");
		
		try {
			Vehicle[] vList = dataAccess.getAllVehicles();
			for (Vehicle v : vList)
				dbVehicles.add(v);
		} catch (SQLException e) {
			
		}
	}
	
	public String addVehicle(Vehicle vehicle)
	{		
		try {
			dataAccess.addVehicle(vehicle);
			dbVehicles.add(vehicle);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void sortList(String field) {
		if (field.equals(VehicleFields.ID.toString()))
			Collections.sort(dbVehicles, new VehicleCompareID());
		else if (field.equals(VehicleFields.TYPE.toString()))
			Collections.sort(dbVehicles, new VehicleCompareType());
		else if (field.equals(VehicleFields.MANUFACTURER.toString()))
			Collections.sort(dbVehicles, new VehicleCompareManufacturer());
		else if (field.equals(VehicleFields.MODEL.toString()))
			Collections.sort(dbVehicles, new VehicleCompareModel());
		else if (field.equals(VehicleFields.YEAR.toString()))
			Collections.sort(dbVehicles, new VehicleCompareYear());
		else if (field.equals(VehicleFields.KM_DRIVEN.toString()))
			Collections.sort(dbVehicles, new VehicleCompareKMDriven());
		else if (field.equals(VehicleFields.KM_LAST_SERVICE.toString()))
			Collections.sort(dbVehicles, new VehicleCompareKMLastService());
		else if (field.equals(VehicleFields.DATE_LAST_SERVICE.toString()))
			Collections.sort(dbVehicles, new VehicleCompareDateLastService());
		else if (field.equals(VehicleFields.ROADWORTHY.toString()))
			Collections.sort(dbVehicles, new VehicleCompareRoadworthy());
		else if (field.equals(VehicleFields.LICENSE_PLATE.toString()))
			Collections.sort(dbVehicles, new VehicleCompareLicensePlate());
		else if (field.equals(VehicleFields.POLICY_NUMBER.toString()))
			Collections.sort(dbVehicles, new VehicleComparePolicyNumber());
		else if (field.equals(VehicleFields.POLICY_TYPE.toString()))
			Collections.sort(dbVehicles, new VehicleComparePolicyType());
		else if (field.equals(VehicleFields.OPERATIONAL.toString()))
			Collections.sort(dbVehicles, new VehicleCompareOperational());
		else if (field.equals(VehicleFields.FUEL_ECON.toString()))
			Collections.sort(dbVehicles, new VehicleCompareFuelEconomy());
	}
	
	public Vehicle getVehicle(String ID )
	{
		Vehicle v = null;
	
		for (Vehicle vIter : dbVehicles) {
			if (vIter.getID().equals(ID))
				v = vIter;
		}
		
		return v;
	}
	
	public String addPart( String ID, String part )
	{
		try {
			Vehicle vehicle = null;
			for (Vehicle v : dbVehicles) {
				if (v.getID().equals(ID))
					vehicle = v;
			}
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
			
			for (Vehicle vIter : dbVehicles) {
				if (vIter.getID().equals(v.getID())) {
					dbVehicles.remove(vIter);
				}
			}
			dbVehicles.add(v);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Vehicle> getAllVehicles()
	{		
		return dbVehicles;
	}
	
	public Vehicle[] getVehicles(String field, String k)
	{
		String key = k.toLowerCase();
		ArrayList<Vehicle> list = new ArrayList<Vehicle>();
		
		for (Vehicle v : dbVehicles) {
			if (field.equals(VehicleFields.ID.toString())) {
				if (v.getID().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.TYPE.toString())) {
				if (v.getType().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.MANUFACTURER.toString())) {
				if (v.getManufacturer().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.MODEL.toString())) {
				if (v.getModel().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.YEAR.toString())) {
				if (new Integer(v.getYear()).toString().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.KM_DRIVEN.toString())) {
				if (new Integer(v.getID()).toString().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.KM_LAST_SERVICE.toString())) {
				if (new Integer(v.getKmLastServiced()).toString().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.DATE_LAST_SERVICE.toString())) {
				if (v.getDateLastServiced().toString().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.ROADWORTHY.toString())) {
				if (new Boolean(v.isRoadWorthy()).toString().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.LICENSE_PLATE.toString())) {
				if (v.getLicensePlate().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.POLICY_NUMBER.toString())) {
				if (v.getInsurance().getPolicyNum().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.POLICY_TYPE.toString())) {
				if (v.getInsurance().getType().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.OPERATIONAL.toString())) {
				if (new Boolean(v.isOperational()).toString().toLowerCase().startsWith(key))
					list.add(v);
			}
			else if (field.equals(VehicleFields.FUEL_ECON.toString())) {
				if (new Double(v.getFuelEcon()).toString().toLowerCase().startsWith(key))
					list.add(v);
			}
		}
		
		Vehicle[] vehicles = new Vehicle[list.size()];
		list.toArray(vehicles);
		return vehicles;
	}
	
	public String removeVehicle(String ID)
	{
		try {
			dataAccess.removeVehicle(ID);
			
			for (Vehicle v : dbVehicles) {
				if (v.getID().equals(ID)) {
					int id = dbVehicles.indexOf(v);
					dbVehicles.remove(id);
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String setPartsList(String id, ArrayList<Part> list) {
		try {
			Vehicle vehicle = null;
			for (Vehicle v : dbVehicles) {
				if (v.getID().equals(id)) {
					vehicle = v;
				}
			}
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
			Vehicle vehicle = null;
			for (Vehicle v : dbVehicles) {
				if (v.getID().equals(ID)) {
					vehicle = v;
				}
			}
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
		Vehicle vehicle = null;
		for (Vehicle v : dbVehicles) {
			if (v.getID().equals(ID)) {
				vehicle = v;
			}
		}
		
		return vehicle.getPartsList();
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
