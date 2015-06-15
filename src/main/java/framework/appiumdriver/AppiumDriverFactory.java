package framework.appiumdriver;

import framework.utility.XmlPropertyLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * A factory that returns a singleton of mobile driver object.
 * 
 * @author Ihor.Bovkit
 * 
 */
public class AppiumDriverFactory {

	private static AppiumDriver appiumDriver;

	private AppiumDriverFactory() {

	}

	/**
	 * get Driver method
	 * 
	 * @param mobilePlatform
	 *            mobile platform object
	 * @return driver for mobile execution
	 * @throws Exception
	 *             if invalid mobile property platform is set
	 */
	public static AppiumDriver getAppiumDriver(String osType, String appType, String mobileDeviceName) throws Exception {

		DesiredCapabilities desiredCaps = new DesiredCapabilities();

		if (osType.equals("android")) {
			desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
			desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, mobileDeviceName);

			if (appType.equals("native")) {
				desiredCaps.setCapability(MobileCapabilityType.APP, XmlPropertyLoader.loadProperty("application.path"));
				appiumDriver = new AndroidDriver(new URL(XmlPropertyLoader.loadProperty("appium.server.url")), desiredCaps);

			} else if (appType.equals("hybrid")) {
				desiredCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "selendroid");
				desiredCaps.setCapability(MobileCapabilityType.APP_PACKAGE, XmlPropertyLoader.loadProperty("application.package"));
				desiredCaps.setCapability(MobileCapabilityType.APP_ACTIVITY, XmlPropertyLoader.loadProperty("application.activity"));
				desiredCaps.setCapability(MobileCapabilityType.APP_WAIT_PACKAGE, XmlPropertyLoader.loadProperty("application.package"));
				desiredCaps.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY, XmlPropertyLoader.loadProperty("application.activity"));

				appiumDriver = new AndroidDriver(new URL(XmlPropertyLoader.loadProperty("appium.server.url")), desiredCaps);
				switchtoWebView(appiumDriver, Integer.valueOf(XmlPropertyLoader.loadProperty("webView.appearance.timeout")));

			} else
				throw new RuntimeException("Invalid type of Android application set - should be Hybrid or Native");

		} else if (osType.equals("ios")) {
			desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
			if (appType.equals("native")) {
				//TODO
			} else if (appType.equals("hybrid")) {
				//TODO
			} else
				throw new RuntimeException("Invalid type of IOS application set - should be Hybrid or Native");
		} else
			throw new RuntimeException("Invalid mobile OS property set in configuration file. Should be IOS or Android.");

		return appiumDriver;
	}

	/**
	 * Kill mobile driver instance.
	 */
	public static void killDriverInstance() {
		appiumDriver.quit();
		appiumDriver = null;
	}

	/**
	 * Switch to mobile web view
	 * 
	 * @param driver
	 *            mobile driver
	 * @param webViewAppearanceTimeout
	 *            timeout - maximum time for webview appearance waiting
	 * @throws InterruptedException
	 */
	private static void switchtoWebView(final AppiumDriver driver,
			int webViewAppearanceTimeout) throws InterruptedException {
		Thread.sleep(webViewAppearanceTimeout * 1000);
		for (String context : driver.getContextHandles()) {
			if (context.toLowerCase().contains("web")) {
				driver.context(context);
				break;
			}
		}
	}
}