package Tests;


import Pages.EmployeesPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.CommonDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Employees_tests extends CommonDriver {
        LoginPage loginPageObj = new LoginPage();
        HomePage homePageObj = new HomePage();
        EmployeesPage employeesPageObj = new EmployeesPage();

        @BeforeTest
        public void SetUpEmployee()
        {
            driver = new ChromeDriver();
            loginPageObj.LoginActions(driver);
            homePageObj.GoToEmployeePage(driver);
        }

       @Test (priority = 0, description = "This test checks if a user is able to create a new employee record")
        public void CreateEmployee_Test() {
            employeesPageObj.CreateNewEmployee(driver, "Jack", "Jack2023");
            employeesPageObj.CreateEmployeeAssertion(driver, "Jack", "Jack2023");
        }

        @Test (priority = 1, description = "This test checks if a user is able to edit an existing employee record")
        public void EditEmployee_Test()  {
            employeesPageObj.EditEmployee(driver, "editedJack", "editedJack2023");
            employeesPageObj.EditEmployeeAssertion(driver, "editedJack", "editedJack2023");
        }

        @Test (priority = 2, description = "This test checks if a user is able to delete an existing employee record")
        public void DeleteEmployee_Test() throws InterruptedException {
            employeesPageObj.DeleteEmployee(driver, "editedJack");
        }

        @AfterTest
        public void TearDown()
        {
            driver.quit();
        }
    }



