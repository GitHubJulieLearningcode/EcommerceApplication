package com.mystore.actionDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface actionInterface {


    void scrollByVisibleElement(WebDriver driver, WebElement element);

    void click(WebDriver driver, WebElement element);

    boolean findElement(WebDriver driver, WebElement element);

    boolean isDisplayed(WebDriver driver, WebElement element);

    boolean isSelected(WebDriver driver, WebElement element);

    boolean isEnabled(WebDriver driver, WebElement element);

    boolean type(WebElement element, String text);

    boolean selectBySendKeys(String value, WebElement element);

    boolean selectByIndex(WebElement element, int index);

    boolean selectByValue(WebElement element, String value);

    boolean selectByVisibleText(WebElement element, String visibleText);

    boolean mouseHoverByJavaScript(WebDriver driver, WebElement element);

    boolean JSClick(WebDriver driver, WebElement element);

    boolean switchToFrameByIndex(WebDriver driver, int index);
    boolean switchToFrameById(WebDriver driver, String idValue);

    boolean switchToDefaultFrame(WebDriver driver);

    void mouseOverElement(WebDriver driver, WebElement element);

    boolean moveToElement(WebDriver driver, WebElement ele);

    boolean mouseover(WebDriver driver, WebElement ele);

    boolean draggable(WebDriver driver, WebElement source, int x, int y);

    boolean draganddrop(WebDriver driver, WebElement source, WebElement target);

    boolean slider(WebDriver driver, WebElement ele, int x, int y);

    boolean rightclick(WebDriver driver, WebElement ele);

    boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count);

    boolean switchToNewWindow(WebDriver driver);

    boolean switchToOldWindow(WebDriver driver);

    int getColumncount(WebElement row);

    int getRowCount(WebElement table);

    boolean Alert(WebDriver driver);

    boolean launchUrl(WebDriver driver, String url);

    boolean isAlertPresent(WebDriver driver);

    String getTitle(WebDriver driver);

    String getCurrentURL(WebDriver driver);

    boolean click1(WebElement locator, String locatorName);

    void fluentWait(WebDriver driver, WebElement element, int timeOut);

    void implicitWait(WebDriver driver, int timeOut);

    void explicitWait(WebDriver driver, WebElement element, int timeOut);

    void pageLoadTimeOut(WebDriver driver, int timeOut);

    String getCurrentTime();
}
