package futuroscope.pageobjects;

import futuroscope.abstractcomponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersHistoryPage extends AbstractComponents {

    WebDriver driver;

    public OrdersHistoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="tr td:nth-child(3)")
    private List<WebElement> productNames;



    public Boolean VerifyOrderDisplay (String productName)
    {
        boolean match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
}
