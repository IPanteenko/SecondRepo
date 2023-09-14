package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

    public class LoginPage
    {
        public void LoginActions(WebDriver driver)
        {
            driver.manage().window().maximize();

            // Launch Turnup portal and navigate to Login page
            driver.navigate().to("http://horse.industryconnect.io/");

            // Identify username textbox and enter valid user name
            WebElement usernameTextbox = driver.findElement(By.id("UserName"));
            usernameTextbox.sendKeys("hari");

            // Identify password texbox and enter valid password
            WebElement passwordTextbox = driver.findElement(By.id("Password"));
            passwordTextbox.sendKeys("123123");

            // Identify Login button and click on the button
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[3]/input[1]"));
            loginButton.click();
        }
    }

