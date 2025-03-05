package com.mystore.actionDriver;

import com.mystore.base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

public class Action extends BaseClass {
    private static final Logger logger = Logger.getLogger(Action.class.getName());

    public void scrollByVisibleElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element successfully.");
        } catch (Exception e) {
            logger.severe("Error scrolling to element: " + e.getMessage());
        }
    }

    public void click(WebDriver driver, WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
            logger.info("Click action performed successfully.");
        } catch (Exception e) {
            logger.severe("Error clicking element: " + e.getMessage());
        }
    }

    public boolean findElement(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element found: " + element);
            return true;
        } catch (Exception e) {
            logger.severe("Element not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isDisplayed(WebDriver driver, WebElement element) {
        return findElement(driver, element) && element.isDisplayed();
    }

    public boolean isSelected(WebDriver driver, WebElement element) {
        return findElement(driver, element) && element.isSelected();
    }

    public boolean isEnabled(WebDriver driver, WebElement element) {
        return findElement(driver, element) && element.isEnabled();
    }

    public boolean type(WebElement element, String text) {
        try {
            if (element.isDisplayed()) {
                element.clear();
                element.sendKeys(text);
                logger.info("Text entered successfully.");
                return true;
            }
        } catch (Exception e) {
            logger.severe("Unable to enter text: " + e.getMessage());
        }
        return false;
    }

    public boolean selectBySendKeys(String value, WebElement element) {
        try {
            element.sendKeys(value);
            logger.info("Dropdown selected using sendKeys.");
            return true;
        } catch (Exception e) {
            logger.severe("Dropdown selection failed: " + e.getMessage());
            return false;
        }
    }

    public boolean selectByIndex(WebElement element, int index) {
        try {
            new Select(element).selectByIndex(index);
            logger.info("Option selected by index.");
            return true;
        } catch (Exception e) {
            logger.severe("Option selection failed: " + e.getMessage());
            return false;
        }
    }

    public boolean selectByValue(WebElement element, String value) {
        try {
            new Select(element).selectByValue(value);
            logger.info("Option selected by value.");
            return true;
        } catch (Exception e) {
            logger.severe("Option selection failed: " + e.getMessage());
            return false;
        }
    }

    public boolean selectByVisibleText(WebElement element, String visibleText) {
        try {
            new Select(element).selectByVisibleText(visibleText);
            logger.info("Option selected by visible text.");
            return true;
        } catch (Exception e) {
            logger.severe("Option selection failed: " + e.getMessage());
            return false;
        }
    }

    public boolean mouseHoverByJavaScript(WebDriver driver, WebElement element) {
        try {
            String script = "var evObj = document.createEvent('MouseEvents');"
                    + "evObj.initMouseEvent('mouseover', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                    + "arguments[0].dispatchEvent(evObj);";
            ((JavascriptExecutor) driver).executeScript(script, element);
            logger.info("Mouse hover action performed.");
            return true;
        } catch (Exception e) {
            logger.severe("Mouse hover action failed: " + e.getMessage());
            return false;
        }
    }

    public boolean JSClick(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.info("JS Click action performed.");
            return true;
        } catch (Exception e) {
            logger.severe("JS Click action failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean switchToFrameByIndex(WebDriver driver, int index) {
        try {
            driver.switchTo().frame(index);
            logger.info("Switched to frame index: " + index);
            return true;
        } catch (Exception e) {
            logger.severe("Failed to switch to frame index: " + index + " - " + e.getMessage());
            return false;
        }
    }
}
