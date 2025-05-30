package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class shopCartStepsSample {
    private WebDriver driver;

    public shopCartStepsSample(){
        this.driver = Hooks.driver;
    }


    @Given("^I Open home page$")
    public void theUserIsOnTheDesktopPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=common/home");
    }

    @When("I add the first product to the cart")
    public void iAddTheFirstProductToTheCart() {
        List<WebElement> cartButtons = driver.findElements(By.cssSelector("button[onclick*='cart.add']"));
        cartButtons.get(0).click();


        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.textToBePresentInElementLocated(By.id("cart-total"), "1 item")
        );
    }

    @Then("the cart total should update")
    public void theTartTotalShouldUpdate() {
        WebElement cartTotal = driver.findElement(By.id("cart-total"));
        String totalText = cartTotal.getText();
        Assert.assertTrue(totalText.contains("1 item"));
    }

    @When("I click the Shopping Cart link")
    public void iClickTheShoppingCartLink() {
        driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();
    }

    @When("I update the quantity of the product to {int}")
    public void iUpdateTheQuantityOfTheProductTo(Integer quantity) {
        WebElement quantityInput = driver.findElement(By.cssSelector("input[name^='quantity']"));
        quantityInput.clear();
        quantityInput.sendKeys(quantity.toString());

        WebElement updateButton = driver.findElement(By.cssSelector("button.btn.btn-primary[data-original-title='Update']"));
        updateButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.attributeToBe(quantityInput, "value", quantity.toString())
        );
    }

    @Then("the cart should reflect quantity {int} for the product")
    public void theCartShouldReflectQuantityForTheProduct(Integer expectedQuantity) {
        WebElement quantityInput = driver.findElement(By.cssSelector("input[name^='quantity']"));
        String actualQty = quantityInput.getAttribute("value");
        Assert.assertEquals(expectedQuantity.toString(), actualQty);
    }


}
