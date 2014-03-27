package tests;

import acceptanceTests.TestRunner;

public class RunAcceptanceTests
{
    public static void main(String[] args) throws Exception
    {
    	try {
	    	String[] parms = new String[1];
	    	parms[0] = "1";  // sleep parameter in 1/2 seconds
	    	TestRunner.main(parms);
    	} catch (Exception e) {
    		e.printStackTrace();
       		System.out.println( e.getMessage());
    	}
    }
}
