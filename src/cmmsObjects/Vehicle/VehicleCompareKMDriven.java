package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareKMDriven implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getKmDriven() - o2.getKmDriven();
	}
}
