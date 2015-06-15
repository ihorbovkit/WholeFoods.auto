package com.applause.auto.pageframework.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Screen {

	protected WebDriver appiumDriver;
	
	public Screen(WebDriver webDriver) {
		this.appiumDriver = webDriver;
	}

	public WebDriver getWebDriver() {
		return appiumDriver;
	}
	
	public void waitForElementPresent(WebElement webElement){
		try{
			WebDriverWait wait = new WebDriverWait(appiumDriver, 20);
			wait.until(ExpectedConditions.visibilityOf(webElement));
		}catch (Exception n){
			n.printStackTrace();
		}
	}
	public void waitForElementsPresent(List<WebElement> webElement){
		try {
			WebDriverWait wait = new WebDriverWait(appiumDriver, 20);
			wait.until(ExpectedConditions.visibilityOfAllElements(webElement));
		}catch(Exception n){
			n.printStackTrace();
		}
	}
	public void WaitForTextPresent(WebElement webelement, String text) throws InterruptedException
    {
        int waitRetryDelayMs = 100;
        int timeOut = 500; 
        boolean first = true; 

        for (int milliSecond = 0; ; milliSecond += waitRetryDelayMs)
        {
            if (milliSecond > timeOut * 1000)
            {
                System.out.println("Timeout: Text '" + text + "' is not found ");
                break;
            }

            if (webelement.getText().contains(text))
            {
                if (!first) System.out.println("Text is found: '" + text + "'");
                break;
            }

            if (first) System.out.println("Waiting for text is present: '" + text + "'");

            first = false;
            Thread.sleep(waitRetryDelayMs);
        }
    }
	
}
