package birenproject.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import birenproject.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;


	public WebDriver initializeDriver() throws IOException {
		//properties Class
		Properties prop =new Properties();
		//convert file path into input stream 
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\birenproject\\Resources\\GlobalData.properties");
		prop.load(fis);


		//Java ternary Operater ----->variable = (condition) ? value_if_true : value_if_false;

		String BrowserName =	System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");

		if(BrowserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(BrowserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver =new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));//help in full screen mode 

		}

		else if(BrowserName.equalsIgnoreCase("Firefox")){
			driver =new FirefoxDriver();
		}

		else if(BrowserName.equalsIgnoreCase("Edge")){
			driver =new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}



	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {

		// Read JSON file to String
		String jsonContent = FileUtils.readFileToString(
				new File(Filepath), "UTF-8");

		// Convert String to HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(
				jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {}
				);

		return data;
	}



	@BeforeMethod(alwaysRun = true)
	public LandingPage lunchApplication() throws IOException {
		driver =initializeDriver();
		landingpage =new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	@AfterMethod(alwaysRun = true)
	public void tierdown() {
		driver.quit();
	}

	public String getScreenShoot(String TestcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts =(TakesScreenshot)driver;
		File Sourse =	ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"\\reports"+TestcaseName+".png");
		FileUtils.copyFile(Sourse, file);
		return System.getProperty("user.dir")+"\\reports"+TestcaseName+".png";
		//---------------------------------get the screenshot and path----------------------------------------> 

	}


}
