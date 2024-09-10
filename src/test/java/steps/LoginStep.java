package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginStep {
    private WebDriver driver;

    @Given("user navigates to page")
    public void user_navigates_to_page() {
        BasePage page = new BasePage();
        driver = page.setup();
    }

    @When("I enter Username as {string} and Password as {string}")
    public void i_enter_Username_as_and_Password_as(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Then("login successful")
    public void login_successful() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertEquals(true,dashboardPage.isDisplayPage());
    }
}
