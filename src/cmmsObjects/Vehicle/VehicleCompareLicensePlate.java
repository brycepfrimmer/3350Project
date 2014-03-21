package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareLicensePlate implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getLicensePlate().compareTo(o2.getLicensePlate());
	}
}
