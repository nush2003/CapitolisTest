import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckBoxPage {
	Logger logger = LogManager.getLogger(CheckBoxPage.class);
	WebDriver driver;
	
	public CheckBoxPage (WebDriver driver) {
		this.driver = driver;
	}
	
	By checkBoxesLink = By.xpath("//a[text()='Checkboxes']");
	By checkBoxesId = By.id ("checkboxes");         
	
		
	public void checkBoxesStatus ()  {
		logger.info("get all checkboxes on the page");
		driver.findElement(checkBoxesLink).click();
		List<WebElement> checkBoxesList = driver.findElement(checkBoxesId).findElements(By.xpath("..//input"));
		
		logger.info("go thought all checkboxes and click on each one,then check that the status changed");
		for (WebElement ckBox : checkBoxesList) {
			if (ckBox.getAttribute("checked") != null) {
				ckBox.click();			
				Assert.assertTrue("Verification Failed: CheckBox that was checked didn't change its status after clicking on it! "  ,ckBox.getAttribute("checked") == null);
			}else {
				ckBox.click();			
				Assert.assertTrue("Verification Failed:  CheckBox that wasn't checked didn't change its status after clicking on it!"  ,ckBox.getAttribute("checked") != null);
			}			
		}		
	}
}
