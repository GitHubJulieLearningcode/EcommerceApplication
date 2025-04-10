package com.mystore.pageObjects;

import com.mystore.actionDriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseClass {
    Action action = new Action();

    public LoginPage() {
        // Ensure driver is retrieved properly
        if (getDriver() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call launchBrowser() before using page objects.");
        }

        PageFactory.initElements(getDriver(), this);
    }

    // Web Elements using @FindBy annotation
    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement UserName;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement Password;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement signBtn;

    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement logo;

    @FindBy(xpath="//button[@class=\"error-button\"]")
    private WebElement errorBtn;
    @FindBy(xpath = "//h3[@data-test=\"error\"]")
    private WebElement error;

    // Constructor


    // Login Method
    public void Login(String uname, String pwd) throws Throwable
    {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(UserName));
//
//        action.scrollByVisibleElement(driver, UserName);
        action.type(UserName, uname);
        action.type(Password, pwd);
        action.JSClick(getDriver(), signBtn);

        // Wait for next page to load instead of Thread.sleep
        //wait.until(ExpectedConditions.urlContains("inventory.html"));
    }
    public String getAlert()
    {
        return error.getText();
    }
}
