package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class sortByStepsSample {
    private WebDriver driver;

    public sortByStepsSample(){
        this.driver = Hooks.driver;
    }

    @When("I sort products by {string}")
    public void iSortProductsBy(String sortOption) {
        WebElement sortDropdown = driver.findElement(By.id("input-sort"));
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOption);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("sort="));
    }

    @Then("the products should be sorted by price in ascending order")
    public void theProductsShouldBeSortedByPriceInAscendingOrder() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".product-layout .price"));

        List<Double> prices = new ArrayList<>();
        for (WebElement priceEl : priceElements) {
            String fullPriceText = priceEl.getText().split("\n")[0].trim();

            String cleaned = fullPriceText.replaceAll("[^\\d.]", "");

            try {
                prices.add(Double.parseDouble(cleaned));
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse price: " + cleaned);
            }
        }

        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        assertEquals("Prices are not sorted in ascending order", sorted, prices);
    }

    @Then("the products should be sorted by price in descending order")
    public void theProductsShouldBeSortedByPriceInDescendingOrder() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(".product-layout .price"));

        List<Double> prices = new ArrayList<>();
        for (WebElement priceEl : priceElements) {
            String fullPriceText = priceEl.getText().split("\n")[0].trim();
            String cleaned = fullPriceText.replaceAll("[^\\d.]", "");

            try {
                prices.add(Double.parseDouble(cleaned));
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse price: " + cleaned);
            }
        }

        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());

        Assert.assertEquals("Prices are not sorted from high to low", sorted, prices);
    }


    @When("I select {string} from the sort dropdown")
    public void iSelectFromTheSortDropdown(String sortOption) {
        WebElement dropdown = driver.findElement(By.id("input-sort"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(sortOption);
    }

    @Then("the products should be sorted by model A-Z")
    public void theProductsShouldBeSortedByModelAZ() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("sort=p.model&order=ASC"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("sort=p.model&order=ASC"));

        driver.quit();
    }

    @Then("the products should be sorted by model Z-A")
    public void theProductsShouldBeSortedByModelZA() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("sort=p.model&order=DESC"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("sort=p.model&order=DESC"));

        driver.quit();
    }

    @Then("the products should be sorted by name A-Z")
    public void theProductsShouldBeSortedByNameAZ() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".caption h4 a")));

        List<WebElement> productElements = driver.findElements(By.cssSelector(".caption h4 a"));
        List<String> productNames = productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedNames = new ArrayList<>(productNames);
        Collections.sort(sortedNames);  // Sort A-Z

        Assert.assertEquals("Products are not sorted by name A-Z", sortedNames, productNames);

        driver.quit();
    }

    @Then("the products should be sorted by name Z-A")
    public void theProductsShouldBeSortedByNameZA() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".caption h4 a")));

        List<WebElement> productElements = driver.findElements(By.cssSelector(".caption h4 a"));
        List<String> productNames = productElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedNames = new ArrayList<>(productNames);
        Collections.sort(sortedNames);
        Collections.reverse(sortedNames);

        Assert.assertEquals("Products are not sorted by name Z-A", sortedNames, productNames);

        driver.quit();
    }

    @Then("the products should be sorted by Rating Highest")
    public void theProductsShouldBeSortedByRatingHighest() {
        List<WebElement> ratingElements = driver.findElements(By.cssSelector(".rating"));

        List<Integer> ratings = new ArrayList<>();
        for (WebElement rating : ratingElements) {
            List<WebElement> starSpans = rating.findElements(By.cssSelector(".fa-stack"));
            int filledStars = 0;

            for (WebElement span : starSpans) {
                List<WebElement> stars = span.findElements(By.cssSelector(".fa-star"));
                if (!stars.isEmpty()) {
                    filledStars++;
                }
            }
            ratings.add(filledStars);
        }
        List<Integer> sortedRatings = new ArrayList<>(ratings);
        sortedRatings.sort(Collections.reverseOrder());

        Assert.assertEquals("Products are not sorted by rating descending", sortedRatings, ratings);
    }

    @Then("the products should be sorted by Rating Lowest")
    public void theProductsShouldBeSortedByRatingLowest() {
        List<WebElement> ratingElements = driver.findElements(By.cssSelector(".rating"));

        List<Integer> ratings = new ArrayList<>();
        for (WebElement rating : ratingElements) {
            List<WebElement> starSpans = rating.findElements(By.cssSelector(".fa-stack"));
            int filledStars = 0;

        }
            List<Integer> sortedRatings = new ArrayList<>(ratings);
            Collections.sort(sortedRatings);

            Assert.assertEquals("Products are not sorted by Rating (Lowest)", sortedRatings, ratings);


    }

}
