package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareType implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getType().compareTo(o2.getType());
	}
}
