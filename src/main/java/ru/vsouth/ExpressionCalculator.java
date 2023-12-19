package ru.vsouth;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Character.isDigit;

public class ExpressionCalculator {

    public Double calculate(String expression) {
        SimpleCalculator calculator = new SimpleCalculator();

        // двухстековый алгоритм Дейкстры
        Deque<Character> operators = new ArrayDeque<>();
        Deque<Double> values = new ArrayDeque<>();

        expression = "(" + expression + ")";
        CharacterIterator it = new StringCharacterIterator(expression);
        Character previouslyPushed = ' ';

        while (it.current() != CharacterIterator.DONE) {
            Character character = it.current();

            if (isDigit(character)) {
                StringBuilder number = new StringBuilder();
                while (isDigit(character) && it.current() != CharacterIterator.DONE) {
                    number.append(character.toString());
                    character = it.next();
                }
                previouslyPushed = number.charAt(number.length() - 1);
                values.push(Double.parseDouble(number.toString()));
                continue;

            } else if (isOperator(character)) {
                if (character == '-' && (values.isEmpty() || previouslyPushed == '(')) {
                    values.push(0.0);
                }

                if (!operators.isEmpty() &&
                        operators.peek() != '(' &&
                        getPriority(character) >= getPriority(operators.peek())) {
                    evalTop(calculator, operators, values);
                }
                previouslyPushed = character;
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

    private void evalTop(SimpleCalculator calculator, Deque<Character> operators, Deque<Double> values) {
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
