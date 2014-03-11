package cmmsBusiness;

import java.sql.SQLException;

import cmmsObjects.ManFields;
import cmmsPersistence.DataAccessObject;

public class AccessManFields {
	
	private DataAccessObject accessObject;
	
	public AccessManFields() {
	    accessObject = new DataAccessObject("Vehicles", "ManFields");
	    accessObject.open("ManFields");
	    try {
            accessObject.updateManFields(new ManFields());
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public ManFields getManFields() {
	    ManFields temp = null;
	    try {
            temp = accessObject.getManFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    return temp;
	}
	
	public void updateManFields(ManFields fields) {
	    try {
            accessObject.updateManFields(fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}//End AccessManFields Class
