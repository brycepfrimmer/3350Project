package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareOperational implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return new Boolean(o1.isOperational()).compareTo(new Boolean(o2.isOperational()));
	}
}
