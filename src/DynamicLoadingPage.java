import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicLoadingPage {
	Logger logger = LogManager.getLogger(DynamicLoadingPage.class);
	WebDriver driver;
	
	public DynamicLoadingPage (WebDriver driver) {		
		this.driver = driver;
	}
	
	By dynamicLoadingLink = By.xpath("//a[text()='Dynamic Loading']");
	By secondExampleLink = By.xpath("//a[text()='Example 2: Element rendered after the fact']");
	By startBtn = By.xpath("//button[text()='Start']");        
	By finishField = By.id("finish");
		
	public void waitForStringToAppear (String actualString) {
		logger.info("go to second example of dynamic loading");
		driver.findElement(dynamicLoadingLink).click();
		driver.findElement(secondExampleLink).click();
		
		driver.findElement(startBtn).click();
		
		logger.info("wait for finish and string to appear ");
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(finishField)));
		
		logger.info("chech the string that appear is right");	
		Assert.assertTrue("Verification Failed:  The text that is written in text paragraph is wrong!"  ,driver.findElement(finishField).getText().equals(actualString));
	}


}
