package cmmsObjects.Vehicle;

import java.util.Comparator;

public class VehicleCompareRoadworthy implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return new Boolean(o1.isRoadWorthy()).compareTo(new Boolean(o2.isRoadWorthy()));
	}	
}
