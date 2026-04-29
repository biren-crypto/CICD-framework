package birenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import birenproject.Abstractcomponenrt.Abstractcomponent;

public class CartPage extends Abstractcomponent{
	WebDriver driver;



@FindBy (css=".cartSection h3")
List <WebElement> cartproducts;

@FindBy(xpath = "//button[text()='Checkout']")
WebElement checkoutButton;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver, this);
	}
	
	public boolean verifyProductDisplayed(String productName) {
		boolean match =cartproducts.stream().anyMatch(cartproducts-> cartproducts.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public  CheckoutPage goTOCheckOut() {
		checkoutButton.click();
		CheckoutPage checkoutpage =new CheckoutPage(driver);
		return checkoutpage;
	}
	

}






