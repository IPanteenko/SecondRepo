package Pages;

import Utilities.CommonDriver;
import Utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends CommonDriver {

    public void GoToTMPage(WebDriver driver)
    {

        // navigate to home page and check if user has logged in Successfully
        Wait.waitIsVisible(driver, "xpath", "//*[@id='logoutForm']/ul/li/a", 15);

//        Assert.isTrue(helloHari.getText() == "Hello hari!", "login failed, Test failed");

        // Click on Administration tab
        WebElement administrationTab = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/a"));
        administrationTab.click();

        // Select Time and Material from the dropdown list
        WebElement tmOption = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/ul/li[3]/a"));
        tmOption.click();
    }

    public void GoToEmployeePage(WebDriver driver)
    {
        WebElement administrationDropdown = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/a"));
        administrationDropdown.click();

        // Choose Employees from drop down menu
        WebElement employeesDropdown = driver.findElement(By.xpath("/html/body/div[3]/div/div/ul/li[5]/ul/li[2]/a"));
        employeesDropdown.click();
    }
}
