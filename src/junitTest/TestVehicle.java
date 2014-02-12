package junitTest;

import CMMS.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVehicle {

	@Test
	public void testVehicle() {
		Vehicle vehicle1 = new Vehicle();
		assertTrue(vehicle1 != null);
	}
	

}
