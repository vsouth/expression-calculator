package ru.vsouth;

public class Main {
    public static void main(String[] args) {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();

        System.out.println("(8 + 2 * 5)/(1 + 3 * 2 - 4) = " + expressionCalculator.calculate("(8 + 2 * 5)/(1 + 3 * 2 - 4)")); // 6.0
        System.out.println("2 * (3 + 4) - 5 = " + expressionCalculator.calculate("2 * (3 + 4) - 5")); // 9.0
        System.out.println("-1+15.1*(-1+2) =" + expressionCalculator.calculate("-1+15.1*(-1+2)")); // 14.1
        System.out.println("-1 =" + expressionCalculator.calculate("-1")); // -1.0


    }
}