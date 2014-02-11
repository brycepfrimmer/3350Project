
public class MyTest {

	public MyTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Interface myInterface = new Interface();
		myInterface.addVehicle(null);
		myInterface.printVehicles();
		myInterface.updateKm("asdf", 200, 10.0);
		myInterface.printVehicles();
		myInterface.removeVehicle("asdf");
		myInterface.printVehicles();
	}

}
