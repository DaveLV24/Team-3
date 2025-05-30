package stepDefinitions;

import hooks.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.CheckoutPageAM;
import pageObjects.MainPageAM;
import pageObjects.NavbarPageObjectAM;
import pageObjects.ShoppingCartPageAM;

import java.time.Duration;
import java.util.Map;

import static org.junit.Assert.*;

public class CheckoutStepsAM {
    private final WebDriver driver;
    private final ShoppingCartPageAM shoppingCartPageAM;
    private final CheckoutPageAM checkoutPageAM;
    private final NavbarPageObjectAM navbarPageObjectAM;
    private final MainPageAM mainPageAM;
    public CheckoutStepsAM() {
        this.driver = Hooks.driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        shoppingCartPageAM = new ShoppingCartPageAM(driver);
        checkoutPageAM = new CheckoutPageAM(driver);
        navbarPageObjectAM = new NavbarPageObjectAM(driver);
        mainPageAM = new MainPageAM(driver);
    }


    @Given("^the user is on the demoshop website$")
    public void theUserIsOnTheDemoshopWebsite() {
        driver.get(mainPageAM.pageUrl);
    }

    @And("^the user is not logged in$")
    public void theUserIsNotLoggedIn() {
        // i dislike this method of checking, but oh well ig. At least it throws an error if the user is not logged in.
        driver.get("https://www.demoshop24.com/index.php?route=account/account");
        assertEquals("https://www.demoshop24.com/index.php?route=account/login",driver.getCurrentUrl());

        driver.get(mainPageAM.pageUrl);
    }

    @And("the user has an empty shopping cart")
    public void theUserHasAnEmptyShoppingCart() {
        driver.get(shoppingCartPageAM.pageUrl);
        iAssertThatISeeEmptyCartText("Your shopping cart is empty!");
        driver.get(mainPageAM.pageUrl);
    }


    @When("I add a MacBook from featured items to the shopping cart")
    public void iAddAMacBookFromFeaturedItemsToTheShoppingCart() { mainPageAM.addFirstFeaturedItem();
    }

    @And("I click on shopping cart in the navbar")
    public void iClickOnShoppingCartInTheNavbar() {
        navbarPageObjectAM.openShoppingCartPage();
        assertEquals(shoppingCartPageAM.pageUrl, driver.getCurrentUrl());
    }

    @When("I go to {string}")
    public void iGoTo(String link) {
        driver.get(link);
    }

    @And("I click Add to cart")
    public void iClickAddToCart() {
        mainPageAM.addItemToCartFromPage();
    }

    @Then("I click checkout")
    public void iClickCheckout() {
        shoppingCartPageAM.clickShoppingCartPageCheckout();
    }

    @And("I assert that I received error: {string}")
    public void iAssertThatIReceivedError(String errorText) {
        assertTrue(shoppingCartPageAM.getShoppingCartPageError().getText().contains(errorText));
        // I'd use assertequals here, but the actual text seems to have a weird symbol that cucumber doesn't recognize as part of the string.
    }

    @And("I assert that I see empty cart text: {string}")
    public void iAssertThatISeeEmptyCartText(String text) {
        assertEquals(text, shoppingCartPageAM.getShoppingCartEmptyCartText().getText());
    }

    @And("I assert that checkout button does not exist")
    public void iAssertThatCheckoutButtonDoesNotExist() {
        assertFalse(shoppingCartPageAM.doesCheckoutExist()); // i dislike this implementation but i couldnt find a better one
    }

    @Then("I click continue in cart")
    public void iClickContinueInCart() {
        shoppingCartPageAM.clickShoppingCartPageContinue();
    }

    @And("I assert that I am on the home page")
    public void iAssertThatIAmOnTheHomePage() {
        assertEquals(mainPageAM.pageUrl, driver.getCurrentUrl());
    }


    @And("I click checkout via navbar")
    public void iClickCheckoutViaNavbar() {
        navbarPageObjectAM.openCheckoutPage();
    }

    @And("I assert I am on the checkout page")
    public void iAssertIAmOnTheCheckoutPage() {
        assertEquals(checkoutPageAM.pageUrl, driver.getCurrentUrl());
    }

    @And("I assert I am on the shopping cart page")
    public void iAssertIAmOnTheShoppingCartPage() {
        assertEquals(shoppingCartPageAM.pageUrl, driver.getCurrentUrl());
    }

    @And("I select {string} by index in the radio menu")
    public void iSelectInTheRadioMenu(String radioIndex) {
        // 0 - register
        // 1 - guest
        checkoutPageAM.selectIndexCheckoutOptionsRadio(radioIndex);
    }

    @And("I click continue1")
    public void iClickContinue1() {
        checkoutPageAM.clickContinueS1();
    }

    @And("I click continue2")
    public void iClickContinue2() {
        checkoutPageAM.clickContinueS2();
    }

    @And("I click continue3")
    public void iClickContinueS3() {
        checkoutPageAM.clickContinueS3();}

    @Then("I enter the following details:")
    public void iEnterTheFollowingDetails(Map<String, String> input) {

        checkoutPageAM.enterFirstName(input.get("first_name"));
        checkoutPageAM.enterLastName(input.get("last_name"));
        checkoutPageAM.enterEmail(input.get("email"));
        checkoutPageAM.enterTelephone(input.get("telephone"));
        checkoutPageAM.enterAdress1(input.get("address1"));
        checkoutPageAM.enterCity(input.get("city"));
        checkoutPageAM.enterPostCode(input.get("post_code"));
        checkoutPageAM.selectIndexRegion(input.get("region/state"));

        checkoutPageAM.clickContinueS2();
    }


    @Then("I click the I have read and agree to the Terms & Conditions checkbox")
    public void iClickTheIHaveReadAndAgreeToTheTermsConditionsCheckbox() {
        checkoutPageAM.clickAgreeTOSCheckbox();
    }

    @And("I see Step 3 warning: {string}")
    public void iSeeStepWarning(String warningText) {
        try {
            // the sleep is here because the warnings take a while to update
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // doing contains here because of the char i cant detect
        assertTrue(checkoutPageAM.getPaymentErrorText().contains(warningText));
    }

    @Then("I select Bank as payment method")
    public void iSelectBankAsPaymentMethod() {
        // No clue how to implement this as it's not implemented within the page. Just throwing an error to show that the test does not pass.
        fail();
    }

    // This was for another test that I was going to help Kirils with, but he managed to do it. Keeping this in case I need it.
//    @And("the user is logged in as {string} , {string}")
//    public void theUserIsLoggedInAs(String email, String password) {
//        driver.get("https://www.demoshop24.com/index.php?route=account/login");
//        driver.findElement(By.id("input-email")).sendKeys(email);
//        driver.findElement(By.id("input-password")).sendKeys(password);
//        driver.findElement(By.cssSelector("input.btn.btn-primary[value='Login']")).click();
//        driver.get(shoppingCartPage.mainPageUrl);
//    }
}
