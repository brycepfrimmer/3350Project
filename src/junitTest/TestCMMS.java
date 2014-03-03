package junitTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestCMMS {

    public static TestSuite suite;

    public static Test suite() {

        suite = new TestSuite("Tests");
        suite.addTestSuite(TestInsurancePolicy.class);
        suite.addTestSuite(TestPart.class);
        suite.addTestSuite(TestVehicle.class);

        return suite;
    }

}
