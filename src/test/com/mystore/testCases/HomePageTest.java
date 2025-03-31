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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mystore.base.BaseClass.loadConfig;

public class HomePageTest extends BaseClass {

    private WebDriver driver;
    private LoginPage login;
    private HomePage home;

    @BeforeMethod
    public void setup() throws Throwable {
        // Load configuration
        BaseClass.launchBrowser();
        loadConfig();  // Ensure `prop` is loaded before using it
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        //getDriver() = new ChromeDriver();
        getDriver().manage().window().maximize();
        getDriver().get("https://www.saucedemo.com/");
        login = new LoginPage();
        home = new HomePage();
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
    { home.applyfilter("Name (A to Z)");
      List Actuallist= home.nameList();
      List ExpectedList=new ArrayList<>(Actuallist);
      System.out.println(Actuallist);
      Collections.sort(ExpectedList);
      System.out.println(ExpectedList);
        Assert.assertEquals(Actuallist,ExpectedList);
    }
    @Test(testName ="Filter items by Name(Z-A)")
    public void filterbyNameZ_A() throws Throwable
    {
        home.applyfilter("Name (Z to A)");
        List Actuallist= home.nameList();
        List ExpectedList=new ArrayList<>(Actuallist);
        System.out.println(Actuallist);
        Collections.sort(ExpectedList,Collections.reverseOrder());
        System.out.println(ExpectedList);
        Assert.assertEquals(Actuallist,ExpectedList);
    }
    @Test(testName = "Filter items by Price(Low to high")
    public void filterbyPricelow_high()
    {
        home.applyfilter("Price (low to high)");
        List Actuallist= home.priceList();
        List ExpectedList=new ArrayList<>(Actuallist);
        System.out.println(Actuallist);
        Collections.sort(ExpectedList);
        System.out.println(ExpectedList);
        Assert.assertEquals(Actuallist,ExpectedList);

    }
    @Test(testName = "Filter items by Price(high to Low")
    public void filterPricehigh_low()
    {
        home.applyfilter("Price (high to low)");
        List Actuallist= home.priceList();
        List ExpectedList=new ArrayList<>(Actuallist);
        System.out.println(Actuallist);
        Collections.sort(ExpectedList,Collections.reverseOrder());
        System.out.println(ExpectedList);
        Assert.assertEquals(Actuallist,ExpectedList);
    }
}
