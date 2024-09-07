package org.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener {
   ExtentReports extent=ExtentManager.getInstance();
   ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("*** Creating Test For Report ***");
     extentTest=extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
     extentTest.pass("Test is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    extentTest.fail("Test is failed"+result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    extentTest.skip("Test is Skipped" +result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
    extent.flush();
        System.out.println("*** Generating Report ***");
    }
}
