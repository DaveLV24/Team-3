package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class wishListStepsSample {
    private WebDriver driver;

    public wishListStepsSample(){
        this.driver = Hooks.driver;
    }

    @When("I click the {string} button for the first product")
    public void iClickAddToWishListButton(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> wishListButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("button[data-original-title='Add to Wish List']")));
        if (!wishListButtons.isEmpty()) {
            wishListButtons.get(0).click();
        } else {
            throw new NoSuchElementException("No Add to Wish List buttons found");
        }
    }

    @Then("I should see the wish list count increased")
    public void iShouldSeeWishListCountIncreased() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        List<WebElement> spans = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("span.hidden-xs.hidden-sm.hidden-md")));

        assertTrue("Expected at least 4 spans", spans.size() >= 4);

        String text = spans.get(3).getText();
        assertTrue("Wish list text format is incorrect: " + text, text.matches("Wish List \\(\\d+\\)"));
    }

    @Given("I am logged in as a valid user")
    public void iAmLoggedInAsValidUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.demoshop24.com/index.php?route=account/login");
        driver.findElement(By.id("input-email")).sendKeys("daniilstester@tester.com");
        driver.findElement(By.id("input-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        wait.until(ExpectedConditions.urlContains("route=account/account"));

    }

    @And("I open the Browse Home page")
    public void iOpenTheBrowsePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the link is clickable, then click
        WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1/a[text()='Testing Demo Shop']")));
        homeLink.click();

        // Optionally wait for some element on the Browse Home page to confirm navigation
        wait.until(ExpectedConditions.urlContains("route=common/home"));


    }

    @Then("the product should be visible in the Wish List")
    public void theProductShouldBeVisibleInTheWishList() {
        // Click the "Wish List" link
        List<WebElement> wishlistLinks = driver.findElements(By.cssSelector("span.hidden-xs.hidden-sm.hidden-md"));
        WebElement wishlistLink = wishlistLinks.get(3); // 4th element
        wishlistLink.click();

        WebElement productNameCell = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//td[@class='text-left']/a[text()='MacBook']")));

        Assert.assertTrue(productNameCell.isDisplayed());
    }

    @Then("the product should be present in the Wish List")
    public void theProductShouldBePresentInTheWishList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement productNameInWishList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table//td[@class='text-left']/a[text()='MacBook']")));
        Assert.assertTrue("Product is not present in the Wish List!", productNameInWishList.isDisplayed());
    }

    @And("Remove from wish list")
    public void removeFromWishList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for and find the remove button (red "X")
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.btn-danger[data-original-title='Remove']"))
        );

        // Click the remove button
        removeButton.click();

        // Optionally, wait for the item to disappear
        wait.until(ExpectedConditions.invisibilityOf(removeButton));
    }


}
