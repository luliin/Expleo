package problemthree.levelone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 23:13
 * Project: Expleo
 * Copyright: MIT
 */
public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public final void calculatorAddTest1() {
        assertEquals(5, calculator.evaluate("2 +3"));
    }

    @Test
    public final void calculatorAddTest2() {
        assertNotEquals(-1, calculator.evaluate("2+32"));
    }
    @Test
    public final void calculatorAddTest3() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("232"));
    }

    @Test
    public final void calculatorAddTest4() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("2+3+2"));
    }

    @Test
    public final void calculatorSubtractTest1() {
        assertEquals(-1, calculator.evaluate("2 -3"));
    }
    @Test
    public final void calculatorSubtractTest2() {
        assertNotEquals(-3, calculator.evaluate("2-32"));
    }

    @Test
    public final void calculatorSubtractTest3() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("2-3-2"));
    }

    @Test
    public final void calculatorMultiplyTest1() {
        assertEquals(-6, calculator.evaluate("2 *-3"));
    }
    @Test
    public final void calculatorMultiplyTest2() {
        assertNotEquals(-3, calculator.evaluate("2*32"));
    }
    @Test
    public final void calculatorMultiplyTest3() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("2*3*2"));
    }

    @Test
    public final void calculatorDivideTest1() {
        assertEquals(-0.666666666666666666, calculator.evaluate("-2/3"));
    }
    @Test
    public final void calculatorDivideTest2() {
        assertNotEquals(-3, calculator.evaluate("2/3"));
    }
    @Test
    public final void calculatorDivideTest3() {
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate("2/3/2"));
    }

    @Test
    public final void calculatorDivideTest4() {
        assertThrows(ArithmeticException.class, () -> calculator.evaluate("7/0"));
    }

    @Test
    public final void parseNumberTest() {
        assertThrows(IllegalArgumentException.class, () -> calculator.parseNumber("kla"));
    }


}
