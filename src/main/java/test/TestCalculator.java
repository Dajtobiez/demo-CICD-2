package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import LAB8.Calculator;

public class TestCalculator {
	Calculator calculator = new Calculator();
	@Test
	public void testSum() {
		Assert.assertEquals(calculator.sum(2, 3), 5);
	}
	
	@Test
	public void testSub() {
		Assert.assertEquals(calculator.sum(2, 3), -1);
	}
}
