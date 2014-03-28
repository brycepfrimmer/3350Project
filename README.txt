****************************************************
			Comp 3350 - Term Project
    Computerized Maintenance Management System 
 			Designed and Developed By:
                Bryce Pfrimmer
                Cody Edwards
                Darwin  Froese
                Delroy Hiebert
                Zac Medeiros
****************************************************

Computerized Maintenance Management System application. Allows for companies with fleets of vehicles to input,
	edit, track, and remove vehicles in order to increase productivity and decrease vehicle downtime.

Folder structure:

	-> src is for *.java files
		src folder contains 11 packages:
			1) cmmsApplication - contains the Main.java source file that runs our program & a helper Services.java
			2) cmmsBusiness - contains our business logic for the program
			3) cmmsObjects - contains the object classes that the program uses
				- InsurancePolicy, Part, ServiceItem are a few among the object classes for items created for the system
					****cmmsObjects\Vehicle - contains the Vehicle class and the helping comparator classes that help with the sorting of the 
					Vehicles in the window table (ArrayList)
			4) cmmsPersistence - contains the classes used to maintain persistence with the HSQLDB
				- The DataAccess classes are for DataAccess into the DataBase
			5) cmmsPresentation
				- Includes classes such as CMMSInterface, AddVehicle, EditVehicle, ViewVehicle, AddPart, EditPartsList, UpdateKilometers
					are all java classes for generating	and running the GUI
			6) org.eclipse.wb.swt
				- Contains mandatory java files for project implementation.
			7) tests - creates the test suites that are run for both unit testing and integration testing
			8) testsBusiness - includes classes used to test business objects
			9) testsIntegration - includes classes used to test integration with the database
			10) testsObjects - tests for objects. Examples:
				- TestInsurancePolicy class tests creation, implementation and function of the insurance policy class.
				- TestPartsList class test creation, implementation and function of the parts list class
				- TestVehicle class test creation implementation and function of the vehicle class
			11) testsPersistence - tests of the classes in cmmsPersistence
	-> bin is for the *.class files that are created upon compilation
	-> databases - contains the database and the corresponding bat files used to manage the internal database
	-> ATs - contains the scripts used to perform the acceptance tests
	-> lib is for the *.jar files that the project requires
		swt.jar - for the SWT tools
		junit.jar - for junit testing framework
		org.hamcrest.core.*.jar - also required for the junit testing framework
		hsqldb.jar - for the HSQLDB implemented in Itr3
		atr.jar - for the Acceptance tests
	-> AllTests.txt is the output from running the tests (see Building and Running with .bat files section below)
	-> RepoLocation.txt simply contains the URL location of our Github repository
	-> log.txt is the Project Log File where meeting summaries, the work log, and the Developer Task list is kept.
	-> Sketch.pdf is the current sketch of the software architecture
	-> SetClassPath.bat - used internally to set the classpath when using the bat files to run the application from the command line
	-> UserStoriesIteration3.txt - the user and developer stories for iteration 3
	-> Itr3Refactoring.txt - the re-factoring information for the work done in iteration 3


Building and Running with .bat files
-------------------------------------------------------------------------------


**Note:** java must be in your PATH


	1. Run 'Compile.bat' to compile - MUST DO FIRST
	2. Run 'Run.'bat' to run the application
	3. Run 'RunUnitTests.bat' to run the junit tests (output WRITES AllTests.txt replacing old copy of the file if there was one)
	4. Run 'RunIntegrationsTests.bat' to run the Integration tests (output gets appended onto AllTests.txt)
	5. Run 'RunAcceptanceTests.bat' to run the Acceptance tests

	
******NOTE******
DURING ITR2
Due to a team member suddenly and unexpectedly leaving the group with the bulk of the HSQL code, we were forced to complete
almost all of our HSQL integration within 24 hours of the deadline. We were unable to get HSQL completely integrated due to
the way we were physically getting the data from the database.

For this reason we created a new branch in our GitHub repository. **Itr2WorkingFunctionality**
At this point in master we had all of our functionality working for our Iteration 3 stories, just without the HSQL DB.
To checkout our functionality please see this branch, re-running Compile.bat and Run.bat.

I've also included a sub-folder in the submission called Itr2WorkingFunctionality which contains the code to do this.

*****NOTE*****
Iteration 3
Major changes: Printing capability and batch vehicle editing (ability to edit certain fields of more than one vehicle at once)
Major changes to code: Listed in the log.txt under Iteration 3 and in the Itr3Refactoring.txt document
Bugs/Issues also listed at the end of Iteration 3 in log.txt
