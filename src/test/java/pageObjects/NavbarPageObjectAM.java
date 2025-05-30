package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavbarPageObjectAM {

    public NavbarPageObjectAM(WebDriver driver) { {
        PageFactory.initElements(driver, this);
    }}
    // Navbar
    @FindBy(xpath = "//nav[@id='top']//span[text()='Shopping Cart']")
    private WebElement navbarShoppingCartButton;
    @FindBy (xpath = "//nav[@id='top']//span[text()='Checkout']")
    private WebElement navbarCheckoutButton;

    @FindBy (xpath = "//nav[@id='top']//span[text()='My Account']")
    private WebElement navbarMyAccountButton;

    @FindBy (css = ".dropdown-menu.dropdown-menu-right")
    private WebElement myAccountDropdownMenu;
    //TODO - add element for my account dropdown stuff and check if logout exists

    public void openShoppingCartPage() {
        navbarShoppingCartButton.click();
    }
    public void openCheckoutPage() {
        navbarCheckoutButton.click();
    }
}
