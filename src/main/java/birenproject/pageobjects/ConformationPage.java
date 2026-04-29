package birenproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import birenproject.Abstractcomponenrt.Abstractcomponent;

public class ConformationPage extends Abstractcomponent {
	WebDriver driver;
	
	@FindBy (tagName = "h1")
	WebElement conformationmessage;
	
	public ConformationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	public String getConformationmessage() {
		return conformationmessage.getText();
	}
	
	

}




