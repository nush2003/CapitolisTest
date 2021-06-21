
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BasicAuthPage {
	Logger logger = LogManager.getLogger(BasicAuthPage.class);
	WebDriver driver;
	
	public BasicAuthPage (WebDriver driver) {
		this.driver = driver;
	}
	
	 By content = By.id("content");
	 
	
		
	public void checkBasicAuthPopup () throws InterruptedException {
		logger.info("entering wrong credentials");
		driver.get("https://fakeUser:fakePass@the-internet.herokuapp.com/basic_auth");
		try {
			driver.findElement(content);
			Assert.fail("Verification Failed: After entering the wrong credentials the content page shouldn't appear!");			
		}catch(Exception  e) {}
		
		logger.info("entering right credentials but with capital letters- shouldn't pass");
		driver.get("https://Admin:Admin@the-internet.herokuapp.com/basic_auth");
		try {
			driver.findElement(content);
			Assert.fail("Verification Failed: After entering the right credentials but with capital letters the content page shouldn't appear!");			
		}catch(Exception  e) {}
		
		logger.info("entering right credentials");
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue("Verification Failed: After entering the right credentials the login didn't work properly! "  ,driver.findElement(content).getText().contains("Congratulations! You must have the proper credentials"));
		Thread.sleep(5000);
		
	}
}
