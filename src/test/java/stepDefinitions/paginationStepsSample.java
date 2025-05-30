package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import static org.junit.Assume.assumeTrue;


public class paginationStepsSample {

    private WebDriver driver;

    public paginationStepsSample(){
        this.driver = Hooks.driver;
    }
    List<String> productListBefore;
    List<String> productListAfter;

    @And("I see the list of products shown")
    public void iStoreTheListOfProductsShown() {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-thumb h4 a"));
        productListBefore = products.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @When("pagination is available for next")
    public void paginationIsAvailableForNext() {
        List<WebElement> nextButtons = driver.findElements(By.xpath("//a[text()='>']"));

        boolean isPaginationAvailable = !nextButtons.isEmpty() && nextButtons.get(0).isDisplayed();

        assertFalse(nextButtons.isEmpty(), "Expected 'Next' button, but it was not found.");

    }

    @When("pagination is available for previous")
    public void paginatioIsAvailableForPrevious() {
        List<WebElement> previousButtons = driver.findElements(By.xpath("//a[text()='<']"));

        boolean isPaginationAvailable = !previousButtons.isEmpty() && previousButtons.get(0).isDisplayed();

        assertTrue("Previous pagination is not available. Failing the test.", isPaginationAvailable);
    }

    @And("I click the \"Next\" button")
    public void iClickTheNextButton() {
        WebElement nextButton = driver.findElement(By.xpath("//a[text()='>']"));
        nextButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.stalenessOf(driver.findElements(By.cssSelector(".product-thumb")).get(0)));
    }

    @And("I click the \"Previous\" button")
    public void iClickThePreviousButton() {
        List<WebElement> previousButtons = driver.findElements(By.xpath("//a[text()='<']"));

        if (!previousButtons.isEmpty() && previousButtons.get(0).isDisplayed()) {
            previousButtons.get(0).click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-thumb")));
        } else {
            throw new NoSuchElementException("Previous button not found or not visible.");
        }
    }

    @Then("I should see a different list of products")
    public void iShouldSeeADifferentListOfProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-thumb h4 a"));
        productListAfter = products.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assertions.assertNotEquals(productListBefore, productListAfter, "Product list did not change after pagination");
    }

    @When("I click the last pagination button")
    public void iClickTheLastPaginationButton() {
        List<WebElement> pageButtons = driver.findElements(By.cssSelector(".pagination a"));

        assertFalse(pageButtons.isEmpty(), "No pagination buttons found, test should fail.");


        WebElement lastPageButton = pageButtons.get(pageButtons.size() - 1);

        assertTrue("Last pagination button is not clickable.", lastPageButton.isDisplayed() && lastPageButton.isEnabled());

        lastPageButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.stalenessOf(driver.findElements(By.cssSelector(".product-thumb")).get(0)));
    }

    @Then("the \"Next\" button should not be clickable or not present")
    public void nextButtonShouldNotBeClickableOrNotPresent() {
        List<WebElement> nextButtons = driver.findElements(By.xpath("//a[text()='>']"));

        if (!nextButtons.isEmpty()) {
            WebElement nextButton = nextButtons.get(0);
            assertFalse(nextButton.isDisplayed() && nextButton.isEnabled(), "Next button is clickable on the last page, test should fail.");
        } else {
        }
    }


    @Then("I should see a last list of products")
    public void iShouldSeeALastListOfProducts() {
        // You should have stored old product list in a previous step
        List<WebElement> currentProducts = driver.findElements(By.cssSelector(".product-thumb"));
        assertFalse(currentProducts.isEmpty(), "Product list on last page is empty.");

    }


    @When("I click on page number {int}")
    public void clickPageNumber(int page) {
        driver.findElement(By.xpath("//a[text()='" + page + "']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.stalenessOf(driver.findElements(By.cssSelector(".product-thumb")).get(0)));
    }

    @And("the current page indicator shows {int}")
    public void verifyCurrentPageIndicator(int page) {
        String currentPage = driver.findElement(By.cssSelector(".pagination .active")).getText();
        assertEquals(String.valueOf(page), currentPage);
    }


}
