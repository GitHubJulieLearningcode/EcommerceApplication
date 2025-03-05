package com.mystore.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public  static Properties prop;
    public static WebDriver driver;
    @BeforeTest
    public  void loadConfig()
    {
        try
        {
            prop=new Properties();
           System.out.println("super Constructor Invoked");
            FileInputStream ip =new FileInputStream(System.getProperty("user.dir")+"Configuration/config.properties");
            prop.load(ip);
           System.out.println("driver: "+driver);

        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.iedriver().setup();
        WebDriverManager.edgedriver().setup();  // Edge Driver setup

        String BrowserName = prop.getProperty("browser");

        if (BrowserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (BrowserName.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (BrowserName.equalsIgnoreCase("IE")) {
            driver = new InternetExplorerDriver();
        } else if (BrowserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name: " + BrowserName);
        }
    }



}
