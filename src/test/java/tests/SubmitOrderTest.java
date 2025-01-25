package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import futuroscope.abstractcomponents.AbstractComponents;
import futuroscope.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups= {"Purchase"}, retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        Thread.sleep(3000);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Pol");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("Confirmation message matches");
        AbstractComponents.signOut();
    }

    @Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(HashMap<String, String> input) throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrdersHistoryPage ordersHistoryPage = productCatalogue.goToOrdersHistoryPage();
        Assert.assertTrue(ordersHistoryPage.VerifyOrderDisplay(input.get("productName")));
    }

    // JSON DataProvider
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }


    //DataProvider
    /*@DataProvider
    public Object[][] getData() {
        return new Object[][] {{"lpalucka@gmail.com", "123Robin", "ZARA COAT 3"}, {"lucytestowekonto@gmail.com", "Robin1234", "ADIDAS ORIGINAL"}};
    } */

    //HashMap DataProvider
    /* @DataProvider
    public Object[][] getData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("email", "lpalucka@gmail.com");
        map.put("password", "123Robin");
        map.put("productName", "ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("email", "lucytestowekonto@gmail.com");
        map1.put("password", "Robin1234");
        map1.put("productName", "ADIDAS ORIGINAL");

        return new Object[][] {{map}, {map1}};
    } */

}
