package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareManufacturer implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getManufacturer().compareTo(o2.getManufacturer());
	}	
}
