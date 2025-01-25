package futuroscope.pageobjects;

import futuroscope.abstractcomponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".hero-primary")
    WebElement confirmationMessage;



    public String getConfirmationMessage()
    {
        return confirmationMessage.getText();
    }

}



//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
     //   Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//        driver.findElement(By.cssSelector("li:nth-child(5) button:nth-child(1)")).click();