package report;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import keywords.BaseClass;

public class ExtentReporterNG extends BaseClass {
	
	public ExtentReports getReportObject() {
		
		Date d = new Date();
		String name = d.toString().replace(':', '_');
		
		String path = System.getProperty("user.dir")+"\\Reporting\\"+name+".png";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("First Report Name");
		reporter.config().setDocumentTitle("XYZ application testing");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Tester1");
		
		return extent;
	}

}
