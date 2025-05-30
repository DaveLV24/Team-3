package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // all my locators
    By cartButtonNearSearch = By.cssSelector("#cart button");
    By checkoutButtonInCartPreview = By.cssSelector("#cart a[href$='checkout/checkout']");
    By macBookAddToCartButton = By.xpath("//div[@class='caption']/h4/a[text()='MacBook']/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'cart.add')]"); //other didn't work either
    By iPhoneAddToCartButton = By.xpath("//div[@class='caption']/h4/a[text()='iPhone']/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'cart.add')]");
    By cartPreviewEmptyMessage = By.cssSelector("#cart .dropdown-menu p");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(11));
    }

    public void addMacBookToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(macBookAddToCartButton)).click();
    }

    public void addIPhoneToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(iPhoneAddToCartButton)).click();
    }

    public void clickCartButtonNearSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButtonNearSearch)).click();
    }

    public void clickCheckoutInCartPreview() {
        driver.findElement(By.linkText("Checkout")).click();
    }



    public String getEmptyCartPreviewMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPreviewEmptyMessage));
        return messageElement.getText();
    }

    public boolean isCheckoutButtonInCartPreviewVisible() {
        try {
            WebElement checkoutButton = driver.findElement(checkoutButtonInCartPreview);
            return checkoutButton.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
