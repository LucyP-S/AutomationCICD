package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import futuroscope.abstractcomponents.AbstractComponents;
import futuroscope.pageobjects.CartPage;
import futuroscope.pageobjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups ="ErrorHandling", retryAnalyzer = Retry.class)
            public void LoginErrorMessage() throws IOException, InterruptedException {

        landingPage.loginApplication("lpalucka1@gmail.com", "123Robin");
        landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        System.out.println("Incorrect email");

        landingPage.loginApplication("lpalucka@gmail.com", "1234Robin");
        landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        System.out.println("Incorrect password");
    }


    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("lucytestowekonto@gmail.com", "Robin1234"); // It is good to have different account for different tests to avoid overlapping issues
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        Thread.sleep(3000);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
        System.out.println("Product is not in the cart");
        AbstractComponents.signOut();
    }
}
