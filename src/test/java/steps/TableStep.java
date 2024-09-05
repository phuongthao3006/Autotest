package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.LoginPage;
import pages.MenuPage;
import pages.TablePage;
import utils.PropertyUtil;

import java.util.Properties;
import java.util.Random;

public class TableStep {
    WebDriver driver;
    @Given("The user opens the page")
    public void the_user_opens_the_page(){
        BasePage page = new BasePage();
        driver = page.setup();

    }

    @When("The user logins success")
    public void the_user_logins_success(){
        LoginPage loginPage = new LoginPage(driver);
        String username = "test" + new Random().nextInt(10) +"@gmail.com";
        String password = "password";
        loginPage.login(username, password);
    }

    @And("The user clicks on the table menu")
    public void the_user_clicks_on_the_table_menu(){
        MenuPage menuPage = new MenuPage(driver);
        menuPage.openTablePage();
    }
    @Then("The user sees the table page")
    public void the_user_sees_the_table_page(){
        MenuPage menuPage = new MenuPage(driver);
        boolean actualTitle = menuPage.isDisplayTable();

        if (actualTitle) {
            System.out.print("Test Passed!");
        } else {
            System.out.print("Test Failed!");
        }
    }
    @When("The user searches \"java\"")
    public void the_user_searches_text(){
        TablePage tablePage = new TablePage(driver);
        tablePage.search("java");
    }

    @Then("The user sees correct result")
    public void the_user_sees_correct_result(){
        TablePage tablePage = new TablePage(driver);
        Assert.assertEquals( 5,tablePage.getListPersonInTable().size());
    }
}
