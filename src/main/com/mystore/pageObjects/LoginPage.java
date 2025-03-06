package com.mystore.pageObjects;

import com.mystore.actionDriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseClass {
    Action action = new Action();
    WebDriver driver;

    // Web Elements using @FindBy annotation
    @FindBy(xpath = "//input[@id='user-name']")
    WebElement UserName;

    @FindBy(xpath = "//input[@id='password']")
    WebElement Password;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement signBtn;

    @FindBy(xpath = "//div[@class='login_logo']")
    WebElement logo;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;  // Ensure driver is retrieved properly
        PageFactory.initElements(driver, this);
    }

    // Login Method
    public void Login(String uname, String pwd) throws Throwable
    {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(UserName));
//
//        action.scrollByVisibleElement(driver, UserName);
        action.type(UserName, uname);
        action.type(Password, pwd);
        action.JSClick(driver, signBtn);

        // Wait for next page to load instead of Thread.sleep
        //wait.until(ExpectedConditions.urlContains("inventory.html"));
    }
}
