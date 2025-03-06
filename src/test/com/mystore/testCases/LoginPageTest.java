package com.mystore.testCases;

import com.mystore.base.BaseClass;
import com.mystore.pageObjects.HomePage;
import com.mystore.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {
    private WebDriver driver;
    private LoginPage login;
    private HomePage home;

    @BeforeMethod
    public void setup() {
        // Load configuration
        loadConfig();  // Ensure `prop` is loaded before using it
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Initialize page objects after driver setup
        login = new LoginPage(driver);
        home = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(testName = "01:Login using standard User")
    public void LoginusingstandardUser() throws Throwable {
        login.Login(prop.getProperty("username"), prop.getProperty("password"));
        String ActualUrl = home.getHomePageUrl();
        String ExpectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(ActualUrl, ExpectedUrl);
    }
}
