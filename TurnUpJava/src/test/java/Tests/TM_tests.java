package Tests;


import Pages.HomePage;
import Pages.LoginPage;
import Pages.TMPage;
import Utilities.CommonDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TM_tests extends CommonDriver
    {
        LoginPage loginPageObj = new LoginPage();
        HomePage homePageObj = new HomePage();
        TMPage tMPageObj = new TMPage();
@BeforeTest
        public void SetupTM()
        {
            // Open the Browser
            driver = new ChromeDriver();
            loginPageObj.LoginActions(driver);

            // Home page object initialization and definition
            HomePage homePageObj = new HomePage();
            homePageObj.GoToTMPage(driver);
        }

   @Test (priority = 0, description = "This test checks if a user is able to create a new time record")
        public void CreateTime_Test()
        {
            // Time&material page initialization and definition
            tMPageObj.CreateTimeRecord(driver, "August2023", "August2023", "23");
            tMPageObj.CreateTMAssertion(driver, "August2023", "August2023", "23.00");
        }

        @Test (priority = 1, description = "This test checks if a user is able to edit an existing time record")
        public void EditTime_Test() 
        {
            tMPageObj.EditTimeRecord(driver, "EditedAugust", "EditedAugust2023", "30");
            tMPageObj.EditTMAssertion(driver, "EditedAugust", "EditedAugust2023", "30.00");
        }

        @Test (priority = 2, description = "This test checks if a user is able to delete an existing time record")
        public void DeleteTime_Test() throws InterruptedException {
            // Creating two records to make sure they have different codes for assertion
            tMPageObj.DeleteTimeRecord(driver);
            tMPageObj.DeleteTMAssertion(driver, "EditedAugust");
        }   

     @AfterTest
        public void TearDown()
        {
            driver.quit();
        }
    }

