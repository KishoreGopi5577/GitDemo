package samplePkg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	@Test
	public void firstTest() throws InterruptedException {
		String productName = "ZARA COAT 3";


		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		Actions a = new Actions(driver);
		
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("ding@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ding@123");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='container']/div[2]//b"));

		String path1 = "(//div[@class='container']/div[2]//button[2])[";
		String path2 = "]";

		for(int i=0;i<products.size();i++) {
		if (products.get(i).getText().equals(productName))
		driver.findElement(By.xpath(path1+(i+1)+path2)).click();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.alertIsPresent());
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("String"));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		Thread.sleep(2000);

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		for(int i=0;i<cartProducts.size();i++) {
		boolean match = cartProducts.get(i).getText().equalsIgnoreCase(productName);
		Assert.assertTrue(match);
		}

		WebElement we = driver.findElement(By.xpath("//li[@class='totalRow']//button"));
		a.moveToElement(we).click().perform();

		Thread.sleep(2000);


		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]"))));

		driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();


		Thread.sleep(2000);

		a.moveToElement(driver.findElement(By.xpath("//div[@class='actions']//a"))).click().perform();

		String confirmMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(2000);

		driver.quit();
		Thread.sleep(2000);

		}
	}


