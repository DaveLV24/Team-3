Feature: Checkout process
  As a user
  I want to checkout
  So that I purchase selected goods

  Background:
    Given MacBook is added to the cart from home page
    Given the user is on the Checkout page

  @TC-013
  Scenario: Registering a new account on the first checkout step
    When I select Register Account option
    And click Continue in Checkout Options
    And I enter credentials
      | First Name       | Test                 |
      | Last Name        | Test                 |
      | E-Mail           | test123@testing.com  |
      | Telephone        | 12345678      |
      | Password         | test          |
      | Password Confirm | test          |
      | Company          | Test          |
      | Address 1        | Test 1        |
      | Address 2        | Test 2        |
      | City             | Test          |
      | Post Code        | 123           |
      | Country          | United Kingdom|
      | Region / State   | Greater London|
    #And I sleep
    And click Continue in Account & Billing Details
    Then I see Payment Method section


