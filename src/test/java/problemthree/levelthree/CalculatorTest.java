package problemthree.levelthree;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-11
 * Time: 16:00
 * Project: Expleo
 * Copyright: MIT
 */
public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void multiplyTest1() {
        assertEquals(30, calculator.evaluate("2*3*5"));
    }

    @Test
    public void multiplyTest2() {
        assertEquals(100, calculator.evaluate("1*10*5*2"));
    }

    @Test
    public void throwsExceptionTest1() {
        assertThrows(Exception.class, () -> calculator.evaluate("Hej *2"));
    }

    @Test
    public void cantDivideByZeroTest1() {
        assertThrows(ArithmeticException.class, () -> calculator.evaluate("6/3/0"));
    }

    @Test
    public void divisionTest1() {
        assertEquals(200.4, calculator.evaluate("1002/5"));
    }

    @Test
    public void divisionTest2() {
        assertEquals(0.5, calculator.evaluate("2/2/2"));
    }

    @Test
    public void divisionPrecedenceTest1() {
        assertEquals(-10, calculator.evaluate("2*5*10/5/-2"));
    }

    @Test
    public void additionPrecedenceTest1() {
        assertEquals(-10, calculator.evaluate("10+-20"));
    }
    @Test
    public void additionPrecedenceTest2() {
        assertEquals(10, calculator.evaluate("2+5+10-5+-2"));
    }

    @Test
    public void subtractionPrecedenceTest1() {
        assertEquals(-5, calculator.evaluate("-10+5-100-- 100"));
    }

    @Test
    public void highPrecedenceFirstTest1() {
        assertEquals(53, calculator.evaluate("100/2+3"));
    }

    @Test
    public void highPrecedenceFirstTest2() {
        assertEquals(0, calculator.evaluate("-5*2--10"));
    }

    @Test
    public void lowPrecedenceFirstTest1() {
        assertNotEquals(24, calculator.evaluate("2+4*3"));
    }

    @Test
    public void lowPrecedenceFirstTest2() {
        assertEquals(14, calculator.evaluate("2+4*3"));
    }

    @Test
    public void lowPrecedenceFirstTest3() {
        assertEquals(21, calculator.evaluate("3*3+2*6"));
    }

    @Test
    public void isLowPrecedenceTest1() {
        assertTrue(calculator.helper.isLowPrecedence("-33.0"));
    }

    @Test
    public void isLowPrecedenceTest2() {
        assertFalse(calculator.helper.isLowPrecedence("*"));
    }

    @Test
    public void exampleTest1() {
        assertEquals(122, calculator.evaluate("2+3*40"));
    }
    @Test
    public void exampleTest2() {
        assertEquals(10, calculator.evaluate("2 * 3 + 4"));
    }
    @Test
    public void exampleTest3() {
        assertEquals(3.666666666666667, calculator.evaluate("2 / 3 + 4 -1"));
    }
    @Test
    public void exampleTest4() {
        assertEquals(-10, calculator.evaluate("2-3*4"));
    }
}
