package birenproject.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Standalone {
	public static void main(String[] args) throws InterruptedException {
		 
		String productname ="ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("biren@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Biren@1234");
		driver.findElement(By.id("login")).click();
		//login complete`	
		List<WebElement> products =driver.findElements(By.cssSelector(".col-lg-4"));
	WebElement product=	products.stream().filter(prod->prod.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		List<WebElement> cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
	boolean match=	cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
	Assert.assertTrue(match);
	driver.findElement(By.xpath("//button[text()='Checkout']")).click();	

	driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.list-group")));
	List<WebElement> options=driver.findElements(By.cssSelector("section.ta-results button.ta-item"));
	options.stream().filter(option->option.getText().equalsIgnoreCase("india")).findFirst().ifPresent(WebElement::click);
	driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
	String orderid =driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
	System.out.println(orderid);
	String confmesg =driver.findElement(By.tagName("h1")).getText();
	Assert.assertTrue(confmesg.equalsIgnoreCase("Thankyou for the order."));
		
	 	
		
	//	
		
		
		Thread.sleep(4000);
		driver.quit();
	}

}
