package ru.vsouth;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionCalculatorTest {

    @org.junit.jupiter.api.Test
    void calculate1() {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        Double result = expressionCalculator.calculate("(8 + 2 * 5)/(1 + 3 * 2 - 4)");
        assertEquals(6.0, result, 0.0);
    }
    @org.junit.jupiter.api.Test
    void calculate2() {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        Double result = expressionCalculator.calculate("(2 * (3 + 4) - 5)");
        assertEquals(9.0, result, 0.0);
    }
    @org.junit.jupiter.api.Test
    void calculate3() {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        Double result = expressionCalculator.calculate("1+15*(2-1)");
        assertEquals(16.0, result, 0.0);
    }
    @org.junit.jupiter.api.Test
    void calculate4() {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        Double result = expressionCalculator.calculate("-1+15*(-1+2)");
        assertEquals(14.0, result, 0.0);
    }
}