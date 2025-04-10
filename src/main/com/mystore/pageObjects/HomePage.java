package com.mystore.pageObjects;

import com.mystore.actionDriver.Action;
import com.mystore.base.BaseClass;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class HomePage extends BaseClass {
    Action action=new Action();

    public HomePage() {
        // Ensure driver is retrieved properly
        if (getDriver() == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call launchBrowser() before using page objects.");
        }
        PageFactory.initElements(getDriver(), this);
    }
    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement HomePageLogo;

    @FindBy(xpath = "//img[@alt='Sauce Labs Backpack']")
    private WebElement ImgElement;

    @FindBy(xpath ="//select[@class=\"product_sort_container\"]")
    private WebElement dropdown;





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
    public List nameList()
    {
        List<WebElement> elements = getDriver().findElements(By.xpath("//div[@class='inventory_item']"));

        // Print the number of elements found
        int l=elements.size();

        ArrayList<String> list=new ArrayList<>();
        for(int i=1;i<=l;i++)
        {
            WebElement element=getDriver().findElement(By.xpath("\n" +
                    "/html/body/div/div/div/div[2]/div/div/div/div["+i+"]/div[2]/div[1]/a/div"));
            list.add(element.getText());

        }
        return list;

    }
    public List priceList()
    {
        List<WebElement> elements = getDriver().findElements(By.xpath("//div[@class='inventory_item']"));

        int l=elements.size();
        ArrayList<String> list=new ArrayList<>();
        for(int i=1;i<=l;i++)
        {
            WebElement element= getDriver().findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[" + i + "]/div[2]/div[2]/div"));
            list.add(element.getText());

        }
        List<Double> prices = new ArrayList<>();
        for (String price : list) {
            prices.add(Double.parseDouble(price.replace("$", ""))); // Remove $ and convert to double
        }
        return prices;
    }


}
