import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JQueryUIPage {
	Logger logger = LogManager.getLogger(JQueryUIPage.class);
	WebDriver driver;
	
	public JQueryUIPage (WebDriver driver) {
		this.driver = driver;
	}
	
	By jqueryUILink = By.xpath("//a[text()='JQuery UI Menus']");	      
	By enabledLink = By.id("ui-id-2");
	By downloadLink = By.id("ui-id-4");
	By excelFormat = By.id("ui-id-8");
	
		
	public void downloadExcelFile (String path, String fileName) throws InterruptedException {
			
		Actions actionProvider = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,10);	 
		 
		logger.info("go to JQueryUI – Menu page ");
		driver.findElement(jqueryUILink).click();	
		
		
		logger.info("choose 'Enable' -> 'Download' -> 'Excel' ");
	    actionProvider.moveToElement(driver.findElement(enabledLink)).build().perform();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(downloadLink)));
	    actionProvider.moveToElement(driver.findElement(downloadLink)).build().perform();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(excelFormat)));
	    driver.findElement(excelFormat).click();	    
	 
	    logger.info("wait for file to finish downloading");
	    File targetFile = new File(path, fileName);	   
	    int downloadTime = 10;
	    waitForFile(targetFile, downloadTime);	  
		
	    Assert.assertTrue("Verification Failed:  The file doesn't exisr or empty!"  ,targetFile.length()>0);
	}
	
	private void waitForFile( File file, long timeout) {
		FluentWait wait = new FluentWait(driver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
		wait.until((driver) -> file.exists());
	}


}
