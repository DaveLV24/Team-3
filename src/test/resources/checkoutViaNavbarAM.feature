Feature: Checkout via navbar
  Background:
    Given the user is on the demoshop website
    And the user is not logged in
    And the user has an empty shopping cart
  Scenario: TC-005 Going to checkout from navbar with an in stock item
    When I add a MacBook from featured items to the shopping cart
    And I click checkout via navbar
    And I assert I am on the checkout page

  Scenario: TC-006 Going to checkout from navbar with an out of stock item
    When I go to "https://www.demoshop24.com/index.php?route=product/product&product_id=49&"
    # Maybe replace this with automated search for the item and then adding it to cart? Not going via page? Not sure
    And I click Add to cart
    And I click checkout via navbar
    And I assert I am on the shopping cart page
    And I assert that I received error: "Products marked with *** are not available in the desired quantity or not in stock!"