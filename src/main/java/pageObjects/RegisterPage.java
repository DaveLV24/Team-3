package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-telephone")
    private WebElement telephoneInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmInput;

    @FindBy(name = "agree")
    private WebElement privacyPolicyCheckbox;

    @FindBy(css = "input[type='submit'].btn.btn-primary")
    private WebElement continueButton;

    public void fillRegistrationForm(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        telephoneInput.sendKeys(telephone);
        passwordInput.sendKeys(password);
        passwordConfirmInput.sendKeys(confirmPassword);
    }

    public void agreeAndContinue() {
        if (!privacyPolicyCheckbox.isSelected()) {
            privacyPolicyCheckbox.click();
        }
        continueButton.click();
    }
}
