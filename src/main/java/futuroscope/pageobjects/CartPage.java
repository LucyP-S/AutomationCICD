package futuroscope.pageobjects;

import futuroscope.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;

    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(css=".totalRow button")
    WebElement checkoutButton;


   public Boolean VerifyProductDisplay (String productName)
   {
       boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
       return match;
   }

   public CheckoutPage goToCheckout()
   {
       checkoutButton.click();
       CheckoutPage checkoutPage = new CheckoutPage(driver);
       return checkoutPage;
   }







}
