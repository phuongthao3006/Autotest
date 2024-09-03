package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends  BasePage{
    @FindBy(xpath = "//input[@id='exampleInputEmail']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@id='exampleInputPassword']")
    WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement submitButton;

    @FindBy(xpath = "//*[@id='customCheck']")
    WebElement customCheck;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String username , String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public String getValidateTextOfUsernameField(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        return usernameField.getAttribute("validationMessage");
    }

}
