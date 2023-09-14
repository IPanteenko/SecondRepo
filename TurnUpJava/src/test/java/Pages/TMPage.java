package Pages;

import Utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class TMPage {

    public void CreateTimeRecord(WebDriver driver, String code, String description, String price) {
        //Identify and click on create new button
        WebElement createNewButton = driver.findElement(By.xpath("//*[@id=\"container\"]/p/a"));
        createNewButton.click();

        // Identify material/time drop down and choose time
        WebElement timeMaterialDropdown = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span/span[2]/span"));
        timeMaterialDropdown.click();
        WebElement timeDropdown = driver.findElement(By.xpath("//*[@id=\"TypeCode_listbox\"]/li[2]"));
        timeDropdown.click();

        // Identify Code textbox and enter code
        WebElement textboxCode = driver.findElement(By.id("Code"));
        textboxCode.sendKeys(code);

        // Identify description textbox and enter decription
        WebElement textboxDescription = driver.findElement(By.id("Description"));
        textboxDescription.sendKeys(description);

        // Identify price textbox and enter price
        WebElement priceTextbox = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
        priceTextbox.sendKeys(price);

        // Identify and click on save button
        WebElement saveButton = driver.findElement(By.id("SaveButton"));
        saveButton.click();

        Wait.waitToBEClickable(driver, "XPath", "//*[@id=\"tmsGrid\"]/div[4]/a[4]", 5);

        // Check if new record was created
        WebElement goToLastPage = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]"));
        goToLastPage.click();
    }

    public void CreateTMAssertion(WebDriver driver, String code, String description, String price) {
        WebElement newRecordCode = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement newDescription = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[3]"));
        WebElement newPrice = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[4]"));

        Assert.assertEquals(newRecordCode.getText(), code, "New code doesn't match created code");
        Assert.assertEquals(newDescription.getText(), description, "New description doesn't match created description");
        Assert.assertEquals(newPrice.getText(), "$" + price, "New price doesn't match created price");
    }

    public void EditTimeRecord(WebDriver driver, String code, String description, String price) {
        WebElement goToLastPage = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]"));
        goToLastPage.click();

        // Identify and click Edit button
        WebElement editButton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[5]/a[1]"));
        editButton.click();

        // Change the code
        WebElement editTextboxCode = driver.findElement(By.id("Code"));
        editTextboxCode.clear();
        editTextboxCode.sendKeys(code);

        // change description
        WebElement editTextboxDescription = driver.findElement(By.id("Description"));
        editTextboxDescription.clear();
        editTextboxDescription.sendKeys(description);

        //Change price
        WebElement overlapPriceTextbox = driver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
        WebElement editPriceTextbox = driver.findElement(By.id("Price"));
        overlapPriceTextbox.click();
        editPriceTextbox.clear();
        overlapPriceTextbox.click();
        editPriceTextbox.sendKeys(price);

        // Save
        WebElement editedsaveButton = driver.findElement(By.id("SaveButton"));
        editedsaveButton.click();

        Wait.waitIsVisible(driver, "XPath", "//*[@id=\"tmsGrid\"]/div[4]/a[4]", 5);

        WebElement goToLastPageAgain = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]"));
        goToLastPageAgain.click();
    }

    public void EditTMAssertion(WebDriver driver, String code, String description, String price) {
        WebElement editedCodeRecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement editedDescRecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[3]"));
        WebElement editedPriceRecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[4]"));

        Assert.assertEquals(editedCodeRecord.getText(), code, "New code doesn't match edited code");
        Assert.assertEquals(editedDescRecord.getText(), description, "New description doesn't match edited description");
        Assert.assertEquals(editedPriceRecord.getText(), "$" + price, "New price doesn't match edited price");
    }


    public void DeleteTimeRecord(WebDriver driver) {
        WebElement goToLastPage = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]"));
        goToLastPage.click();

        Wait.waitIsVisible(driver, "XPath", "//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]", 5);

        //Check the Code of the last element
        WebElement lastRecord = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));

        // Click on Delete
        WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[5]/a[2]"));
        deleteButton.click();
        driver.switchTo().alert().accept();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.stalenessOf(lastRecord));
    }

    public void DeleteTMAssertion(WebDriver driver, String code) throws InterruptedException {

        // Check if material record has been updated
        Wait.waitForJStoLoad(driver, 20);
        WebElement updatedCode = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));
        Assert.assertNotEquals(updatedCode.getText(), code, "The record hasn't been deleted");
    }

}
