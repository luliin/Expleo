import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 22:12
 * Project: Expleo
 * Copyright: MIT
 */
public class ProblemTwoTest {


    private ProblemTwo problemTwo = new ProblemTwo();

    @Test
    public final void listLinkTest() {
        problemTwo.getDriver().get("https://google.com");
        List<WebElement> list = problemTwo.findElementsByTagName("a");
        assertFalse(list.isEmpty());
        problemTwo.getDriver().quit();
    }
}
