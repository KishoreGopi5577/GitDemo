package keywords;

import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ApplicationKeywords extends ValidationKeywords {
	
	ExtentTest log;
	
	public ApplicationKeywords(ExtentTest log) {
		this.log = log;
		super.log = log;
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\env.properties";
		prop = new Properties();
		envProp = new Properties();
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			String env = prop.getProperty("env");
			path = System.getProperty("user.dir")+"\\src\\test\\resources\\"+env+".properties";
			fis = new FileInputStream(path);
			envProp.load(fis);
			
				}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void login() {
		log.log(Status.PASS, "Logging in");
	}
	public void selectDateFromCalendar() {
		
	}
	public void verifyStockAdded() {
		
	}
	public void tearDown() {
		driver.quit();
	}
	

}
