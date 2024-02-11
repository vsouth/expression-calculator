package ru.vsouth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionCalculatorTest {
    static ExpressionCalculator expressionCalculator;

    @BeforeEach
    void setUp() {
        expressionCalculator = new ExpressionCalculator();
    }

    @org.junit.jupiter.api.Test
    void testCalculate() {
        assertEquals(6.0,
                expressionCalculator.calculate("(8 + 2 * 5)/(1 + 3 * 2 - 4)"),
                0.0);
        assertEquals(9.0,
                expressionCalculator.calculate("2 * (3 + 4) - 5"),
                0.0);
        assertEquals(14.1,
                expressionCalculator.calculate("-1+15.1*(-1+2)"),
                0.0);
        assertEquals(-1.0,
                expressionCalculator.calculate("-1"),
                0.0);
    }


    @ParameterizedTest
    @ValueSource(strings = {"0.0", "1", "3.003"})
    void testParseNumber(String string) {
        CharacterIterator it = new StringCharacterIterator(string);
        char character = it.current();
        assertNotNull(expressionCalculator.parseNumber(it, character));
    }

    @ParameterizedTest
    @ValueSource(chars = {'/', '*', '+', '-', '('})
    void testIsOperatorTrue(char o) {
        assertTrue(expressionCalculator.isOperator(o));
    }

    @ParameterizedTest
    @ValueSource(chars = {')', '0', 'a'})
    void testIsOperatorFalse(char o) {
        assertFalse(expressionCalculator.isOperator(o));
    }

    @Test
    void getPriority() {
        assertEquals(1,expressionCalculator.getPriority('*'));
        assertEquals(1,expressionCalculator.getPriority('/'));
        assertEquals(2,expressionCalculator.getPriority('+'));
        assertEquals(2,expressionCalculator.getPriority('-'));
        assertEquals(-1,expressionCalculator.getPriority('('));
    }
}