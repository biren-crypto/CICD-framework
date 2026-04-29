package birenproject.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;

import birenproject.Resources.ExtentReporterNG;

public class Listeners implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);// unique thred id for test case so that parallel execution will not fail 
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	 extentTest.get().log(Status.PASS, "Test pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());//

        WebDriver driver = null;

        // Get driver using reflection
        try {
            driver = (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filepath = null;
        

        try {
            if (driver != null) {
            	 BaseTest base = new BaseTest();
                filepath = base.getScreenShoot(result.getMethod().getMethodName(), driver);
            } else {
                System.out.println("Driver is NULL ❌");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (filepath != null) {
        	 extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
        } else {
            System.out.println("Screenshot not captured ❌");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();//mandate---- for result 
    }
}

//-----------------------Here we conect evry dot and set the listener---------------->

















