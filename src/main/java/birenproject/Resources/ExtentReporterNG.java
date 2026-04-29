package birenproject.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent;
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "/reports/index.html";
		ExtentSparkReporter reporter =new ExtentSparkReporter(path);

		reporter.config().setDocumentTitle("Test result");
		reporter.config().setReportName("Web Automation Result");

		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Biren" );
		return extent;

	}
}
// here we create html report ---------->set the path -------------->config and modify report accordingly------------->