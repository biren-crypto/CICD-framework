package birenproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import birenproject.Abstractcomponenrt.Abstractcomponent;
	
public class LandingPage  extends Abstractcomponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="userEmail")
	WebElement useremail;
	
	@FindBy(id ="userPassword")
	WebElement password;
	
	@FindBy(id ="login")
	WebElement sumbitButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessege;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

	}
	public ProductCatalouge loginApplication(String username, String Password) {
		useremail.sendKeys(username);
		password.sendKeys(Password);
		sumbitButton.click();
		ProductCatalouge productalouge =new ProductCatalouge(driver);
		return productalouge;
	}
	
	public String geterrormessage() {
		waitForWebElementToAppear(errormessege);
		return errormessege.getText();
	}
}
