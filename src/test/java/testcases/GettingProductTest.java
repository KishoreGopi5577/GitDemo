package testcases;

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

public class GettingProductTest {
	
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
		System.out.println("LOGIN IS SUCCESSFUL !!!");

		List<WebElement> products = driver.findElements(By.xpath("//div[@class='container']/div[2]//b"));

		String path1 = "(//div[@class='container']/div[2]//button[2])[";
		String path2 = "]";

		for(int i=0;i<products.size();i++) {
			System.out.println("THE DISPLAYED PRODUCT IS :: "+products.get(i).getText());
		if (products.get(i).getText().equals(productName))
		driver.findElement(By.xpath(path1+(i+1)+path2)).click();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait.until(ExpectedConditions.alertIsPresent());
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("String"));

				driver.quit();
		Thread.sleep(2000);

		}
	}


