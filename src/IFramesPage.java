import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IFramesPage {
	Logger logger = LogManager.getLogger(IFramesPage.class);
	WebDriver driver;
	
	public IFramesPage (WebDriver driver) {
		this.driver = driver;
	}
	
	By framesLink = By.xpath("//a[text()='Frames']");
	By iFrameLink = By.xpath("//a[text()='iFrame']");
	By frameId = By.id ("mce_0_ifr");         
	By fieldId = By.id("tinymce");
		
	public void typeInIFrameField (String inputString) {
		logger.info("go to iFrame");
		driver.findElement(framesLink).click();
		driver.findElement(iFrameLink).click();
		
		logger.info("write my string");
		driver.switchTo().frame(driver.findElement(frameId));
		driver.findElement(fieldId).clear();
		driver.findElement(fieldId).sendKeys(inputString);
			
		logger.info("check the the input field has my inputed string");
		Assert.assertTrue("Verification Failed:  The text that is written in text paragraph is wrong!"  ,driver.findElement(fieldId).getText().equals(inputString));
	}

}
