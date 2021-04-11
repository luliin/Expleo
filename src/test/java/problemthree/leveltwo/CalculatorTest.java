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
}
