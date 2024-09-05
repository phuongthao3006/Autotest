package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Person;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.LoginPage;
import pages.MenuPage;
import pages.TablePage;
import utils.PropertyUtil;

import java.util.List;
import java.util.Properties;
import java.util.Random;

public class TableStep {
    WebDriver driver;
    private String searchText;
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
        Assert.assertEquals(true, actualTitle);
    }
    @When("The user searches {string}")
    public void the_user_searches_text(String searchText){
        this.searchText = searchText;
        TablePage tablePage = new TablePage(driver);
        tablePage.search(searchText);
    }

    @Then("The user sees correct result")
    public void the_user_sees_correct_result(){
        TablePage tablePage = new TablePage(driver);
        List<Person> actualListPerson = tablePage.getListPersonInTable();
        Assert.assertEquals( 5,tablePage.getListPersonInTable().size());
        boolean actual;
        for (int i = 0; i < actualListPerson.size(); i++) {
            String name = actualListPerson.get(i).getName().toLowerCase();
            String position = actualListPerson.get(i).getPosition().toLowerCase();
            String office = actualListPerson.get(i).getOffice().toLowerCase();
            String age = String.valueOf(actualListPerson.get(i).getAge());
            String startDate = String.valueOf(actualListPerson.get(i).getStartDate()).toLowerCase();
            String salary = actualListPerson.get(i).getSalary().toLowerCase();
            if(name.contains(searchText)||position.contains(searchText)|| office.contains(searchText)|| age.contains(searchText)
                    || startDate.contains(searchText)|| salary.contains(searchText)){
                actual = true;
            }else {
                actual = false;
            }
            Assert.assertEquals(true, actual);
        }
    }
}
