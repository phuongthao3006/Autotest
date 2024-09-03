package testcase;

import org.junit.Assert;
import org.junit.Test;
import pages.BasePage;
import pages.DashboardPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.Random;

public class LoginTest extends BasePage {
    @Test
    public void login(){
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage= new DashboardPage(driver);
        String username = "test" + new Random().nextInt(10) +"@gmail.com";
        String password = "password";
        loginPage.login(username, password);
        boolean isDisplay = dashboardPage.isDisplayPage();
        Assert.assertEquals(true, isDisplay);
    }

    @Test
    public  void loginFail(){
        LoginPage loginPage = new LoginPage(driver);
        String username = "test";
        String password = "password";
        loginPage.login(username, password);
        String mess = loginPage.getValidateTextOfUsernameField();
        String expect = String.format("Please include an '@' in the email address. '%s' is missing an '@'.", username);
        Assert.assertEquals(expect, mess);
    }
}
