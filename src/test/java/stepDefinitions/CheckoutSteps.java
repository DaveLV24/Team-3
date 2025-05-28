package stepDefinitions;

import hooks.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.CheckoutPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    public CheckoutSteps() {
        this.driver = Hooks.driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
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

        driver.get(checkoutPage.mainPageUrl);
        // TODO - ig implement this better
    }

    @And("the user has an empty shopping cart")
    public void theUserHasAnEmptyShoppingCart() {
//        driver.get(checkoutPage.shoppingCartPageUrl);
        // TODO - implement
    }


    @When("I add a MacBook from featured items to the shopping cart")
    public void iAddAMacBookFromFeaturedItemsToTheShoppingCart() {
        checkoutPage.addFirstFeaturedItem();
    }

    @And("I click on shopping cart in the navbar")
    public void iClickOnShoppingCartInTheNavbar() {
        checkoutPage.openShoppingCartPageViaNavbar();
        assertEquals(checkoutPage.shoppingCartPageUrl, driver.getCurrentUrl());
    }

    @When("I go to {string}")
    public void iGoTo(String link) {
        driver.get(link);
    }

    @And("I click Add to cart")
    public void iClickAddToCart() {
        checkoutPage.addItemToCartFromPage();
    }
}
