@TC-007
Feature: Checkout

  Scenario: User tries to checkout with no items in cart
    Given the user is on the homepage
    Then the user should not be logged in
    When the user clicks on the Checkout link
    Then the user should see an empty cart message

  @TC-008
  Scenario: User tries to checkout with in-stock and out-of-stock items
    Given the user is on the homepage
    Then the user should not be logged in
    When the user adds MacBook to the cart
    And the user adds iPhone to the cart
    And the user clicks on the Checkout link
    Then the user should see an out of stock error message

  @TC-009
  Scenario: User goes to checkout from shopping cart preview with an in-stock item
    Given the user is on the homepage
    Then the user should not be logged in
    When the user adds MacBook to the cart
    And the user clicks on the Cart button near search
    And the user clicks Checkout in the cart preview
    Then the user should be routed to the Checkout page

  @TC-010
  Scenario: User goes to checkout from shopping cart preview with an out-of-stock item
    Given the user is on the homepage
    Then the user should not be logged in
    When the user adds iPhone to the cart
    And the user clicks on the Cart button near search for out of stock item
    And the user clicks Checkout in the cart preview for out of stock item
    Then the user should see an out of stock error message on the cart page

  @TC-011
  Scenario: User opens the cart near search with no items
    Given the user is on the homepage
    Then the user should not be logged in
    When the user clicks on the Cart button near search
    Then the user should see an empty cart message in the cart preview
    And the checkout button should not be displayed in the cart preview

  @TC-012
  Scenario: User goes to checkout from shopping cart preview with one in-stock and one out-of-stock item
    Given the user is on the homepage
    Then the user should not be logged in
    When the user adds MacBook to the cart
    And the user adds iPhone to the cart
    And the user clicks on the Cart button near search
    And the user clicks Checkout in the cart preview
    Then the user should see an out of stock error message on the cart page


