package com.mystore.pageObjects;

import com.mystore.actionDriver.Action;
import com.mystore.base.BaseClass;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
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

    @FindBy(xpath ="//select[@class=\"product_sort_container\"]")
    WebElement dropdown;



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
    public void applyfilter(String s)
    {
        boolean k= action.selectByVisibleText(dropdown,s);
        if(k)
        {
           System.out.println("selection successful");
        }
        else
        {
            System.out.println("selection unsuccessful");
        }


    }


}
