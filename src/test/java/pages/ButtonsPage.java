package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage extends BasePage{
    public ButtonsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
