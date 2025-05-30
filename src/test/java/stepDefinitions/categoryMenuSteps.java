package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class categoryMenuSteps {
    private WebDriver driver;
    List<String> availableLinks = new ArrayList<String>();
    int expectedProductCount = 0;
    String currentPageUrl;
    Map<String, Set<String>> categoryProducts = new HashMap<>();
    @After
    public void afterScenario() {
        System.out.println("After each scenario — cleaning sets");
        categoryProducts.clear();
    }

    public categoryMenuSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on Home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://www.demoshop24.com/");
    }
    @Then("^I should see category menu$")
    public void iShouldSeeCategoryMenu() throws Throwable {
        WebElement categoryMenu = driver.findElement(By.className("navbar-collapse"));
        assertTrue("Category menu is visible", categoryMenu.isDisplayed());
    }
    @And("^I click on \"([^\\\"]*)\" link$")
    public void iClickOnLink(String linkNAme) throws Throwable {
        driver.findElement(By.xpath("//a[contains(text(),'"+ linkNAme +"')]")).click();
    }

    @And("^I am on About us page")
    public void assertABoutUsPageUrl() throws Throwable {
        assertEquals("https://www.demoshop24.com/index.php?route=information/information&information_id=4", driver.getCurrentUrl());
    }

    @When("^I click each link from header")
    public void iClickEachLinkFromHeader() throws Throwable {

        List<WebElement> headerLinks = driver.findElements(By.cssSelector("#top a"));

        for (WebElement link : headerLinks) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty() && href.startsWith("https://")) {
                availableLinks.add(href);
            }
        }
    }
    @Then("^I should see category menu on each page")
    public void iShouldSeeCategoryMenuOnEachPage() throws Throwable {
        for(String url: availableLinks) {
            driver.get(url);
            WebElement menu = driver.findElement(By.id("menu"));
            assertTrue("Navbar is not visible on page: " + url, menu.isDisplayed());
        }
    }
    @And("^I click each link from footer")
    public void iClickEachLinkFromFooter() throws Throwable {

        List<WebElement> headerLinks = driver.findElements(By.cssSelector("footer a"));

        for (WebElement link : headerLinks) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty() && href.startsWith("https://")) {
                availableLinks.add(href);
            }
        }
    }

    @And("^I Hover over \"([^\\\"]*)\" category$")
    public void iHoverOverCategory(String category) throws Throwable {
        Actions hoverOver = new Actions(driver);
        WebElement laptopsCategory = driver.findElement(By.xpath("//a[text()='" + category + "']"));
        hoverOver.moveToElement(laptopsCategory).perform();
    }


    @And("^I should see that drop-down menu appears$")
    public void iShouldSeeDropDownMenu() throws Throwable {
        WebElement categoryMenu = driver.findElement(By.className("dropdown"));
        assertTrue("Drop-down menu is visible", categoryMenu.isDisplayed());
    }

    @And("^I am on Contact Us page")
    public void assertContactUsUrl() throws Throwable {
        assertEquals("https://www.demoshop24.com/index.php?route=information/contact", driver.getCurrentUrl());
    }


    @And("^I get the product count from \"([^\\\"]*)\" subcategory link$")
    public void iGetProductCountFrom(String category) throws Throwable {
        WebElement subcategoryElement = driver.findElement(By.xpath("//a[contains(text(),'" + category + "')]"));
        Pattern regExp = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = regExp.matcher(subcategoryElement.getText());

        if (matcher.find()) {
            expectedProductCount = Integer.parseInt(matcher.group(1));
        }
    }

    @And("^I click on subcategory \"([^\\\"]*)\"$")
    public void iClickOnSubcategory(String category) throws Throwable {
        driver.findElement(By.xpath("//a[contains(text(),'" + category + "')]")).click();
       currentPageUrl = driver.getCurrentUrl();
    }

    @And("^I am on product category page$")
    public void assertProductCategoryUrl() throws Throwable {
        assertEquals( currentPageUrl, driver.getCurrentUrl());
    }
    @And("^I check products on page \"([^\\\"]*)\"$")
    public void iCheckProduct(String categoryName) throws Throwable {
        List<WebElement> productElements = driver.findElements(By.cssSelector(".product-thumb"));
        Set<String> productTitles = new HashSet<>();
        for(WebElement product : productElements){
            WebElement image = product.findElement(By.tagName("img"));
            String title = image.getAttribute("title");
            productTitles.add(title);
        }
        categoryProducts.put(categoryName,productTitles);
    }

    @And("^I see that each category has unique products$")
    public void iSeeUniqueProduct() throws Throwable {
        Set<String> seenProducts = new HashSet<>();
        Set<String> duplicateProducts = new HashSet<>();

        for (Map.Entry<String, Set<String>> entry : categoryProducts.entrySet()) {
            for (String product : entry.getValue()) {
                if (!seenProducts.add(product)) {
                    duplicateProducts.add(product);
                }
            }
        }
        assertTrue( "Duplicates found: " + duplicateProducts, !duplicateProducts.isEmpty());
    }

    @And("^I click on category \"([^\\\"]*)\"$")
    public void iClickOnCategory(String category) throws Throwable {
        driver.findElement(By.xpath("//a[text()='" + category +"']")).click();
    }

    @And("^I should see correct number of products for subcategory$")
    public void iShouldSee() throws Throwable {
        List<WebElement> productElements = driver.findElements(By.cssSelector(".product-thumb"));
        int actualProductCount = productElements.size();
        assertEquals("The number of products does not match the number in the subcategory", expectedProductCount, actualProductCount);
    }

}
