package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.CheckoutPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    public CheckoutSteps() {
        this.driver = Hooks.driver;
        checkoutPage = new CheckoutPage(driver);
    }


    @Given("^the user is on the demoshop website$")
    public void theUserIsOnTheDemoshopWebsite() {
        driver.get(checkoutPage.mainPageUrl);
    }

    @And("^the user is not logged in$")
    public void theUserIsNotLoggedIn() {
        // i dislike this method of checking, but oh well ig
        driver.get("https://www.demoshop24.com/index.php?route=account/account");
        assertEquals("https://www.demoshop24.com/index.php?route=account/login",driver.getCurrentUrl());
        // TODO - ig implement this better
    }

    @And("the user has an empty shopping cart")
    public void theUserHasAnEmptyShoppingCart() {
        driver.get(checkoutPage.shoppingCartPageUrl);
        // TODO - implement
    }
}
