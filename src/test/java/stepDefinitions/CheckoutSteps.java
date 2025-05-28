package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CheckoutPage;
import pageObjects.NavigationBarSection;
import io.cucumber.datatable.DataTable;

import java.time.Duration;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private NavigationBarSection navigationBarSection;
    public CheckoutSteps() {
        this.driver = Hooks.driver;
        checkoutPage = new CheckoutPage(driver);
        navigationBarSection = new NavigationBarSection(driver);
    }

    @And("the user is on the Checkout page")
    public void iClickCheckoutViaNavbar() {
        navigationBarSection.openCheckoutPage();
    }

    @Given("MacBook is added to the cart from home page")
    public void addMacBookFromHomePage() {
        driver.get("https://www.demoshop24.com/index.php?route=common/home");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        By addMacBookBtn = By.cssSelector("button[onclick=\"cart.add('43');\"]");
        wait.until(ExpectedConditions.elementToBeClickable(addMacBookBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
    }

    @When("I select Register Account option")
    public void selectTheRegisterOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='register']")));
        checkoutPage.selectRegisterOption();
    }

    @And("I enter credentials")
    public void iEnterCredentials(DataTable table) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
        Map<String, String> creds = table.asMap(String.class, String.class);
        checkoutPage.enterCredentials(creds);
    }

    @And("click Continue in Checkout Options")
    public void clickContinueInCheckoutOptions() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-account")));
        checkoutPage.clickContinueCO();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
    }

    @And("click Continue in Account & Billing Details")
    public void clickContinueInAccountBillingDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
        checkoutPage.agreePrivacyPolicy();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-address")));
        checkoutPage.clickContinueABD();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("collapse-payment-method")));
    }

    @Then("I see Payment Method section")
    public void iSeePaymentMethodSection() {
        checkoutPage.waitForPaymentMethodSection();
        assertTrue(checkoutPage.getPaymentMethodSection().isDisplayed());
    }

    @Then("I sleep")
    public void iSleep() throws Throwable {
        Thread.sleep(10000);
    }
}
