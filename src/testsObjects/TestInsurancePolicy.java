package testsObjects;

import junit.framework.TestCase;

import org.junit.Test;

import cmmsObjects.InsurancePolicy;

public class TestInsurancePolicy extends TestCase {
    InsurancePolicy policy = new InsurancePolicy("number123", "General");

    @Test
    public void testInsurancePolicy() {
        InsurancePolicy inPolicy = new InsurancePolicy("polNum123",
                "type general");
        assertTrue(inPolicy != null);
    }

    @Test
    public void testSetPolicyNum() {
        boolean success = policy.setPolicyNum("abc123");
        assertTrue(success);
        success = policy.setPolicyNum("   ");
        assertFalse(success);
        success = policy.setPolicyNum(null);
        assertFalse(success);
    }

    @Test
    public void testSetType() {
        boolean success = policy.setType("recreational");
        assertTrue(success);
        success = policy.setType("   ");
        assertFalse(success);
        success = policy.setType(null);
        assertFalse(success);
    }

    @Test
    public void testToString() {
        String string = policy.toString();
        assertTrue(string.length() > 0);
        assertTrue(string != null);
        assertTrue(!string.trim().isEmpty());
    }

}
