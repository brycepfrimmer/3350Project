package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareKMLastService implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getKmLastServiced() - o2.getKmLastServiced();
	}
}
