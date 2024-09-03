package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage extends BasePage{
    @FindBy(xpath = "//span[text()='Components']/parent::a")
    WebElement componentMenu;
    @FindBy(xpath = "//a[text()='Buttons']")
    WebElement buttonsMenu;
    @FindBy(xpath = "//a[text()='Cards']")
    WebElement cardsMenu;
    @FindBy(xpath = "//span[contains(text(),'Utilities')]/parent::a")
    WebElement utilitiesMenu;
    @FindBy(xpath = "//span[text()='Pages']/parent::a")
    WebElement pageMenu;
    @FindBy(xpath = "//span[text()='Charts']/parent::a")
    WebElement chartsMenu;
    @FindBy(xpath = "//span[text()='Tables']/parent::a")
    WebElement tableMenu;
    @FindBy(xpath = "//h1[contains(text(),'Buttons')]")
    WebElement titleButton;

    public  MenuPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openTablePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.visibilityOf(tableMenu));
        tableMenu.click();
    }

    public void openChartPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.visibilityOf(chartsMenu));
        chartsMenu.click();
    }

    public  void openButtonPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(componentMenu));
        componentMenu.click();
        String classAttribute = componentMenu.getAttribute("class");
        if(classAttribute.equals("nav-link collapsed")){
            componentMenu.click();
        }
        wait.until(ExpectedConditions.visibilityOf(buttonsMenu)).click();
    }
    public boolean isDisplay(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(titleButton));
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    public void openCardPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.visibilityOf(componentMenu)).click();
        String classAttribute = cardsMenu.getAttribute("class");
        if(classAttribute.equals("nav-link collapsed")){
            componentMenu.click();
        }
        wait.until(ExpectedConditions.visibilityOf(cardsMenu)).click();
    }

}
