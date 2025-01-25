package futuroscope.abstractcomponents;

import futuroscope.pageobjects.CartPage;
import futuroscope.pageobjects.OrdersHistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Used to store all re-usable elements: waits, switching to frames and windows, javasript executors etc

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css="[routerlink*='myorders']")
    WebElement ordersButton;

    @FindBy(css="li:nth-child(5) button:nth-child(1)")
    static WebElement signOutButton;


    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement ele)  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage()
    {
        cartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrdersHistoryPage goToOrdersHistoryPage()
    {
        ordersButton.click();
        OrdersHistoryPage ordersHistoryPage = new OrdersHistoryPage(driver);
        return ordersHistoryPage;
    }

    public static void signOut()
    {
        signOutButton.click();
    }


}
