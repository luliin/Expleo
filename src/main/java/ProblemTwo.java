import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Julia Wigenstedt
 * Date: 2021-04-09
 * Time: 22:12
 * Project: Expleo
 * Copyright: MIT
 */
public class ProblemTwo {



    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        //Convenient
        driver.get("https://selenium.dev");
        System.out.println(driver.getCurrentUrl());
    }



}
