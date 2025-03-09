package com.mystore.pageObjects;

import com.mystore.actionDriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass {
    Action action=new Action();
    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement HomePageLogo;

    @FindBy(xpath = "//img[@alt='Sauce Labs Backpack']")
    WebElement ImgElement;



    public HomePage(WebDriver driver) {
        this.driver = driver;  // Ensure driver is retrieved properly
        PageFactory.initElements(driver, this);
    }

    public String getHomePageUrl()
    {
        String url=action.getCurrentURL(getDriver());
        return url;
    }
    public String getImageSrc()
    {
        return ImgElement.getDomAttribute("src");
    }


}
