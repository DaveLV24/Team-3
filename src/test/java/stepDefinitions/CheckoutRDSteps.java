package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.CheckoutRDPage;

public class CheckoutRDSteps {

    private WebDriver driver;
    private HomePage homePage;
    private CheckoutRDPage checkoutRDPage;

    public CheckoutRDSteps() {
        this.driver = Hooks.driver;
        homePage = new HomePage(driver);
        checkoutRDPage = new CheckoutRDPage(driver);
    }

    @Given("the user is on the homepage")
    public void userIsOnHomepage() {
        driver.get("https://www.demoshop24.com/");
    }

    @Then("the user should not be logged in")
    public void verifyUserNotLoggedIn() {
        Assert.assertFalse("User is unexpectedly logged in!", checkoutRDPage.isUserLoggedIn());
    }


    @When("the user clicks on the Checkout link")
    public void userClicksOnCheckout() {
        checkoutRDPage.clickCheckoutLink();
    }

    @Then("the user should see an empty cart message")
    public void verifyEmptyCartMessage() {
        String message = checkoutRDPage.getEmptyCartMessage();
        Assert.assertTrue("Expected empty cart message not found", message.contains("Your shopping cart is empty!"));
    }

    @When("the user adds MacBook to the cart")
    public void addMacBookToCart() {
        homePage.addMacBookToCart();
    }

    @When("the user adds iPhone to the cart")
    public void addIPhoneToCart() {
        homePage.addIPhoneToCart();
    }

    @Then("the user should see an out of stock error message")
    public void verifyOutOfStockErrorMessage() {
        String message = checkoutRDPage.getOutOfStockErrorMessage();
        Assert.assertTrue("Out of stock message not found!",
                message.contains("Products marked with *** are not available in the desired quantity or not in stock!"));
    }

    @When("the user clicks on the Cart button near search")
    public void clickCartButtonNearSearch() {
        homePage.clickCartButtonNearSearch();
    }

    @When("the user clicks Checkout in the cart preview")
    public void clickCheckoutInCartPreview() {
        homePage.clickCheckoutInCartPreview();
    }

    @Then("the user should be routed to the Checkout page")
    public void verifyCheckoutPageDisplayed() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        //important to use contains for TC-009, as my other methods didn't work on 009
        Assert.assertTrue(
                "User is not routed to Checkout page. Current URL: " + currentUrl,
                currentUrl.contains("checkout")
        );
    }


    @When("the user clicks on the Cart button near search for out of stock item")
    public void clickCartButtonNearSearchOutOfStock() {
        homePage.clickCartButtonNearSearch();
    }

    @When("the user clicks Checkout in the cart preview for out of stock item")
    public void clickCheckoutInCartPreviewOutOfStock() {
        homePage.clickCheckoutInCartPreview();
    }

    @Then("the user should see an out of stock error message on the cart page")
    public void verifyOutOfStockErrorMessageOnCartPage() {
        String message = checkoutRDPage.getOutOfStockErrorMessage();
        Assert.assertTrue("Expected out of stock error message not found!",
                message.contains("Products marked with *** are not available in the desired quantity or not in stock!"));
    }

    @Then("the user should see an empty cart message in the cart preview")
    public void verifyEmptyCartMessageInCartPreview() {
        String message = homePage.getEmptyCartPreviewMessage();
        Assert.assertTrue("Expected empty cart message not found in cart preview!",
                message.contains("Your shopping cart is empty!"));
    }

    @Then("the checkout button should not be displayed in the cart preview")
    public void verifyNoCheckoutButtonInCartPreview() {
        boolean isCheckoutVisible = homePage.isCheckoutButtonInCartPreviewVisible();
        Assert.assertFalse("Checkout button should not be visible when cart is empty", isCheckoutVisible);
    }
}
