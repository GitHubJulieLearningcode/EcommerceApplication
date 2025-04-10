package com.mystore.Utility;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mystore.actionDriver.Action;
import com.mystore.base.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {

    Action action = new Action();

    public void onTestStart(ITestResult result) {
       String testName = result.getTestClass().getName() + " :: " + result.getMethod().getMethodName();
        ExtentTest test = extentManager.getExtent().createTest(testName);
        extentManager.setTest(test);
    }


    public void onTestSuccess(ITestResult result) {
        extentManager.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        extentManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Failed", ExtentColor.RED));
        extentManager.getTest().log(Status.FAIL, result.getThrowable());

        String imgPath = action.screenShot(BaseClass.getDriver(), result.getMethod().getMethodName());
        try {
            extentManager.getTest().fail("Screenshot attached:",
                    MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
        } catch (Exception e) {
            extentManager.getTest().fail("Screenshot couldn't be attached due to error: " + e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        extentManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    public void onStart(ITestContext context) {}

    public void onFinish(ITestContext context) {
        extentManager.endReport();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
