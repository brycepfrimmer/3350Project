package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareYear implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getYear() - o2.getYear();
	}
}
