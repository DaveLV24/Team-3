package stepDefinitions;

import hooks.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.CheckoutPage;
import pageObjects.MainPage;
import pageObjects.NavbarPageObject;
import pageObjects.ShoppingCartPage;

import java.time.Duration;
import java.util.Map;

import static hooks.Hooks.driver;
import static org.junit.Assert.*;

public class CheckoutSteps {
    private WebDriver driver;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;
    private NavbarPageObject navbarPageObject;
    private MainPage mainPage;
    public CheckoutSteps() {
        this.driver = Hooks.driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        navbarPageObject = new NavbarPageObject(driver);
        mainPage = new MainPage(driver);
    }


    @Given("^the user is on the demoshop website$")
    public void theUserIsOnTheDemoshopWebsite() {
        driver.get(shoppingCartPage.mainPageUrl);
    }

    @And("^the user is not logged in$")
    public void theUserIsNotLoggedIn() {
        // i dislike this method of checking, but oh well ig
        driver.get("https://www.demoshop24.com/index.php?route=account/account");
        assertEquals("https://www.demoshop24.com/index.php?route=account/login",driver.getCurrentUrl());

        driver.get(shoppingCartPage.mainPageUrl);
        // TODO - ig implement this better
    }

    @And("the user has an empty shopping cart")
    public void theUserHasAnEmptyShoppingCart() {
//        driver.get(checkoutPage.shoppingCartPageUrl);
        // TODO - implement
    }


    @When("I add a MacBook from featured items to the shopping cart")
    public void iAddAMacBookFromFeaturedItemsToTheShoppingCart() { mainPage.addFirstFeaturedItem();
    }

    @And("I click on shopping cart in the navbar")
    public void iClickOnShoppingCartInTheNavbar() {
        navbarPageObject.openShoppingCartPage();
        assertEquals(shoppingCartPage.shoppingCartPageUrl, driver.getCurrentUrl());
    }

    @When("I go to {string}")
    public void iGoTo(String link) {
        driver.get(link);
    }

    @And("I click Add to cart")
    public void iClickAddToCart() {
        mainPage.addItemToCartFromPage();
    }

    @Then("I click checkout")
    public void iClickCheckout() {
        shoppingCartPage.clickShoppingCartPageCheckout();
    }

    @And("I assert that I received error: {string}")
    public void iAssertThatIReceivedError(String errorText) {
        assertTrue(shoppingCartPage.getShoppingCartPageError().getText().contains(errorText));
        // I'd use assertequals here, but the actual text seems to have a weird symbol that cucumber doesn't recognize as part of the string.
    }

    @And("I assert that I see empty cart text: {string}")
    public void iAssertThatISeeEmptyCartText(String text) {
        assertEquals(text, shoppingCartPage.getShoppingCartEmptyCartText().getText());
    }

    @And("I assert that checkout button does not exist")
    public void iAssertThatCheckoutButtonDoesNotExist() {
        assertFalse(shoppingCartPage.doesCheckoutExist()); // i dislike this implementation but i couldnt find a better one
    }

    @Then("I click continue in cart")
    public void iClickContinueInCart() {
        shoppingCartPage.clickShoppingCartPageContinue();
    }

    @And("I assert that I am on the home page")
    public void iAssertThatIAmOnTheHomePage() {
        assertEquals(shoppingCartPage.mainPageUrl, driver.getCurrentUrl());
    }


    @And("I click checkout via navbar")
    public void iClickCheckoutViaNavbar() {
        navbarPageObject.openCheckoutPage();
    }

    @And("I assert I am on the checkout page")
    public void iAssertIAmOnTheCheckoutPage() {
        assertEquals(checkoutPage.checkoutPageUrl, driver.getCurrentUrl());
    }

    @And("I assert I am on the shopping cart page")
    public void iAssertIAmOnTheShoppingCartPage() {
        assertEquals(shoppingCartPage.shoppingCartPageUrl, driver.getCurrentUrl());
    }

    @And("I select {string} by index in the radio menu")
    public void iSelectInTheRadioMenu(String radioIndex) {
        // 0 - register
        // 1 - guest
        checkoutPage.selectIndexCheckoutOptionsRadio(radioIndex);
    }

    @And("I click continue1")
    public void iClickContinue1() {
        checkoutPage.clickContinueS1();
    }

    @And("I click continue2")
    public void iClickContinue2() {
        checkoutPage.clickContinueS2();
    }

    @And("I click continue3")
    public void iClickContinueS3() {checkoutPage.clickContinueS3();}

    @Then("I enter the following details:")
    public void iEnterTheFollowingDetails(Map<String, String> input) {

        checkoutPage.enterFirstName(input.get("first_name"));
        checkoutPage.enterLastName(input.get("last_name"));
        checkoutPage.enterEmail(input.get("email"));
        checkoutPage.enterTelephone(input.get("telephone"));
        checkoutPage.enterAdress1(input.get("address1"));
        checkoutPage.enterCity(input.get("city"));
        checkoutPage.enterPostCode(input.get("post_code"));
        checkoutPage.selectIndexRegion(input.get("region/state"));

        checkoutPage.clickContinueS2();
    }


    @Then("I click the I have read and agree to the Terms & Conditions checkbox")
    public void iClickTheIHaveReadAndAgreeToTheTermsConditionsCheckbox() {
        checkoutPage.clickAgreeTOSCheckbox();
    }

    @And("I see Step 3 warning: {string}")
    public void iSeeStepWarning(String warningText) {
        // the sleep is here because the warnings take a while to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // doing contains here because of the char i cant detect
        assertTrue(checkoutPage.getPaymentErrorText().contains(warningText));
    }
}
