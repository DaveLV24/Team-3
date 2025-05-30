package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPageAM {

    public MainPageAM(WebDriver driver){
        {
            PageFactory.initElements(driver, this);
        }
    }

    public String pageUrl ="https://www.demoshop24.com/index.php?route=common/home";

                    // -----------------------
                    // Shopping cart menu
                    @FindBy(css = "span#cart-total")
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
    @FindBy (xpath = "//div[@class='row']//span[text()='Add to Cart']") // here the first is a macbook
    private List<WebElement> featuredItemsButtons;

    // not exactly main page thing, but I don't think it's needed to make a seperate file just for this
    @FindBy (xpath = "//button[@id='button-cart' and text()='Add to Cart']")
    private WebElement itemPageAddToCartButton;

    public void addFirstFeaturedItem(){
        featuredItemsButtons.get(0).click();
    }


    public void addItemToCartFromPage() {
        itemPageAddToCartButton.click();
    }


}
