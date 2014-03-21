package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareFuelEconomy implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return (int)(o1.getFuelEcon() - o2.getFuelEcon());
	}
}
