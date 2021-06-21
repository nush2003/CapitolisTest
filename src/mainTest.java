
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;



public class mainTest {
	static WebDriver driver;
	static String path = System.getProperty("user.dir");
	Logger logger = LogManager.getLogger(mainTest.class);
	
	CheckBoxPage checkBoxPage = new CheckBoxPage(driver);
	BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
	IFramesPage IFramesPage = new IFramesPage(driver);
	DynamicLoadingPage dynLoadPage = new DynamicLoadingPage(driver);
	JQueryUIPage jQueryUIPage = new JQueryUIPage(driver);
	
	
	@BeforeClass
	public static void initDriver() {
				
		System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("download.default_directory", path);
	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("prefs", chromePrefs);	
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		
	}
	
	
	 @Test
	 public void test() throws IOException, InterruptedException {
		 
		
		driver.get("http://the-internet.herokuapp.com/");	
		
		logger.info("Starting Checkboxes test");
		checkBoxPage.checkBoxesStatus();
		driver.navigate().back();
		
		logger.info("Starting Frames test");
		IFramesPage.typeInIFrameField("Inna Pery's test");
		driver.navigate().back();	
		driver.navigate().back();	
		
		logger.info("Starting Dynamic Loading test");
		dynLoadPage.waitForStringToAppear("Hello World!");
		driver.navigate().back();	
		driver.navigate().back();	
		
		logger.info("Starting JQueryUI test");
		jQueryUIPage.downloadExcelFile(path, "menu.xls");
		driver.navigate().back();
		
		logger.info("Starting Basic Auth test");
		basicAuthPage.checkBasicAuthPopup();
	  				 
	 } 
	 
	
	
	 
	 @AfterClass
	 public static void tearDown() {
		driver.quit();
	 }
	
}
