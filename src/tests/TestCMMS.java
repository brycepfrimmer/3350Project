package tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import testsBusiness.TestAccessManFields;
import testsBusiness.TestAccessVehicle;
import testsObjects.TestInsurancePolicy;
import testsObjects.TestPart;
import testsObjects.TestVehicle;
import testsObjects.TestServiceItem;
import testsObjects.TestManFields;

public class TestCMMS {

    public static TestSuite suite;

    public static Test suite() {

        suite = new TestSuite("Tests");
        //Test Object classes
        suite.addTestSuite(TestInsurancePolicy.class);
        suite.addTestSuite(TestVehicle.class);
        suite.addTestSuite(TestPart.class);
        suite.addTestSuite(TestServiceItem.class);
        suite.addTestSuite(TestManFields.class);
        
        //Test Business classes
        suite.addTestSuite(TestAccessManFields.class);
        suite.addTestSuite(TestAccessVehicle.class);

        return suite;
    }

}
