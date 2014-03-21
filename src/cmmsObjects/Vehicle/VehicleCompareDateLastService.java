package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareDateLastService implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getDateLastServiced().compareTo(o2.getDateLastServiced());
	}
}
