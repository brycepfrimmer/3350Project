package tests;

import testsIntegration.IntegrationHSQLTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(IntegrationHSQLTest.class);
        return suite;
    }
}
