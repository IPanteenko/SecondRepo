
package Pages;

import Utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EmployeesPage
    {
        public void CreateNewEmployee(WebDriver driver, String name, String username)
        {
            //Identify and click on create new button
            WebElement createNewButton = driver.findElement(By.xpath("//*[@id=\"container\"]/p/a"));
            createNewButton.click();

            //Identify and enter Name
            WebElement nameTextbox = driver.findElement(By.id("Name"));
            nameTextbox.sendKeys(name);

            //Identify and enter username
            WebElement usernameTextbox = driver.findElement(By.id("Username"));
            usernameTextbox.sendKeys(username);

            //Identify and enter password
            WebElement passwordTextbox = driver.findElement(By.id("Password"));
            passwordTextbox.sendKeys("Qw!12345");

            //Identify and enter RetypePassword
            WebElement retypePasswordTextbox = driver.findElement(By.id("RetypePassword"));
            retypePasswordTextbox.sendKeys("Qw!12345");

            // Identify and click on save button
            WebElement saveButton = driver.findElement(By.id("SaveButton"));
            saveButton.click();

            // Check if new Employee was created 
            WebElement backToListbutton = driver.findElement(By.xpath("//*[@id=\"container\"]/div/a"));
            backToListbutton.click();

        }

        public void CreateEmployeeAssertion(WebDriver driver, String name, String username){

            Wait.waitToBEClickable(driver, "xpath", "//*[@id=\"usersGrid\"]/div[4]/a[4]/span[contains(@class,\"k-icon k-i-seek-e\")]", 20);

            Wait.waitForJStoLoad(driver, 20);

            WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[4]/a[4]/span"));
            goToLastPageButton.click();

            WebElement createdName = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
            WebElement createdUsername = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/table/tbody/tr[last()]/td[2]"));

            Assert.assertEquals(createdName.getText(), name, "New name doesn't match");
            Assert.assertEquals(createdUsername.getText(), username, "New username doesn't match");
        }

        public void EditEmployee(WebDriver driver, String name, String username)  {

            Wait.waitToBEClickable(driver, "xpath", "//*[@id=\"usersGrid\"]/div[4]/a[4]/span", 20);

            WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[4]/a[4]/span"));
            goToLastPageButton.click();

            WebElement editButton = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[3]/table/tbody/tr[last()]/td[3]/a[1]"));
            editButton.click();

            WebElement nameTextbox = driver.findElement(By.id("Name"));
            nameTextbox.clear();
            nameTextbox.sendKeys(name);

            WebElement usernameTextbox = driver.findElement(By.id("Username"));
            usernameTextbox.clear();
            usernameTextbox.sendKeys(username);
            
            WebElement saveButton = driver.findElement(By.id("SaveButton"));
            saveButton.click();

            WebElement backToListbutton = driver.findElement(By.xpath("//*[@id=\"container\"]/div/a"));
            backToListbutton.click();
        }
        public void EditEmployeeAssertion(WebDriver driver, String name, String username) {

            Wait.waitToBEClickable(driver, "xpath", "//*[@id=\"usersGrid\"]/div[4]/a[4]/span", 20);

            Wait.waitForJStoLoad(driver, 20);

            WebElement goToLastPageButtonEdited = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[4]/a[4]/span"));
            goToLastPageButtonEdited.click();

            WebElement editedName = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
            WebElement editedUsername = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/table/tbody/tr[last()]/td[2]"));

            Assert.assertEquals(editedName.getText(), name, "New name doesn't match");
            Assert.assertEquals(editedUsername.getText(), username, "New username doesn't match");
        }

        public void DeleteEmployee(WebDriver driver, String code) throws InterruptedException {
            WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[4]/a[4]/span"));
            goToLastPageButton.click();

            Wait.waitIsVisible(driver, "XPath", "//*[@id=\"usersGrid\"]/div[3]/table/tbody/tr[last()]/td[1]", 5);

            //Check the Code of the last element
            // Click on Delete
            WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[3]/table/tbody/tr[last()]/td[3]/a[2]"));
            deleteButton.click();
            driver.switchTo().alert().accept();

            Wait.waitForJStoLoad(driver, 20);
            WebElement lastRecord = driver.findElement(By.xpath("//*[@id=\"usersGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
            var lastRecordtext = lastRecord.getText();

            Assert.assertNotEquals(lastRecordtext, code, "The record hasn't been deleted");
        }
    }

