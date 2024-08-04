package com.calculator;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "E:\\PORTFOLIO\\QA Testing\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // Remove headless mode for debugging
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:3000/");
    }

    @Test
    public void testAdd() {
        try {
            // Wait for the firstNumber input to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='firstNumber']")));
            
            // Debugging: Print the page source to check if elements are present
            System.out.println("Page source: " + driver.getPageSource());

            driver.findElement(By.cssSelector("input[name='firstNumber']")).sendKeys("2");
            driver.findElement(By.cssSelector("input[name='secondNumber']")).sendKeys("2");
            driver.findElement(By.cssSelector("button[name='addButton']")).click();

            String result = driver.findElement(By.cssSelector("div.result")).getText();
            Assert.assertEquals(result, "4", "The addition result is incorrect.");
        } catch (Exception e) {
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
