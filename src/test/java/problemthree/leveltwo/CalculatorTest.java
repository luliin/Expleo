package problemthree.leveltwo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-11
 * Time: 10:24
 * Project: Expleo
 * Copyright: MIT
 */
public class CalculatorTest {

    Calculator calculator = new Calculator();
    @Test
    public final void addTest1() {
        assertEquals(6, calculator.evaluate("2+2+2"));
    }

    @Test
    public final void precedenceAdditionTest1() {
        assertEquals(2, calculator.evaluate("2+2-2"));
    }

    @Test
    public final void precedenceAdditionTest2() {
        assertEquals(6, calculator.evaluate("2+2--2"));
    }

    @Test
    public final void assertThrowsTest1() {
        assertThrows(IllegalArgumentException.class, ()-> calculator.evaluate("*-2+2+2"));
    }

    @Test
    public final void assertThrowsTest2() {
        assertThrows(Exception.class, ()-> calculator.evaluate("-2+2*2"));
    }

    @Test
    public final void assertNotThrowsTest1() {
        assertDoesNotThrow(() -> calculator.evaluate("-1+3+2"));
    }

    @Test
    public final void assertThrowsTest3() {
        assertThrows(Exception.class, ()-> calculator.evaluate("-2*2+2"));
    }

    @Test
    public final void assertThrowsTest4() {
        assertThrows(Exception.class, ()-> calculator.evaluate("10 * 10 -30"));
    }

    @Test
    public final void precedenceMultiplicationTest1() {
        assertEquals(8, calculator.evaluate("2*2*2"));
    }

    @Test
    public final void precedenceMultiplicationTest2() {
        assertEquals(2, calculator.evaluate("2*2/2"));
    }


    @Test
    public final void assertThrowsDivideByZero() {
        assertThrows(ArithmeticException.class, ()-> calculator.evaluate("10 * 10 /0"));
    }




}
