package keywords;

import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericKeywords extends BaseClass {
	
	public WebDriver driver;
	public Properties prop;
	public Properties envProp;
	public ExtentTest log;
	
	public void openBrowser(String bName) {
		log.log(Status.PASS, "Opening Browser : "+bName);
		
		if(bName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
			
		}
		else if(bName.equalsIgnoreCase("Firefox")) {
			System.out.println("Opening Firefox Browser !!!");
		}
		else 
			System.out.println("Opening Edge Browser !!!");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	public void navigate(String urlKey) {
		
		driver.get(prop.getProperty(urlKey));
		log.log(Status.PASS, "Navigating to URL : "+prop.getProperty(urlKey));
	}
	public void click(String locatorKey) {
		getElement(locatorKey).click();
		log.log(Status.PASS, "Clicking on element : "+locatorKey);
	}
	public void type(String locatorKey, String data) {
		log.log(Status.PASS, "Populating : "+locatorKey+" with "+data);
		getElement(locatorKey).sendKeys(data);
	}
	public void select(String locator, String data) {
		log.log(Status.PASS, "Selecting : "+locator+" and the option is "+data);
	}
	public WebElement getElement(String locatorKey) {
		WebElement we = driver.findElement(getLocator(locatorKey));
		
		return we;
	}
	public boolean isElementPresent(String locatorKey) {
		log.log(Status.INFO,"Checking the presence of the element");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
				wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
		}
		catch(Exception e) {
			return false;
		}
	
		return true;
	}
	public boolean isElementVisible(String locatorKey) {
		log.log(Status.INFO,"Checking the visibility of the element");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		}
		catch(Exception e) {
			return false;
		}
	
		return true;
	}
	public By getLocator(String locatorKey) {
		By by = null;
		if(locatorKey.endsWith("_xpath"))
			by =By.xpath(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_id"))
			by = By.id(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_css"))
			by = By.cssSelector(prop.getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			by = By.name(prop.getProperty(locatorKey));
		
		return by;
	}

}
