package framework.pages.wholeFoodsPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.applause.auto.pageframework.pages.Screen;

public class SearchResultPage extends Screen{

	public SearchResultPage(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	@FindBy (how = How.XPATH, using = "//android.widget.ListView")
	public WebElement multipleSearchResults;
	
	public boolean searchMultiplyResultIsDisplayed(){
		return multipleSearchResults.isDisplayed();
	}

}
