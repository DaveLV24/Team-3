package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationBarSection {
    private WebDriver driver;

    public NavigationBarSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//nav[@id='top']//span[text()='Checkout']")
    private WebElement barCheckoutLink;

    @FindBy(how = How.XPATH, using = "//span[text()='My Account']")
    private WebElement myAccountLink;

    @FindBy(how = How.XPATH, using = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
    private WebElement registerLink;

    @FindBy(how = How.XPATH, using = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Login']")
    private WebElement loginLink;

    public void goToCheckoutPage() {
        barCheckoutLink.click();
    }

    public void openMyAccountDropdown() {
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountLink).click().perform();
        WebElement parentLi = driver.findElement(By.xpath("//li[contains(@class, 'dropdown') and .//span[text()='My Account']]"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.attributeContains(parentLi, "class", "open"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(registerLink));
    }

    public void goToRegisterPage() {
        openMyAccountDropdown();
        registerLink.click();
    }

    public void goToLoginPage() {
        openMyAccountDropdown();
        loginLink.click();
    }

}
