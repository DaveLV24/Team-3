package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//nav[@id='top']//span[text()='Checkout']")
    private WebElement navigationCheckoutButton;

    @FindBy(id = "input-payment-firstname")
    private WebElement firstNameInput;
    @FindBy(id = "input-payment-lastname")
    private WebElement lastNameInput;
    @FindBy(id = "input-payment-email")
    private WebElement emailInput;
    @FindBy(id = "input-payment-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-payment-password")
    private WebElement passwordInput;
    @FindBy(id = "input-payment-confirm")
    private WebElement confirmInput;

    @FindBy(id = "input-payment-company")
    private WebElement companyInput;
    @FindBy(id = "input-payment-address-1")
    private WebElement address1Input;
    @FindBy(id = "input-payment-address-2")
    private WebElement address2Input;
    @FindBy(id = "input-payment-city")
    private WebElement cityInput;
    @FindBy(id = "input-payment-postcode")
    private WebElement postcodeInput;
    @FindBy(id = "input-payment-country")
    private WebElement countrySelect;
    @FindBy(id = "input-payment-zone")
    private WebElement zoneSelect;

    @FindBy(id = "collapse-payment-method")
    private WebElement paymentMethodSection;

    @FindBy(how = How.XPATH, using = "//input[@name='account' and @value='guest']")
    private WebElement guestRadioButton;

    @FindBy(how = How.ID, using = "button-account")
    private WebElement continueButtonCO;
    @FindBy(css="input#button-register")
    private WebElement continueButtonABD;
    @FindBy(how = How.ID, using ="button-payment-address")
    private WebElement continueButtonBD;

    @FindBy(how = How.XPATH, using = "//input[@name='account' and @value='register']")
    private WebElement registerRadioButton;

    @FindBy(name = "agree")
    private WebElement privacyPolicyCheckbox;

    @FindBy(how = How.CSS, using = "div.alert.alert-danger.alert-dismissible")
    private WebElement loginWarningMessage;

    @FindBy(how = How.XPATH, using = "//input[@name='payment_address' and @value='existing']")
    private WebElement existingAddressRadio;

    @FindBy(how = How.NAME, using = "address_id")
    private WebElement addressDropdown;

    @FindBy(how = How.XPATH, using = "//input[@name='payment_address' and @value='new']")
    private WebElement newAddressRadio;

    public WebElement getExistingAddressRadio() {
        return existingAddressRadio;
    }

    public WebElement getNewAddressRadio() {
        return newAddressRadio;
    }

    public WebElement getAddressDropdown() {
        return addressDropdown;
    }

    public WebElement getLoginWarningMessage() {
        return loginWarningMessage;
    }

    public void openCheckoutPage() {
        navigationCheckoutButton.click();
    }

    public WebElement getPaymentMethodSection() {
        return paymentMethodSection;
    }

    public String getPageUrl() {
        return "https://demoshop24.com/index.php?route=checkout/checkout";
    }

    public void selectRegisterOption() {
        if (!registerRadioButton.isSelected()) {
            registerRadioButton.click();
        }
    }

    public void clickContinueCO() {
        continueButtonCO.click();
    }
    public void clickContinueABD() {
        continueButtonABD.click();
    }
    public void clickContinueBD() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueButtonBD));
        continueButtonBD.click();
    }

    public void enterCredentials(Map<String, String> creds) {
        firstNameInput.sendKeys(creds.get("First Name"));
        lastNameInput.sendKeys(creds.get("Last Name"));
        emailInput.sendKeys(creds.get("E-Mail"));
        telephoneInput.sendKeys(creds.get("Telephone"));

        passwordInput.sendKeys(creds.get("Password"));
        confirmInput.sendKeys(creds.get("Password Confirm"));

        companyInput.sendKeys(creds.get("Company"));
        address1Input.sendKeys(creds.get("Address 1"));
        address2Input.sendKeys(creds.get("Address 2"));
        cityInput.sendKeys(creds.get("City"));
        postcodeInput.sendKeys(creds.get("Post Code"));

        Select country = new Select(countrySelect);
        country.selectByVisibleText(creds.get("Country"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(zoneSelect));

        Select zone = new Select(zoneSelect);
        zone.selectByVisibleText(creds.get("Region / State"));
    }

    public void enterNewAddress(Map<String, String> addr) {
        firstNameInput.sendKeys(addr.get("First Name"));
        lastNameInput.sendKeys(addr.get("Last Name"));
        String company = addr.get("Company");
        if (company != null && !company.trim().isEmpty()) {
            companyInput.sendKeys(company);
        }
        address1Input.sendKeys(addr.get("Address 1"));
        String address2 = addr.get("Address 2");
        if (address2 != null && !address2.trim().isEmpty()) {
            address2Input.sendKeys(address2);
        }
        cityInput.sendKeys(addr.get("City"));
        String postcode = addr.get("Post Code");
        if (postcode != null && !postcode.trim().isEmpty()) {
            postcodeInput.sendKeys(postcode);
        }

        Select country = new Select(countrySelect);
        country.selectByVisibleText(addr.get("Country"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(zoneSelect));

        Select zone = new Select(zoneSelect);
        zone.selectByVisibleText(addr.get("Region / State"));
    }

    public void agreePrivacyPolicy() {
        if (!privacyPolicyCheckbox.isSelected()) {
            privacyPolicyCheckbox.click();
        }
    }

    public void waitForPaymentMethodSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(paymentMethodSection));
    }

}
