package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    //TODO - probably move these to a seperate file
    public String mainPageUrl ="https://www.demoshop24.com/index.php?route=common/home";
    public String shoppingCartPageUrl = "https://www.demoshop24.com/index.php?route=checkout/cart";
    public String checkoutPageUrl = "https://www.demoshop24.com/index.php?route=checkout/checkout";

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //maybe move navbar to seperate file?
    //not a big fan of navigating via xpath and text, but I think that's the only distinguishing thing here.
    // I don't want to do like path[5] as I believe the order is more likely to change than essential text.
    //--------------------------
    // Navbar
    @FindBy (xpath = "//nav[@id='top']//span[text()='Shopping Cart']")
    private WebElement navbarShoppingCartButton;
    @FindBy (xpath = "//nav[@id='top']//span[text()='Checkout']")
    private WebElement navbarCheckoutButton;
    @FindBy (xpath = "//nav[@id='top']//span[text()='My Account']")
    private WebElement navbarMyAccountButton;

    @FindBy (css = ".dropdown-menu.dropdown-menu-right")
    private WebElement myAccountDropdownMenu;
    //TODO - add element for my account dropdown stuff and check if logout exists

    // -----------------------
    // Shopping cart menu
    @FindBy (css = "span#cart-total")
    private WebElement searchShoppingCartButton; //Shopping cart button next to search. To more clearly distinguish it from the button in the navbar
    // TODO - add the shopping cart button webelement and its buttnos like checkout and some way to assert all items shown in it are correct
    @FindBy (css = ".dropdown-menu.pull-right") // I dislike this, but I don't see any other way to do it.
    private WebElement shoppingCartOpenMenu;
    // not sure what a better method here to select these two would be
    //also would it be better to do it as a child of it?
    @FindBy (xpath = "//ul[@class='dropdown-menu pull-right']//a[@href='https://www.demoshop24.com/index.php?route=checkout/cart']")
    private WebElement shoppingCartMenuViewCart;
    @FindBy (xpath = "//ul[@class='dropdown-menu pull-right']//a[@href='https://www.demoshop24.com/index.php?route=checkout/checkout']")
    private WebElement shoppingCartMenuCheckout;


    // ---------------------------
    // Featured
    @FindBy (xpath = "//div[@class='row']//span[text()='Add to Cart'][1]") // here its a macbook
    private WebElement firstFeaturedItemAddToCartButton;

    @FindBy (xpath = "//button[@id='button-cart' and text()='Add to Cart']")
    private WebElement itemPageAddToCartButton;

    // --------------

    //TODO - all checkout interaction and stuff

    public void openShoppingCartPageViaNavbar() {
        navbarShoppingCartButton.click();
    }
    public void openCheckoutPageViaNavbar() {
        navbarCheckoutButton.click();
    }

}