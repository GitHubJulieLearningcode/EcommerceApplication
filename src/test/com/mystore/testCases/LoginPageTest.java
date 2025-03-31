package com.mystore.testCases;

import com.mystore.Utility.log;
import com.mystore.actionDriver.Action;
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
        BaseClass.launchBrowser();
        loadConfig();  // Ensure `prop` is loaded before using it
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        //getDriver() = new ChromeDriver();
        getDriver().manage().window().maximize();
        getDriver().get("https://www.saucedemo.com/");
        login = new LoginPage();
        home = new HomePage();

        // Initialize page objects after driver setup


    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @Test(testName = "01:Login using standard User")
    public void LoginusingstandardUser() throws Throwable {
        log.startTestCase("01:Login using standard User");

        log.info("Entering Username and Password");
        login.Login(prop.getProperty("username"), prop.getProperty("password"));

        String ActualUrl = home.getHomePageUrl();
        String ExpectedUrl = "https://www.saucedemo.com/inventory.html";

        log.info("Verifying login success: Checking if redirected to inventory page");
        if (!ActualUrl.equals(ExpectedUrl)) {
            log.error("Login failed! Expected URL: " + ExpectedUrl + ", but got: " + ActualUrl);
        }

        Assert.assertEquals(ActualUrl, ExpectedUrl, "Login failed: User is not redirected to the inventory page");

        log.endTestCase("01:Login using standard User");

    }
    @Test(testName = "Login Using locked_out_user")
    public void LoginLockedOutUser() throws Throwable
    {
        login.Login("locked_out_user",prop.getProperty("password"));
        String ActualError=login.getAlert();
        String ExpectedError="Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(ActualError,ExpectedError);
    }
    @Test(testName = "Login using problem_user")
    public void LoginWithProblemUser() throws Throwable
    {
        login.Login("problem_user",prop.getProperty("password"));
        String ActualUrl = home.getHomePageUrl();
        String ExpectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(ActualUrl, ExpectedUrl);
        String ActulImg=home.getImageSrc();
        Assert.assertTrue(ActulImg.contains("sl-404.168b1cce.jpg"));

    }
    @Test(testName = "Login using performance_glitch_user")
    public void LoginwithPerformanceGlitchuser() throws  Throwable
    {
        login.Login("performance_glitch_user",prop.getProperty("password"));
        String ActualUrl = home.getHomePageUrl();
        String ExpectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(ActualUrl, ExpectedUrl);

    }
    @Test(testName = "Login using error_user")
    public void LoginwitherrorUser() throws Throwable
    {

        login.Login("error_user",prop.getProperty("password"));
        String ActualUrl = home.getHomePageUrl();
        String ExpectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(ActualUrl, ExpectedUrl);
    }
    @Test(testName = "Login using visual_user")
    public void LoginWithVisualUser() throws Throwable
    {
        login.Login("visual_user",prop.getProperty("password"));
        String ActualUrl = home.getHomePageUrl();
        String ExpectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(ActualUrl, ExpectedUrl);

    }

}
