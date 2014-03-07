package cmmsPersistence;

import java.util.ArrayList;

import cmmsBusiness.DBInterface;
import cmmsObjects.ManFields;
import cmmsObjects.Vehicle;

public class DataAccessObject implements DBInterface {
	
	//Stub data access class
	
	public Vehicle getVehicle( String ID )
	{return dbInterface.getVehicle(ID);}
	
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
	
	public void updateVehicle( Vehicle vehicle )
	{
		dbInterface.removeVehicle(vehicle.getID() );
		dbInterface.addVehicle( vehicle );
	}
	
	public void removeVehicle( String id )
	{dbInterface.removeVehicle( id );}
	
	public void updateManFields( ManFields fields )
	{dbInterface.updateManFields( fields );}
	
	public ManFields getManFields()
	{return dbInterface.getManFields();}
}//End DataAccessObject Class
