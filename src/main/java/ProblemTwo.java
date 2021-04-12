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

    private WebDriver driver = null;
    private String webPageToSearch;

    public static void main(String[] args) {

        //This is the web page we will use
        String webPageToSearch ="https://www.google.com/";

        ProblemTwo problemTwo = new ProblemTwo(webPageToSearch);

        problemTwo.printElementToConsole("a", "href");
        //Lastly we close the driver
        if(problemTwo.driver!=null) {
            problemTwo.driver.quit();
        }
    }

    public ProblemTwo() {
        //We use this to get the path to our chrome drivers
        WebDriverManager.getInstance(CHROME).setup();

        //Our chrome driver instance
        driver = new ChromeDriver();
    }

    public ProblemTwo(String webPageToSearch) {
        //This is the web page we will use
        this.webPageToSearch = webPageToSearch;

        //We use this to get the path to our chrome drivers
        WebDriverManager.getInstance(CHROME).setup();

        //Our chrome driver instance
        driver = new ChromeDriver();

        //We use our chrome driver instance to get the web page
        driver.get(webPageToSearch);

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getWebPageToSearch() {
        return webPageToSearch;
    }

    public void setWebPageToSearch(String webPageToSearch) {
        this.webPageToSearch = webPageToSearch;
    }


    //Find all relevant elements from page, in this case all a elements and add them to a list
    public List<WebElement> findElementsByTagName(String tagName) {
        return driver.findElements(By.tagName("a"));
    }

    public void printElementToConsole(String tagName, String attribute) {
        System.out.println("\nPrinting elements from: " + webPageToSearch + "\n");
        //For each a element we first map by chosen attribute and then print each element
        List<WebElement> list = findElementsByTagName(tagName);
        list.stream().map(a -> a.getAttribute(attribute)).forEach(System.out::println);
    }


}
