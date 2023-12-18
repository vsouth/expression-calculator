package ru.vsouth;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        System.out.println(expressionCalculator.calculate("1 * ( 2 - 1 )"));
    }
}