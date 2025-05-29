package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class CheckoutPage {
    public String pageUrl = "https://www.demoshop24.com/index.php?route=checkout/checkout";
    public CheckoutPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy (css="div.radio input")
    private List<WebElement> checkoutOptionsRadio;
    @FindBy (css="input#button-account")
    private WebElement continueButtonS1;


    // -------------
    // Billing details page
    @FindBy (css="input#input-payment-firstname")
    private WebElement firstNameInput;
    @FindBy (css="input#input-payment-lastname")
    private WebElement lastNameInput;
    @FindBy (css="input#input-payment-email")
    private WebElement emailInput;
    @FindBy (css = "input#input-payment-telephone")
    private WebElement telephoneInput;
    @FindBy (css = "input#input-payment-address-1")
    private WebElement adress1Input;
    @FindBy (css="input#input-payment-city")
    private WebElement cityInput;
    @FindBy (css="input#input-payment-postcode")
    private WebElement postCodeInput;
    @FindBy (css="select#input-payment-zone")
    private WebElement regionSelect;
    public Select getRegionSelect(){
        return new Select(regionSelect);
    }
    @FindBy (css="input#button-guest")
    private WebElement continueButtonS2;

    // ---------------
    // Payment page

    @FindBy (css="input#button-payment-method")
    private WebElement continueButtonS3;

    @FindBy (css="input[type='checkbox'][name='agree']")
    private WebElement agreeTOSCheckbox;

    @FindBy (css="div.alert.alert-danger.alert-dismissible")
    private WebElement paymentError;

    public void clickAgreeTOSCheckbox(){
        agreeTOSCheckbox.click();
    }


    public void selectIndexCheckoutOptionsRadio(String sIndex){
        int index = Integer.parseInt(sIndex);
        checkoutOptionsRadio.get(index).click();
    }

    public void clickContinueS1(){
        continueButtonS1.click();
    }
    public void clickContinueS2() {
        continueButtonS2.click();
    }

    public void clickContinueS3(){
        continueButtonS3.click();
    }

    public void selectIndexRegion(String sIndex){
        regionSelect.click();
        int index = Integer.parseInt(sIndex);
        getRegionSelect().selectByIndex(index);
        regionSelect.click();
    }

    public void enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }
    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }
    public void enterTelephone(String telephone){
        telephoneInput.sendKeys(telephone);
    }
    public void enterAdress1(String adress1){
        adress1Input.sendKeys(adress1);
    }
    public void enterCity(String city){
        cityInput.sendKeys(city);
    }
    public void enterPostCode(String postCode){
        postCodeInput.sendKeys(postCode);
    }

    public String getPaymentErrorText(){
        return paymentError.getText();
    }
}

