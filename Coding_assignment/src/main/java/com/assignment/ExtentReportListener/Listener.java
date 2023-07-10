package com.assignment.ExtentReportListener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.assignment.utility.BaseTest_Listener;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listener extends ExtentManager implements ITestListener{
	
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}
	
	
	public void onTestSuccess(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS) {
 			
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"Test Case Passed",ExtentColor.GREEN));
			
 		}
 		
	}
	
	
	public void onTestFailure(ITestResult result) {		
		if(result.getStatus() == ITestResult.FAILURE) {
 			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + ""+"Test Case FAILED due to below issues:", ExtentColor.RED));
 			
 			test.fail(result.getThrowable());
 			String pathString = BaseTest_Listener.screenShot(BaseTest_Listener.driver, result.getName());
 		   try {
 		    test.addScreenCaptureFromPath(pathString);
 		   } catch (IOException e) {
 		   
 		    e.printStackTrace();
 		   }
 			
 		}
	}
	
	
	public void onTestSkipped(ITestResult result) {
		if(result.getStatus() == ITestResult.SKIP) {
 			
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"Test case Skkiped",ExtentColor.ORANGE));
 		}
	}
	
	
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

}
