package ru.vsouth;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Stack;

import static java.lang.Character.isDigit;

public class ExpressionCalculator {

    public Double calculate(String expression) {
        SimpleCalculator calculator = new SimpleCalculator();

        // двухстековый алгоритм Дейкстры
        Stack<Character> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        expression = "(" + expression + ")";
        CharacterIterator it = new StringCharacterIterator(expression);

        while (it.current() != CharacterIterator.DONE) {
            Character character = it.current();

            if (isDigit(character)) {
                String number = "";
                while (isDigit(character) && it.current() != CharacterIterator.DONE) {
                    number += character.toString();
                    character = it.next();
                }
                values.push(Double.parseDouble(number));
                continue;

            } else if (isOperator(character)) {
                if (!operators.isEmpty() &&
                        operators.peek() != '(' &&
                        getPriority(character) >= getPriority(operators.peek())) {
                    evalTop(calculator, operators, values);
                }
                operators.push(character);

            } else if (character == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    evalTop(calculator, operators, values);
                }
                operators.pop();
            }
            it.next();
        }
        return values.pop();
    }

    private void evalTop(SimpleCalculator calculator, Stack<Character> operators, Stack<Double> values) {
        Character operator = operators.pop();
        Double b = values.pop();
        Double a = values.pop();
        values.push(calculator.calculate(a, b, operator));
    }


    private Boolean isOperator(Character o) {
        return "/*+-(".indexOf(o) > -1;
    }

    private int getPriority(Character o) {
        switch (o) {
            case '*':
            case '/':
                return 1;
            case '+':
            case '-':
                return 2;
            case '(':
                return -1;
            default:
                return -2;
        }
    }
}
