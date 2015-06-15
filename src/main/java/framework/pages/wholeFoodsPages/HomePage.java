package framework.pages.wholeFoodsPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.applause.auto.pageframework.pages.Screen;



public class HomePage extends Screen{

	public HomePage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (how = How.XPATH, using = "//android.widget.EditText[contains(@resource-id, 'com.wholefoods.wholefoodsmarket:id/etHomeSearch')]")
	public WebElement searchField;
	
	@FindBy (how = How.XPATH, using = "//android.widget.ImageView[contains(@resource-id, 'com.wholefoods.wholefoodsmarket:id/imgSearch')]")
	public WebElement searchButton;
	
	public SearchResultPage search(String searchText){
		waitForElementPresent(searchField);
		searchField.sendKeys(searchText);
		searchButton.click();
		return PageFactory.initElements(appiumDriver, SearchResultPage.class);
	}

}
