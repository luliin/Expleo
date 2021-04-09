import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 21:28
 * Project: Expleo
 * Copyright: MIT
 */
public class ProblemOneTest {
    private final ProblemOne problemOne = new ProblemOne();

    @Test
    public void detectAnagramTest1() {
        assertTrue(problemOne.detectAnagram("Mary", "army"));
    }

    @Test
    public void detectAnagramTest2() {
        assertFalse(problemOne.detectAnagram("MaryEllen", "army"));
    }

}
