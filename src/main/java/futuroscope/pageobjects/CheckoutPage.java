package futuroscope.pageobjects;

import futuroscope.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[placeholder='Select Country']")
    private WebElement selectCountryField;  // Encapsulation - element cannot be used outside of this class

    @FindBy(css=".ta-item:nth-of-type(3)")
    private WebElement selectCountryOption;

    @FindBy(css=".action__submit")
    private WebElement placeOrderButton;

    By results = By.cssSelector(".ta-results");


    public void selectCountry(String countryName) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(selectCountryField, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountryOption.click();
    }

    public ConfirmationPage placeOrder()
    {
        placeOrderButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }








}
