package com.assignment.ExtentReportListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlreport;
	public static ExtentTest test;
	public static ExtentReports extent1;
	public static ExtentHtmlReporter htmlreport1;

	public static void setExtent() {

		htmlreport = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "//test-output//ExtentReports//Report.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("OS", "WINDOWS");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Banu");
		htmlreport.config().setChartVisibilityOnOpen(true);
		htmlreport.config().setDocumentTitle("CodingAssignment.Report");
		htmlreport.config().setReportName("Report");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreport.config().setTheme(Theme.DARK);

	}

	public static void endReport() throws InterruptedException {
		extent.flush();
		Thread.sleep(10000);
	}

}
