package com.applause.auto.testscenarios;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pages.wholeFoodsPages.HomePage;
import framework.pages.wholeFoodsPages.SearchResultPage;
import com.applause.auto.test.TestBase;

public class BasicSearchTest extends TestBase{
	
	@Test
	public void basicSeachTest() {
		HomePage homePage = PageFactory.initElements(appiumDriver, HomePage.class);
		homePage.search("coffee");
		SearchResultPage search = PageFactory.initElements(appiumDriver, SearchResultPage.class);
		Assert.assertTrue(search.searchMultiplyResultIsDisplayed(), "Multiply Result is displayed");
	}

}
