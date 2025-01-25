package stepdefinitions;

import TestComponents.BaseTest;
import futuroscope.abstractcomponents.AbstractComponents;
import futuroscope.pageobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;

    @Given ("I landed on Ecommerce Page")
    public void iLandedOnEcommercePage() throws IOException {
        landingPage = launchApplication();
    }

    @Given ("^Logged in with username (.+) and password (.+)$")
    public void loggedInWithUsernameAndPassword(String username, String password) throws IOException {
         productCatalogue = landingPage.loginApplication(username, password);
    }

    @When ("^I add product productName (.+) to Cart$")
    public void iAddProductToCart(String productName) throws IOException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When ("^Checkout productName (.+) and submit order$")
    public void checkoutAndSubmit(String productName) throws IOException, InterruptedException {
        cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Pol");
        confirmationPage = checkoutPage.placeOrder();
    }

    @Then ("{string} message is displayed on Confirmation Page")
    public void messageIsDisplayedOnConfirmationPage(String string) throws IOException {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then ("{string} message is displayed")
    public void messageIsDisplayed(String string) throws IOException {
        landingPage.getErrorMessage();
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }
}
