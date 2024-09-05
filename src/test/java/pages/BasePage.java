package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyUtil;

import java.time.Duration;
import java.util.Properties;

public class BasePage {
    public WebDriver driver;

    @Before
    public WebDriver setup(){
        Properties properties = PropertyUtil.propertyLoader("src/test/resources/config/config.properties");
        String driveName = properties.getProperty("driver");
        switch (driveName){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "FireFox":
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(properties.getProperty("base.url"));
        return driver;
     }

     @After
    public void closeDriver(){
        driver.close();
    }
}
