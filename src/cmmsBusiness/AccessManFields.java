package cmmsBusiness;

import java.sql.SQLException;

import cmmsObjects.ManFields;
import cmmsPersistence.DataAccessManFields;
import cmmsPersistence.DataAccessObject;
import cmmsPersistence.StubDataAccessObject;

public class AccessManFields implements DataAccessManFields {
	
	private DataAccessManFields accessObject;
	
	public AccessManFields() {
	    accessObject = new DataAccessObject("Vehicles");
	    accessObject.open();
	    try {
            accessObject.updateManFields(new ManFields());
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public AccessManFields( DataAccessManFields object )
	{
		accessObject = (StubDataAccessObject) object;
		accessObject.open();
	}

	@Override
	public ManFields getManFields() {
	    ManFields temp = null;
	    try {
            temp = accessObject.getManFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    return temp;
	}
	
	@Override
	public void updateManFields(ManFields fields) {
	    try {
            accessObject.updateManFields(fields);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}
}//End AccessManFields Class
