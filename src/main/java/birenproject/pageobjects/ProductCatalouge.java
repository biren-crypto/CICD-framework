package birenproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import birenproject.Abstractcomponenrt.Abstractcomponent;
	
public class ProductCatalouge extends Abstractcomponent{
	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".col-lg-4")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsby =By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");	
	By toster =By.id("toast-container");

	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsby);
		return products;

	}
	public WebElement GetProductByName(String productName) {
		WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName) {
		WebElement prod = GetProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toster);	
		waitForElementToDisappear(spinner);
		
	}
}
 