package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBarSection {
    public NavigationBarSection(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//nav[@id='top']//span[text()='Checkout']")
    private WebElement barCheckoutButton;

    public void openCheckoutPage() {
        barCheckoutButton.click();
    }


}
