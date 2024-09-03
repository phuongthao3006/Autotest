package testcase;

import model.Person;
import org.junit.Assert;
import org.junit.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MenuPage;
import pages.TablePage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTest extends BasePage {
    @Test
    public void checkDataTable(){
        LoginPage login = new LoginPage(driver) ;
        MenuPage menuPage = new MenuPage(driver);
        TablePage tablePage = new TablePage(driver);
        String username = "test@gmail.com";
        String password = "password";
        login.login(username, password);
        menuPage.openTablePage();
        tablePage.tapOnShowEntries(25);
        Assert.assertEquals( 25,tablePage.getListPersonInTable().size());
    }

    @Test
    public  void checkSortTable() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        TablePage tablePage = new TablePage(driver);
        String username = "test@gmail.com";
        String password = "password";
        login.login(username, password);
        menuPage.openTablePage();
        tablePage.clickSort(true);
        Thread.sleep(3000);
        List<Person> actualListPerson = tablePage.getListPersonInTable();
        List<String> actualName = actualListPerson.stream().map(p -> p.getName()).toList();
        List<String> expectedList = actualListPerson.stream().map(p -> p.getName()).sorted().toList();
        assertThat(actualName).hasSameSizeAs(expectedList);
        for(int i = 0 ; i < actualName.size(); i++){
            assertThat(actualName.get(i)).isEqualTo(expectedList.get(i));
        }
    }

    @Test
    public void searchTable(){
        LoginPage login = new LoginPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        TablePage tablePage = new TablePage(driver);
        String username = "test@gmail.com";
        String password = "password";
        login.login(username, password);
        menuPage.openTablePage();
        tablePage.search("java");
        List<Person> actualListPerson = tablePage.getListPersonInTable();
        boolean actual;
        for (int i = 0; i < actualListPerson.size(); i++) {
            String name = actualListPerson.get(i).getName().toLowerCase();
            String position = actualListPerson.get(i).getPosition().toLowerCase();
            String office = actualListPerson.get(i).getOffice().toLowerCase();
            String age = String.valueOf(actualListPerson.get(i).getAge());
            String startDate = String.valueOf(actualListPerson.get(i).getStartDate()).toLowerCase();
            String salary = actualListPerson.get(i).getSalary().toLowerCase();
            if(name.contains("java")||position.contains("java")|| office.contains("java")|| age.contains("java")
            || startDate.contains("java")|| salary.contains("java")){
                actual = true;
            }else {
                actual = false;
            }
            Assert.assertEquals(true, actual);
        }
    }

}
