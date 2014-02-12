package CMMS;

public interface CMMSInterface {
	final Interface dbInterface = new Interface();
	
	final int VEHICLE_FIELD_COUNT = 13;
	enum VEHICLE_FIELDS { 
		ID,
		TYPE,
		MANUFACTURER,
		MODEL,
		YEAR,
		KM_DRIVEN,
		KM_LAST_SERVICE,
		ROADWORTHY,
		LICENSE_PLATE,
		POLICY_NUMBER,
		POLICY_TYPE,
		OPERATIONAL,
		FUEL_ECON
	}
}