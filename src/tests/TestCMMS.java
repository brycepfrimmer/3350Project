package tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import testsObjects.TestInsurancePolicy;
import testsObjects.TestPart;
import testsObjects.TestVehicle;
import testsObjects.TestServiceItem;
import testsObjects.TestManFields;

import testsPersistence.TestInterface;

public class TestCMMS {

    public static TestSuite suite;

    public static Test suite() {

        suite = new TestSuite("Tests");
        suite.addTestSuite(TestInsurancePolicy.class);
        suite.addTestSuite(TestVehicle.class);
        suite.addTestSuite(TestPart.class);
        suite.addTestSuite(TestServiceItem.class);
        suite.addTestSuite(TestManFields.class);
        
        suite.addTestSuite(TestInterface.class);

        return suite;
    }

}
