package cmmsObjects.Vehicle;
import java.util.Comparator;

public class VehicleComparePolicyType implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o1.getInsurance().getType().compareTo(o2.getInsurance().getType());
	}
}
