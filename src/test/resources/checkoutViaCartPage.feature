Feature: Checkout via shopping cart page
  Background:
    Given the user is on the demoshop website
    And the user is not logged in
    And the user has an empty shopping cart
  Scenario: TC-001 Going to checkout from page with an in stock item and try to purchase it
    When I add a MacBook from featured items to the shopping cart
    # Ideally I'd change MacBook to something you can enter. Maybe first featured item? I implemented it that way, but not sure how to do the cucumber step
    And I click on shopping cart in the navbar
    Then I click checkout
    And I select "1" by index in the radio menu
    And I click continue1
    Then I enter the following details:
    | first_name   | Test            |
    | last_name    | testTest        |
    | email        | test@email.test |
    | telephone    | 0000123         |
    | address1     | TestAddress     |
    | city         | TestCity        |
    | post_code    | 1234            |
    | region/state | 1               |
    And I click continue2
    And I click continue3
    And I see Step 3 warning: "You must agree to the Terms & Conditions!"
    Then I click the I have read and agree to the Terms & Conditions checkbox
    And I click continue3
    And I see Step 3 warning: "Payment method required!"
    #I can not implement these steps as they do not exist in the webapp, therefore I am not able to define selectors for them :)
#    Then I select Bank as payment method
#    Then I click continue
#    And I assert my checkout information is correct
#    And I click continue

  Scenario: TC-002 Going to checkout from page with an out of stock item
    When I go to "https://www.demoshop24.com/index.php?route=product/product&product_id=49&"
    # Maybe replace this with automated search for the item and then adding it to cart? Not going via page? Not sure
    And I click Add to cart
    And I click on shopping cart in the navbar
    Then I click checkout
    And I assert that I received error: "Products marked with *** are not available in the desired quantity or not in stock!"

    #not sure if I wrote this one quite correctly
  Scenario: TC-003 Going to checkout from page with no item in the shopping cart
    #if this doesnt work, replace when with And lol
    When I click on shopping cart in the navbar
    And I assert that I see empty cart text: "Your shopping cart is empty!"
    And I assert that checkout button does not exist
    Then I click continue in cart
    And I assert that I am on the home page

    Scenario: TC-004 Going to checkout from page with one in stock and one out of stock item in the shopping cart
      When I add a MacBook from featured items to the shopping cart
      And I go to "https://www.demoshop24.com/index.php?route=product/product&product_id=49&"
    # Maybe replace this with automated search for the item and then adding it to cart? Not going via page? Not sure
      And I click Add to cart
      And I click on shopping cart in the navbar
      Then I click checkout
      And I assert that I received error: "Products marked with *** are not available in the desired quantity or not in stock!"
