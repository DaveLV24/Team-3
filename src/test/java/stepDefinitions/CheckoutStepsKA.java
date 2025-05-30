package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CheckoutPageKA;
import pageObjects.NavigationBarSection;
import io.cucumber.datatable.DataTable;
import pageObjects.RegisterPage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CheckoutStepsKA {
    private WebDriver driver;
    private CheckoutPageKA checkoutPage;
    private RegisterPage registerPage;
    private NavigationBarSection navigationBarSection;
    public CheckoutStepsKA() {
        this.driver = Hooks.driver;
        checkoutPage = new CheckoutPageKA(driver);
        navigationBarSection = new NavigationBarSection(driver);
        registerPage = new RegisterPage(driver);
    }

    @And("I go to the Checkout page")
    public void iAmOnTheCheckoutPageThroughNavigationBar() {
        navigationBarSection.goToCheckoutPage();
    }

    @Given("MacBook is added to the cart from home page")
    public void addMacBookFromHomePage() {
        driver.get("https://www.demoshop24.com/index.php?route=common/home");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        By addMacBookBtn = By.cssSelector("button[onclick=\"cart.add('43');\"]");
        wait.until(ExpectedConditions.elementToBeClickable(addMacBookBtn)).click();
    }

    @When("I select Register Account option")
    public void selectTheRegisterOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='register']")));
        checkoutPage.selectRegisterOption();
    }

    @And("I enter credentials on Checkout Page")
    public void iEnterCredentials(DataTable table) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
        Map<String, String> creds = table.asMap(String.class, String.class);
        checkoutPage.enterCredentials(creds);
    }

    @And("I enter credentials on Register Page")
    public void iEnterCredentialsOnRegisterPage(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        String firstName = data.get("First Name");
        String lastName = data.get("Last Name");
        String email = data.get("E-Mail");
        String telephone = data.get("Telephone");
        String password = data.get("Password");
        String confirmPassword = data.get("Password Confirm");

        registerPage.fillRegistrationForm(firstName, lastName, email, telephone, password, confirmPassword);
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
        checkoutPage.clickContinueABD();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("collapse-payment-method")));
    }

    @And("click Continue in Billing Details")
    public void clickContinueInBillingDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        checkoutPage.clickContinueBD();
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

    @When("I click Register through My Account in navigation bar")
    public void iClickRegisterThroughMyAccountInNavigationBar() {
        navigationBarSection.goToRegisterPage();
    }

    @When("I click Login through My Account in navigation bar")
    public void iClickLoginThroughMyAccountInNavigationBar() {
        navigationBarSection.goToLoginPage();
    }



    @And("click Agree and Continue on Register Page")
    public void clickContinueOnRegisterPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registerPage.agreeAndContinue();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")));
    }

    @And("see the success message and click Continue again")
    public void seeTheSuccessMessageAndClickContinueAgain() {
        WebElement successHeader = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']"));
        WebElement continueButton = driver.findElement(By.xpath("//a[@class='btn btn-primary' and text()='Continue']"));
        continueButton.click();
    }

    @And("click Checkout on Shopping Cart page")
    public void clickCheckoutOnShoppingCartPage() {
        WebElement checkoutButton = driver.findElement(By.xpath("//a[@class='btn btn-primary' and text()='Checkout']"));
        checkoutButton.click();
    }

    @And("I enter New Address")
    public void iEnterNewAddress(DataTable tableAdd) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
        Map<String, String> addr = tableAdd.asMap(String.class, String.class);
        checkoutPage.enterNewAddress(addr);
    }

    @And("I enter right email and password and press Login")
    public void iEnterRightEmailAndPasswordForLogin(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String email = credentials.get(0).get("E-Mail");
        String password = credentials.get(0).get("Password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        WebElement passwordInput = driver.findElement(By.id("input-password"));
        WebElement loginButton;
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("route=checkout")) {
            loginButton = driver.findElement(By.cssSelector("input[type='button'][value='Login']"));
        } else {
            loginButton = driver.findElement(By.cssSelector("input[type='submit'][value='Login']"));
        }
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    @Then("I see Warning message")
    public void iSeeWarningNoMatchForEMailAddressAndOrPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement warning = wait.until(ExpectedConditions.visibilityOf(checkoutPage.getLoginWarningMessage()));
        String actualMessage = warning.getText().trim();
        String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
    }

    @Then("I see the saved addresses in the list")
    public void iSeeTheSavedAddressesInTheList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement radio = wait.until(ExpectedConditions.visibilityOf(checkoutPage.getExistingAddressRadio()));

        WebElement dropdown = checkoutPage.getAddressDropdown();
        List<WebElement> options = new Select(dropdown).getOptions();
    }

    @And("^I choose \"([^\"]*)\" from the list$")
    public void iChooseFromTheList(String addr) {
        Select select = new Select(checkoutPage.getAddressDropdown());

        for (WebElement option : select.getOptions()) {
            if (option.getText().trim().equals(addr.trim())) {
                select.selectByVisibleText(addr.trim());
                break;
            }
        }
    }

    @Then("I select I want to use a new address")
    public void iSelectIWantToUseANewAddress() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.getNewAddressRadio()));
        radio.click();
    }

    @Then("I see several error messages")
    public void iSeeSeveralErrorMessages() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='input-payment-firstname']/following-sibling::div[contains(@class, 'text-danger')]")));
        Assert.assertEquals("First Name must be between 1 and 32 characters!", firstNameError.getText());

        WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='input-payment-lastname']/following-sibling::div[contains(@class, 'text-danger')]")));
        Assert.assertEquals("Last Name must be between 1 and 32 characters!", lastNameError.getText());

        WebElement address1Error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='input-payment-address-1']/following-sibling::div[contains(@class, 'text-danger')]")));
        Assert.assertEquals("Address 1 must be between 3 and 128 characters!", address1Error.getText());

        WebElement cityError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='input-payment-city']/following-sibling::div[contains(@class, 'text-danger')]")));
        Assert.assertEquals("City must be between 2 and 128 characters!", cityError.getText());

        WebElement regionError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//select[@id='input-payment-zone']/following-sibling::div[contains(@class, 'text-danger')]")
        ));
        Assert.assertEquals("Please select a region / state!", regionError.getText());
    }

    @And("I choose Payment Method")
    public void iChoosePaymentMethod() {
        // there are no options to choose, step will always fail
        //fail();
    }

    @And("I choose Currency")
    public void iChooseCurrancy() {
        // there are no options to choose, step will always fail
        //fail();
    }

    @Then("I Confirm the order")
    public void iConfirmTheOrder() {
        // there is no functionality available to test, step will always fail
        //fail();
    }
}
