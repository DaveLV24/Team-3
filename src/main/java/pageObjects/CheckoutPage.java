package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    By checkoutLink = By.cssSelector("#top-links a[title='Checkout']");
    By emptyCartMessage = By.xpath("//div[@id='content']//p[contains(text(), 'Your shopping cart is empty!')]");
    By outOfStockErrorMessage = By.cssSelector(".alert.alert-danger");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(11));
    }

    public void clickCheckoutLink() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLink)).click();
    }

    public String getEmptyCartMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
        return messageElement.getText();
    }

    public String getOutOfStockErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(outOfStockErrorMessage));
            return errorElement.getText();
        } catch (TimeoutException e) {
            System.out.println(driver.getPageSource());
            throw e;
        }
    }


    public boolean isCheckoutPageDisplayed() {
        return driver.getTitle().contains("Checkout");
    }

    public boolean isUserLoggedIn() {
        return !driver.findElements(By.xpath("//a[contains(@href,'account/logout')]")).isEmpty(); //easiest way to check in my opinion
    }


}
