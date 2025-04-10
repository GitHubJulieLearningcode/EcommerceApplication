package com.mystore.actionDriver;

import com.mystore.base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Action extends BaseClass implements actionInterface {
    private static final Logger logger = Logger.getLogger(Action.class.getName());

    @Override
    public void scrollByVisibleElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element successfully.");
        } catch (Exception e) {
            logger.severe("Error scrolling to element: " + e.getMessage());
        }
    }

    @Override
    public void click(WebDriver driver, WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
            logger.info("Click action performed successfully.");
        } catch (Exception e) {
            logger.severe("Error clicking element: " + e.getMessage());
        }
    }

    @Override
    public boolean findElement(WebDriver driver, WebElement element) {
        Logger logger = Logger.getLogger(getClass().getName());

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element found: " + element);
            return true;
        } catch (Exception e) {
            logger.severe("Element not found: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean isDisplayed(WebDriver driver, WebElement element) {
        return findElement(driver, element) && element.isDisplayed();
    }

    @Override
    public boolean isSelected(WebDriver driver, WebElement element) {
        return findElement(driver, element) && element.isSelected();
    }

    @Override
    public boolean isEnabled(WebDriver driver, WebElement element) {
        return findElement(driver, element) && element.isEnabled();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
     @Override
    public boolean switchToFrameByIndex(WebDriver driver, int index) {
        try {
            driver.switchTo().frame(index);
            Action.logger.info("Switched to frame index: " + index);
            return true;
        } catch (Exception e) {
            Action.logger.severe("Failed to switch to frame index: " + index + " - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean switchToFrameById(WebDriver driver, String idValue) {
        try {
            driver.switchTo().frame(idValue);
            logger.info("Switched to frame index: " + idValue);
            return true;
        } catch (Exception e) {
            logger.severe("Failed to switch to frame index: " + idValue + " - " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean switchToDefaultFrame(WebDriver driver) {
        boolean flag = false;
        try {
            driver.switchTo().defaultContent();
            flag = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                // SuccessReport("SelectFrame ","Frame with Name is selected");
            } else {
                // failureReport("SelectFrame ","The Frame is not selected");
            }
        }
    }

    @Override
    public void mouseOverElement(WebDriver driver, WebElement element) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                System.out.println("MouseOver Action is performed on");
            } else {
                System.out.println("MouseOver action is not performed on");
            }
        }
    }

    @Override
    public boolean moveToElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
            new Actions(driver).moveToElement(ele).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean mouseover(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(ele).build().perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean draggable(WebDriver driver, WebElement source, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(source, x, y).build().perform();
            Thread.sleep(5000);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Draggable Action is performed on " + source);
            } else {
                System.out.println("Draggable action is not performed on " + source);
            }
        }
    }

    @Override
    public boolean draganddrop(WebDriver driver, WebElement source, WebElement target) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDrop(source, target).perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean slider(WebDriver driver, WebElement ele, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(ele, x, y).build().perform();
            Thread.sleep(5000);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean rightclick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).contextClick(ele).perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean switchWindowByTitle(WebDriver driver, String windowTitle, int count) {
        boolean flag = false;
        try {
            Set<String> windowList = driver.getWindowHandles();
            String[] array = windowList.toArray(new String[0]);
            driver.switchTo().window(array[count - 1]);
            flag = driver.getTitle().contains(windowTitle);
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean switchToNewWindow(WebDriver driver) {
        boolean flag = false;
        try {
            Set<String> s = driver.getWindowHandles();
            Object popup[] = s.toArray();
            driver.switchTo().window(popup[1].toString());
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean switchToOldWindow(WebDriver driver) {
        boolean flag = false;
        try {
            Set<String> s = driver.getWindowHandles();
            Object popup[] = s.toArray();
            driver.switchTo().window(popup[0].toString());
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    @Override
    public int getColumncount(WebElement row) {
        return row.findElements(By.tagName("td")).size();
    }

    @Override
    public int getRowCount(WebElement table) {
        return table.findElements(By.tagName("tr")).size() - 1;
    }

    @Override
    public boolean Alert(WebDriver driver) {
        boolean presentFlag = false;
        try {
            driver.switchTo().alert().accept();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
        }
        return presentFlag;
    }

    @Override
    public boolean launchUrl(WebDriver driver, String url) {
        boolean flag = false;
        try {
            driver.navigate().to(url);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    @Override
    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    @Override
    public String getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    @Override
    public boolean click1(WebElement locator, String locatorName) {
        boolean flag = false;
        try {
            locator.click();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    @Override
    public void implicitWait(WebDriver driver, int timeOut) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Override
    public void explicitWait(WebDriver driver, WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void pageLoadTimeOut(WebDriver driver, int timeOut) {
        driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
    }

    @Override
    public String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
    }


    @Override
    public String screenShot(WebDriver driver, String filename) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        // This new path for jenkins
        String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
                + dateName + ".png";
        return newImageString;
    }


    }

