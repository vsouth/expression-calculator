package ru.vsouth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    static SimpleCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new SimpleCalculator();
    }

    @Test
    public void testAddition() {
        assertEquals(5.0, calculator.calculate(2.0, 3.0, '+'), 0.001);
    }

    @Test
    public void testSubtraction() {
        assertEquals(2.0, calculator.calculate(5.0, 3.0, '-'), 0.001);
    }

    @Test
    public void testMultiplication() {
        assertEquals(6.0, calculator.calculate(2.0, 3.0, '*'), 0.001);
    }

    @Test
    public void testDivision() {
        assertEquals(2.0, calculator.calculate(6.0, 3.0, '/'), 0.001);
    }

    @Test
    public void testInvalidOperator() {
        Exception exception = assertThrows(RuntimeException.class, () -> calculator.calculate(2.0, 3.0, '^'));
        assertNotNull(exception);
    }

    @Test
    public void testDivisionByZero() {
        assertEquals(Double.POSITIVE_INFINITY, calculator.calculate(5.0, 0.0, '/'), 0.001);
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(-1.0, calculator.calculate(2.0, 3.0, '-'), 0.001);
    }

    @Test
    public void testZeroResult() {
        assertEquals(0.0, calculator.calculate(0.0, 3.0, '/'), 0.001);
    }

}