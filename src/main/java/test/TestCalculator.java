package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import LAB8.Calculator;

public class TestCalculator {
    Calculator calculator = new Calculator();

    @Test
    public void testSum() {
        Assert.assertEquals(calculator.sum(3, 3), 6);
    }

    @Test
    public void testSub() {
        Assert.assertEquals(calculator.sub(2, 2), 0);
    }
}