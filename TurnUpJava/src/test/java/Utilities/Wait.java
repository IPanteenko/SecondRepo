
package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.Objects;


public class Wait
    {
        public static void waitToBEClickable(WebDriver driver, String locatorType, String locatorValue, int seconds)
        {
            var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            if (locatorType == "XPath")
            {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
            }
            if (locatorType == "Id")
            {
                wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
            }
            if (locatorType == "Name")
            {
                wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
            }
        }
        public static void waitIsVisible(WebDriver driver, String locatorType, String locatorValue, int seconds) {
            var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            if (Objects.equals(locatorType, "XPath")) {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
            }
            if (Objects.equals(locatorType, "Id")) {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
            }
            if (Objects.equals(locatorType, "Name")) {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
            }
        }
        public static void WaitIsPresence(WebDriver driver, String locatorType, String locatorValue, int seconds) {

            var wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            if (Objects.equals(locatorType, "XPath")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorValue)));
            }
            if (Objects.equals(locatorType, "Id")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(locatorValue)));
            }
            if (Objects.equals(locatorType, "Name")) {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(locatorValue)));
            }

        }

        public static void waitForJStoLoad(WebDriver driver, int seconds) {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

            // wait for jQuery to load
            ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    try {
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        return ((Long)js.executeScript("return jQuery.active") == 0);
                    }
                    catch (Exception e) {
                        return true;
                    }
                }
            };

            // wait for Javascript to load
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    return js.executeScript("return document.readyState")
                            .toString().equals("complete");
                }
            };

            wait.until(jQueryLoad);
            wait.until(jsLoad);
        }

    }

