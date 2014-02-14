3350Project
===========

Computerized Maintenance Management System application. Allows for companies with fleets of vehicles to input,
	edit, track, and remove vehicles in order to increase productivity and decrease vehicle downtime.

Folder structure:

	-> src is for *.java files
		src folder contains three packages:
			1) CMMS
				-CMMS class is the main program class, from this class everything else gets run
				-CMMSInterface, AddVehicle, EditVehicle, ViewVehicle, Addpart, EditPartsList, UpdateKilometers
					are java classes for generating	and running the GUI
				-InsurancePolicy, PartsList, Vehicle are object classes for items created by the system
				-StubDB is the stub database that will be replaced in the next iteration with a 
					SQL database
				-Interface is the class that allows the GUI to interact with the database and the 
					object class files without worrying about independent implementation
			2) junitTest
				-TestInsurancePolicy class tests creation, implementation and function
					of the insurance policy class.
				-TestPartsList class test creation, implementation and function of the parts list class
				-TestVehicle class test creation implementation and function of the vehicle class
				-TestCMMS is the test suite that runs all the test cases for the project.
			3) org.eclipse.wb.swt
				-Contains mandatory jar files for project implementation.
	-> bin is for *.class files
	-> lib is for *.jar files


Running it with .bat files
-------------------------------------------------------------------------------


**Note:** java must be in your PATH


	1. Run 'Compile.bat'
	2. Run 'Run.'bat'
	3. Run 'RunUnitTests.bat' to run the junit tests (output goes into AllTests.txt)

