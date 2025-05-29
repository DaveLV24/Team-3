package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class sideBarProductStepsSample {
    private WebDriver driver;

    public sideBarProductStepsSample(){
        this.driver = Hooks.driver;
    }


    @Given("^I Open Desktop page$")
    public void theUserIsOnTheDesktopPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=20");
    }

    @Given("^I Open Laptops & Notebooks page$")
    public void theUserIsOnTheLaptopsNotebooksPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=18");
    }

    @Given("^I Open Components page$")
    public void theUserIsOnTheComponentsPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=25");
    }

    @Given("^I Open Tablets page$")
    public void theUserIsOnTheTabletsPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=57");
    }

    @Given("^I Open Software page$")
    public void theUserIsOnTheSoftwarePage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=17");
    }

    @Given("^I Open Phones page$")
    public void theUserIsOnThePhonesPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=24");
    }

    @Given("^I Open Cameras page$")
    public void theUserIsOnTheCamerasPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=33");
    }

    @Given("^I Open MP3 Player page$")
    public void theUserIsOnTheMP3PlayerPage() throws Throwable {
        driver.get("https://www.demoshop24.com/index.php?route=product/category&path=34");
    }

    @Then("^The left sidebar should be visible$")
    public void theLeftSidebarShouldBeVisible() throws Throwable {
        // Example CSS selector for left sidebar - inspect the actual page and adjust!
        WebElement sidebar = driver.findElement(By.cssSelector("div.list-group"));
        assertTrue(sidebar.isDisplayed());
    }

    @When("^The category list should contain:$")
    public void theCategoryListShouldContain(io.cucumber.datatable.DataTable dataTable) throws Throwable {
        List<String> expectedCategories = dataTable.asList();

        List<WebElement> categoryElements = Hooks.driver.findElements(By.cssSelector(".list-group a.list-group-item"));
        List<String> actualCategories = categoryElements.stream()
                .map(e -> e.getText().replaceAll("\\(\\d+\\)", "").trim())  // Clean up "Desktops (12)" to "Desktops"
                .collect(Collectors.toList());

        for (String expected : expectedCategories) {
            assertTrue("Missing category: " + expected, actualCategories.contains(expected));
        }
    }

    @Then("^I should see a list of products$")
    public void iShouldSeeAListOfProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector(".list-group a.list-group-item"));
        assertTrue(products.size() > 0);
    }

    @Then("A list of products should be displayed")
    public void aListOfProductsShouldBeDisplayed() {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-layout.product-grid"));
        assertFalse("No products displayed!", products.isEmpty());
    }

    @Then("The number of products shown should match the count")
    public void theNumberOfProductsShownShouldMatchTheCount() {

        List<WebElement> products = driver.findElements(By.cssSelector(".product-layout.product-grid"));

        WebElement activeCategory = driver.findElement(By.cssSelector(".list-group .active"));
        String text = activeCategory.getText(); // Example: "Desktops (12)"


        int expectedCount = Integer.parseInt(text.replaceAll(".*\\((\\d+)\\).*", "$1"));
        int actualCount = products.size();

        assertEquals("Product count mismatch", expectedCount, actualCount);
    }

    @When("I click on the first product in the list")
    public void iClickOnTheFirstProductInTheList() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout .caption a"));
        assertFalse("No products found", products.isEmpty());
        products.get(0).click();
    }

    @When("I click on the second product in the list")
    public void iClickOnTheSecondProductInTheList() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout .caption a"));
        assertFalse("No products found", products.isEmpty());
        products.get(1).click();
    }

    @When("I click on the third product in the list")
    public void iClickOnTheThirdProductInTheList() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout .caption a"));
        assertFalse("No products found", products.isEmpty());
        products.get(2).click();
    }

    @When("I click on the fourth product in the list")
    public void iClickOnTheFourthProductInTheList() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout .caption a"));
        assertFalse("No products found", products.isEmpty());
        products.get(3).click();
    }

    @When("I click on the fifth product in the list")
    public void iClickOnTheFifthProductInTheList() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout .caption a"));
        assertFalse("No products found", products.isEmpty());
        products.get(4).click();
    }

    @When("I click on the eleven product in the list")
    public void iClickOnTheElevenProductInTheList() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout .caption a"));
        assertFalse("No products found", products.isEmpty());
        products.get(10).click();
    }

    @Then("I should see the product details page")
    public void iShouldSeeTheProductDetailsPage() {
        List<WebElement> h1Elements = Hooks.driver.findElements(By.tagName("h1"));
        WebElement productTitle = h1Elements.get(1);
        assertTrue("Product title not displayed", productTitle.isDisplayed());
    }

    @Then("The product should have a name, image, and price")
    public void theProductShouldHaveNameImagePrice() {
        List<WebElement> h1Elements = Hooks.driver.findElements(By.tagName("h1"));
        List<WebElement> images = Hooks.driver.findElements(By.cssSelector("#content .thumbnails img"));
        WebElement price = Hooks.driver.findElement(By.cssSelector(".list-unstyled h2, .price")); // fallback selector
        WebElement name = h1Elements.get(1);

        assertTrue(name.isDisplayed());
        assertFalse(images.isEmpty());
        assertTrue(images.get(0).isDisplayed());
        assertTrue(price.isDisplayed());
    }

    @When("I go back to the product list")
    public void iGoBackToTheProductList() {
        Hooks.driver.navigate().back();
    }

    @Then("I am back on the Phones page with product listings")
    public void iAmBackOnThePhonesPageWithProductListings() {
        List<WebElement> products = Hooks.driver.findElements(By.cssSelector(".product-layout.product-grid"));
        assertFalse("No products displayed after navigating back", products.isEmpty());
    }



}
