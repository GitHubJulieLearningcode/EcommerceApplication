package com.mystore.testCases;

import com.mystore.base.BaseClass;
import com.mystore.pageObjects.HomePage;
import com.mystore.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.mystore.base.BaseClass.loadConfig;

public class HomePageTest extends BaseClass {

    private WebDriver driver;
    private LoginPage login;
    private HomePage home;

    @BeforeMethod
    public void setup() throws Throwable {
        // Load configuration
        loadConfig();  // Ensure `prop` is loaded before using it
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        login = new LoginPage(driver);
        home = new HomePage(driver);
        login.Login(prop.getProperty("username"), prop.getProperty("password"));
        // Initialize page objects after driver setup

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(testName ="Filter items by Name(A-z)")
    public void filterbyNameA_Z() throws Throwable
    {
       //validate filter
        home.applyfilter("Name (A to Z)");

    }

}
