import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 22:12
 * Project: Expleo
 * Copyright: MIT
 */
public class ProblemTwo {


    public static void main(String[] args) {

        //This is the web page we will use
        String webPageToSearch ="https://www.google.com/";

        //We use this to get the path to our chrome drivers
        WebDriverManager.getInstance(CHROME).setup();

        //Our chrome driver instance
        WebDriver driver = new ChromeDriver();

        //We use our chrome driver instance to get the web page
        driver.get(webPageToSearch);

        //Find all relevant elements from page, in this case all a elements and add them to a list
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("\nAll links on chosen web page: " + webPageToSearch + "\n");

        //For each a element we first map by "href" attribute and then print each element
        links.stream().map(a -> a.getAttribute("href")).forEach(System.out::println);

        //Lastly we close the driver
        driver.close();

    }
}
