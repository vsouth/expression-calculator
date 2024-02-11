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
        operators.push('(');
        Deque<Double> values = new ArrayDeque<>();
        CharacterIterator it = new StringCharacterIterator(expression);

        while (it.current() != CharacterIterator.DONE) {
            char character = it.current();
            if (isDigit(character)) {
                values.push(parseNumber(it, character));
                character = it.current();
            }
            if (isOperator(character)) {
                pushOperator(calculator, operators, values, character);
                character = it.current();
            }
            if (character == ')') {
                evalTopWhilePossible(calculator, operators, values);
            }
            it.next();
        }
        evalTopWhilePossible(calculator, operators, values);
        return values.pop();
    }

    private void pushOperator(SimpleCalculator calculator, Deque<Character> operators, Deque<Double> values, char character) {
        addZeroIfNeeded(operators, values, character);
        evalTopIfNeeded(calculator, operators, values, character);
        operators.push(character);
    }

    private void evalTopWhilePossible(SimpleCalculator calculator, Deque<Character> operators, Deque<Double> values) {
        while (!operators.isEmpty() && operators.peek() != '(') {
            evalTop(calculator, operators, values);
        }
        operators.pop();
    }

    private void evalTopIfNeeded(SimpleCalculator calculator, Deque<Character> operators, Deque<Double> values, char character) {
        if (!operators.isEmpty()) {
            if (operators.peek() != '(' && getPriority(character) >= getPriority(operators.peek())) {
                evalTop(calculator, operators, values);
            }
        }
    }

    private void addZeroIfNeeded(Deque<Character> operators, Deque<Double> values, char character) {
        if (!operators.isEmpty()) {
            if (character == '-' && (operators.peek() == '(')) {
                values.push(0.0);
            }
        }
    }

    protected Double parseNumber(CharacterIterator it, Character character) {
        StringBuilder number = new StringBuilder();
        while ((isDigit(character) || character.equals('.')) && it.current() != CharacterIterator.DONE) {
            number.append(character);
            character = it.next();
        }
        return Double.valueOf(number.toString());
    }

    private void evalTop(SimpleCalculator calculator, Deque<Character> operators, Deque<Double> values) {
        Character operator = operators.pop();
        Double b = values.pop();
        Double a = values.pop();
        values.push(calculator.calculate(a, b, operator));
    }


    protected Boolean isOperator(Character o) {
        return "/*+-(".indexOf(o) > -1;
    }

    protected int getPriority(Character o) {
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
