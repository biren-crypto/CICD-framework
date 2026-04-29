package birenproject.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import birenproject.pageobjects.CartPage;
import birenproject.pageobjects.ProductCatalouge;
import birenproject.testComponents.BaseTest;
import birenproject.testComponents.Retry;

public class ErrorValidation extends BaseTest{
	@Test(groups  = {"ErrorHandling"}, retryAnalyzer=Retry.class)

	public void loginErrorValidation() throws IOException{
		  landingpage.loginApplication("biren@gmail.com", "Biren@12334");
		  
		 Assert.assertEquals("Incorrect email or password.", landingpage.geterrormessage());
	}
	@Test(groups  = {"ErrorHandling"})
	public void cartpageErrorValidation() {
		String productName="ZARA COAT 3";

		ProductCatalouge productcatalouge =landingpage.loginApplication("biren@gmail.com", "Biren@1234");

		productcatalouge.addProductToCart(productName);
		CartPage cartpage = productcatalouge.goTOCartPage();

		boolean match =cartpage.verifyProductDisplayed(productName);
		Assert.assertTrue(match);		
		
	}
}

//  retryAnalyzer=Retry.class ---> if test fail it will be respond for re run the failed test 