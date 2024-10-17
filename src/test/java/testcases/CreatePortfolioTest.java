package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import keywords.ApplicationKeywords;
import report.ExtentReporterNG;


public class CreatePortfolioTest {
	
	@Test
	public void createPortFolioTest() {
		
		ExtentReporterNG reporter = new ExtentReporterNG();
		ExtentReports logger = reporter.getReportObject();
		ExtentTest log = logger.createTest("FIRST TEST");
		ApplicationKeywords app = new ApplicationKeywords(log);
		
		
		log.log(Status.PASS, "Starting Test");
		
		app.openBrowser("Chrome");
		app.navigate("url");
		//app.click("signIn_xpath");
		app.type("username_xpath", "gopikishore5577@rediffmail.com");
		app.type("password_xpath", "Selenium@1");
		app.click("login_submit_xpath");
				
		app.validateTitle();
		app.validateElementPresent();
		app.selectDateFromCalendar();
		app.tearDown();
		
		logger.flush();
		
	}

}
