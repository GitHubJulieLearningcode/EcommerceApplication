package com.mystore.base;

import com.mystore.Utility.extentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public static Properties prop;
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();  //Declare thread local for parallel execution


    @BeforeSuite

    public static void loadConfig() throws IOException {
        if (extentManager.getExtent() == null) {
            extentManager.setExtent();
        }
        DOMConfigurator.configure("log4j.xml");

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setDriver(WebDriver driverInstance) {
        driver.set((RemoteWebDriver) driverInstance);
    }
    public static WebDriver getDriver() {
        return driver.get();
    } //get from thread local

    public static void launchBrowser() throws IOException {
        if (prop == null) {
            loadConfig();  // Ensure properties are loaded before using them
        }

        String browserName = prop.getProperty("browser", "Chrome").trim(); // Default to Chrome if not specified

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
    }
    @AfterSuite
    public  void afterSuit()
    {
        //extentManager.endReport();
    }
}
