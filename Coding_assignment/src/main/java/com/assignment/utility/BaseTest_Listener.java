package com.assignment.utility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.annotations.BeforeSuite;

import com.assignment.ExtentReportListener.ExtentManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest_Listener {

	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	private String apkPath = "\\APK_Json\\app-debug.apk";

	@BeforeSuite(alwaysRun = true)
	public void configureAppium() throws MalformedURLException, InterruptedException {

		Thread.sleep(5000);
		ExtentManager.setExtent();
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\banupriya.j\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(30)).build();
		service.start();
		initiateAndroidDriver("MobileTest", apkPath);

	}

	public void initiateAndroidDriver(String mobileName, String apkPath) throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(mobileName);
		options.setApp(System.getProperty("user.dir") + apkPath);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void afterSuite() throws InterruptedException {

		driver.quit();
		service.stop();
		ExtentManager.endReport();
		Thread.sleep(10000);
		}

	@BeforeClass
	public void reportForEveryBeforeClass() throws InterruptedException {
		Thread.sleep(5000);
		ExtentManager.setExtent();
	}

	@AfterClass
	public void reportForEveryAfterClass() throws InterruptedException {
		ExtentManager.endReport();
		Thread.sleep(10000);

	}

	public static String screenShot(AndroidDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "//ScreenShots//" + filename + "_" + dateName + ".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return destination;
	}

}
