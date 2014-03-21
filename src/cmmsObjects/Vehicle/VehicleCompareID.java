package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareID implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle vehicle1, Vehicle vehicle2) {
		return vehicle1.getID().compareTo(vehicle2.getID());
	}
}
