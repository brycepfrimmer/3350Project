package cmmsBusiness;

import cmmsObjects.ManFields;
import cmmsPersistence.DataAccessObject;

public class AccessManFields {
	
	private DataAccessObject accessObject;
	
	public AccessManFields()
	{accessObject = new DataAccessObject();}

	public ManFields getManFields()
	{return accessObject.getManFields();}
	
	public void updateManFields( ManFields fields)
	{accessObject.updateManFields( fields );}

}//End AccessManFields Class
