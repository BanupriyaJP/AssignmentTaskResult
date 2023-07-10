package com.assignment.Applaunch;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.assignment.utility.BaseTest_Listener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Assignment_Applaunch extends BaseTest_Listener {

	public Assignment_Applaunch(AndroidDriver driver) {
		BaseTest_Listener.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait;
	// ***********************************Application
	// Web-element**********************************

	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.energyaustralia.codingtestsample:id/titleTextView'])")
	private List<WebElement> txtBrands;

	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.energyaustralia.codingtestsample:id/festivalTextView'])")
	private List<WebElement> txtRecordLabel;

	// **************************Application method********************************

	@SuppressWarnings("deprecation")
	public void scrollsBycoordinates(int x, int y, int x1, int y1) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try {
			@SuppressWarnings("rawtypes")
			TouchAction action = new TouchAction((PerformsTouchActions) driver);

			action.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
					.moveTo(PointOption.point(x1, y1)).release().perform();
		} catch (Exception e) {
			System.out.println("TouchAction not performed" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void jsonValueCompare(String name, List<WebElement> ele, int i) throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		//Thread.sleep(3000);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper
					.readTree(new File(System.getProperty("user.dir") + "//APK_Json//file.json"));
			String value = jsonNode.get(name).textValue();

			System.out.println("compared: " + value);
			wait.until(ExpectedConditions.visibilityOf((ele.get(i))));
			Assert.assertEquals(value, ele.get(i).getText());
			System.out.println(ele.get(i).getText() + "String compared from API");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void jsonCompareWithApplicationUp() throws InterruptedException, JsonProcessingException, IOException {

		jsonValueCompare("name1", txtBrands, 0);
		jsonValueCompare("recordLabel1", txtRecordLabel, 0);
		jsonValueCompare("name2", txtBrands, 1);
		jsonValueCompare("recordLabel2", txtRecordLabel, 1);
		jsonValueCompare("name3", txtBrands, 2);
		jsonValueCompare("recordLabel3", txtRecordLabel, 2);
		jsonValueCompare("name4", txtBrands, 3);
		jsonValueCompare("recordLabel4", txtRecordLabel, 3);
		jsonValueCompare("name5", txtBrands, 4);
		jsonValueCompare("recordLabel5", txtRecordLabel, 4);
		jsonValueCompare("name6", txtBrands, 5);
		jsonValueCompare("recordLabel6", txtRecordLabel, 5);
		jsonValueCompare("name7", txtBrands, 6);
		jsonValueCompare("recordLabel7", txtRecordLabel, 6);
		jsonValueCompare("name8", txtBrands, 7);
		jsonValueCompare("recordLabel8", txtRecordLabel, 7);
		jsonValueCompare("name9", txtBrands, 8);
		jsonValueCompare("recordLabel9", txtRecordLabel, 8);
		jsonValueCompare("name10", txtBrands, 9);
		jsonValueCompare("recordLabel10", txtRecordLabel, 9);
		jsonValueCompare("name11", txtBrands, 10);
	}

	public void jsonCompareWithApplicationDown() throws InterruptedException, JsonProcessingException, IOException {
		jsonValueCompare("recordLabel11", txtRecordLabel, 5);
		jsonValueCompare("name12", txtBrands, 6);
		jsonValueCompare("recordLabel12", txtRecordLabel, 6);
		jsonValueCompare("name13", txtBrands, 7);
		jsonValueCompare("recordLabel13", txtRecordLabel, 7);
		jsonValueCompare("name14", txtBrands, 8);
		jsonValueCompare("recordLabel14", txtRecordLabel, 8);
		jsonValueCompare("name15", txtBrands, 9);
		jsonValueCompare("recordLabel15", txtRecordLabel, 9);
		jsonValueCompare("name16", txtBrands, 10);
		jsonValueCompare("recordLabel16", txtRecordLabel, 10);
	}

	public void jsonFileCompareWithApplication() throws InterruptedException, JsonProcessingException, IOException {
		jsonCompareWithApplicationUp();
		scrollsBycoordinates(0, 2107, 0, 212);
		jsonCompareWithApplicationDown();
	}
}