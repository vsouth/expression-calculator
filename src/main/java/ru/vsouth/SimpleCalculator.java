package ru.vsouth;

import java.util.function.BinaryOperator;

public class SimpleCalculator {

    private final BinaryOperator<Double> addition = Double::sum;
    private final BinaryOperator<Double> subtraction = (a, b) -> a - b;
    private final BinaryOperator<Double> multiplication = (a, b) -> a * b;
    private final BinaryOperator<Double> division = (a, b) -> a / b;

    public Double calculate(Double a, Double b, char operator) {
        Double result;
        switch (operator) {
            case '+':
                result = addition.apply(a, b);
                break;
            case '-':
                result = subtraction.apply(a, b);
                break;
            case '*':
                result = multiplication.apply(a, b);
                break;
            case '/':
                result = division.apply(a, b);
                break;
            default:
                throw new RuntimeException();
        }
        return result;
    }

}
