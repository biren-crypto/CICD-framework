package birenproject.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import birenproject.pageobjects.CartPage;
import birenproject.pageobjects.CheckoutPage;
import birenproject.pageobjects.ConformationPage;
import birenproject.pageobjects.OrdersPage;
import birenproject.pageobjects.ProductCatalouge;
import birenproject.testComponents.BaseTest;
import birenproject.testComponents.Retry;

public class EndToEnd extends BaseTest{
	String productName="ZARA COAT 3";
	@Test(dataProvider = "getData", groups = {"dataprovider"} , retryAnalyzer=Retry.class)
	public void sumbitOrder(HashMap<String, String> input) throws IOException{
		ProductCatalouge productcatalouge =landingpage.loginApplication(input.get("email"), input.get("password"));

		productcatalouge.addProductToCart(input.get("productName"));
		CartPage cartpage = productcatalouge.goTOCartPage();

		boolean match =cartpage.verifyProductDisplayed(input.get("productName"));
		Assert.assertTrue(match);		
		CheckoutPage checkoutpage =	cartpage.goTOCheckOut();
		checkoutpage.selectCountry("india");
		ConformationPage conformationpage = checkoutpage.sumbitOrder();
		String confmesg =conformationpage.getConformationmessage();
		Assert.assertTrue(confmesg.equalsIgnoreCase("Thankyou for the order."));

	}
	@Test(dependsOnMethods = {"sumbitOrder"})
	public void orderHistryTest() {
		ProductCatalouge productcatalouge =landingpage.loginApplication("biren@gmail.com", "Biren@1234");
		OrdersPage orderpage =productcatalouge.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrdertDisplayed(productName));
	}
	
	
	
	@DataProvider
	public Object[] [] getData() throws IOException {
		

		List<HashMap<String, String>> data =getJsonDataToMap
				(System.getProperty("user.dir") + "\\src\\test\\java\\birenproject\\data\\purchaseOrder.json");
		return new  Object[][]  {{data.get(0)},{data.get(1)}};
	}
} 



// end to end 













