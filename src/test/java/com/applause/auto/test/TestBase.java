package com.applause.auto.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import framework.appiumdriver.AppiumDriverFactory;
import framework.utility.XmlPropertyLoader;

/*
 * Base class for all the test classes
 * 
 * @author Ihor Bovkit
 */

public class TestBase {

	protected WebDriver appiumDriver;
	protected String timeout;

	@BeforeMethod
	@Parameters({ "osType", "appType", "mobileDeviceName" })
	public void setup(String osType, String appType, String mobileDeviceName)
			throws Exception {
		timeout = XmlPropertyLoader.loadProperty("implicit-timeout");
		appiumDriver = AppiumDriverFactory.getAppiumDriver(osType, appType,
				mobileDeviceName);
		appiumDriver.manage().timeouts()
				.implicitlyWait(Integer.valueOf(timeout), TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (appiumDriver != null) {
			AppiumDriverFactory.killDriverInstance();
		}
	}

}