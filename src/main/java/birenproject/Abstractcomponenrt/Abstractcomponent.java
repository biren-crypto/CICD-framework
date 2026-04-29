package birenproject.Abstractcomponenrt;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import birenproject.pageobjects.CartPage;
import birenproject.pageobjects.OrdersPage;

public class Abstractcomponent {
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(xpath = "//button[contains(text(), ' ORDERS')]")
	WebElement Orders;
	

	WebDriver driver;
	public Abstractcomponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBY) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
	}
	
	public void waitForWebElementToAppear(WebElement findBY) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBY));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goTOCartPage() {
		cartHeader.click();
		CartPage cartpage =new CartPage(driver);
		return cartpage;
	}
	
	public  OrdersPage goToOrderPage() {
		Orders.click();
		OrdersPage orderpage =new OrdersPage(driver);
		return orderpage;
	}
	
	
}