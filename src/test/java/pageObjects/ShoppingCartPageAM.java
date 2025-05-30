package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPageAM {
    //TODO - probably move these to a seperate file
    public String pageUrl = "https://www.demoshop24.com/index.php?route=checkout/cart";

    public ShoppingCartPageAM(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    // --------------
    // Shopping cart page
    @FindBy (xpath = "//a[@class = 'btn btn-primary' and text() = 'Checkout']")
    private WebElement shoppingCartPageCheckout;
    @FindBy (xpath = "//a[@class = 'btn btn-primary' and text() = 'Continue']")
    private WebElement shoppingCartPageContinue;

    @FindBy (css = "div.alert.alert-danger.alert-dismissible")
    private WebElement shoppingCartPageError;

    @FindBy (css = "div#content p") //i dislike selecting it like this :(
    private WebElement shoppingCartEmptyCartText;

    //TODO - all checkout interaction and stuff



    public void clickShoppingCartPageCheckout() {shoppingCartPageCheckout.click();}

    public void clickShoppingCartPageContinue() {shoppingCartPageContinue.click();}

    public WebElement getShoppingCartPageError(){
        return shoppingCartPageError;
    }

    public WebElement getShoppingCartEmptyCartText(){
        return shoppingCartEmptyCartText;
    }

    public WebElement getShoppingCartPageCheckout(){
        return shoppingCartPageCheckout;
    }

    public boolean doesCheckoutExist(){
        // i hateeeee this but I couldn't find a better way lol.
        try{
            getShoppingCartPageCheckout().click();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

}