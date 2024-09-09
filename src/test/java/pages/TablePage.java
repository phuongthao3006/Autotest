package pages;

import model.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class TablePage extends BasePage{
    @FindBy(xpath = "//select[@name='dataTable_length']")
    WebElement selectDataTableLength;

    @FindBy(xpath = "//input[@type='search']")
    WebElement searchTable;

    @FindBy(xpath = "//th[contains(text(),'Name')]")
    WebElement nameTableHeader;
    public TablePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void tapOnShowEntries(int count){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        selectDataTableLength.click();
        String xpath = String.format("//option[@value='%d']",count);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
    }

    public List<Person> getListPersonInTable(){
        String xpathRow = "//table/tbody/tr";
        List<WebElement> listRow = driver.findElements(By.xpath(xpathRow));
        List<Person> listPerson = new ArrayList<>();
        for (int i = 0; i < listRow.size(); i++) {
            Person person = new Person();
            WebElement row = listRow.get(i);
            WebElement nameCol = row.findElement(By.xpath("./td[1]"));
            person.setName(nameCol.getText());
            WebElement positionCol = row.findElement(By.xpath("./td[2]"));
            person.setPosition(positionCol.getText());
            WebElement officeCol = row.findElement(By.xpath("./td[3]"));
            person.setOffice(officeCol.getText());
            WebElement ageCol = row.findElement(By.xpath("./td[4]"));
            person.setAge(Integer.parseInt(ageCol.getText()));
            WebElement startDateCol = row.findElement(By.xpath("./td[5]"));
            person.setStartDate(new Date(startDateCol.getText()));
            WebElement salaryCol = row.findElement(By.xpath("./td[6]"));
            person.setSalary(salaryCol.getText());
            listPerson.add(person);
        }
        return listPerson;
    }

    public void clickSort(boolean isAscending){
        if (isAscending){
            String ariaSort = nameTableHeader.getAttribute("aria-sort");
            if (ariaSort==null|| ariaSort.equals("")|| ariaSort.equals("descending")){
                nameTableHeader.click();
            }
        }else {
            String ariaSort = nameTableHeader.getAttribute("aria-sort");
            if (ariaSort==null || ariaSort.equals("")|| ariaSort.equals("ascending")){
                nameTableHeader.click();
            }
        }
    }

    public void search(String search){
        searchTable.sendKeys(search);
    }

    public List<Map<String, String>> getTableData() {
        List<Map<String, String>> tableData = new ArrayList<>();
        WebElement table = driver.findElement(By.id("dataTable"));
        List<WebElement> row = table.findElements(By.tagName("tr"));
        List<WebElement> headers = row.get(0).findElements(By.tagName("th"));
        List<String> columnNames = new ArrayList<>();
        for (WebElement header : headers) {
            columnNames.add(header.getText());
        }
        for (int i = 1; i < row.size(); i++) {
            List<WebElement> column = row.get(i).findElements(By.tagName("td"));
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < column.size(); j++) {
                rowData.put(columnNames.get(j), column.get(j).getText());
            }
            tableData.add(rowData);
        }
        return tableData;
    }

}
