package birenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import birenproject.Abstractcomponenrt.Abstractcomponent;

public class CheckoutPage extends Abstractcomponent{
	WebDriver driver;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryplaceholder;
	
	@FindBy(css = "section.ta-results button.ta-item")
	List<WebElement> countryoptions;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement sumbitbutton;
	
	By countryoption =By.cssSelector("section.list-group");
	
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	public void selectCountry(String Countryname) {
		Actions a=new Actions(driver);
		a.sendKeys(countryplaceholder,Countryname).build().perform();
		waitForElementToAppear(countryoption);
		countryoptions.stream().filter(option->option.getText().equalsIgnoreCase("india")).findFirst().ifPresent(WebElement::click);
		
	} 
	
	public ConformationPage sumbitOrder() {
		sumbitbutton.click();
		ConformationPage conformationpage =new ConformationPage(driver);
		return conformationpage;
		
	}
	

}













//String orderid =driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
//System.out.println(orderid);
//String confmesg =driver.findElement(By.tagName("h1")).getText();
//Assert.assertTrue(confmesg.equalsIgnoreCase("Thankyou for the order."));
	
