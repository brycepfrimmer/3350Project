package cmmsPersistence;

import java.sql.SQLException;

import cmmsObjects.ManFields;

public interface DataAccessManFields{
	public ManFields getManFields() throws SQLException;
	public void updateManFields(ManFields fields) throws SQLException;
	public void open();
}
