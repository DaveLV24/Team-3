Feature: Checkout process
  As a user
  I want to checkout
  So that I purchase selected goods

  Background:
    Given MacBook is added to the cart from home page

  @TC-013
  Scenario: Checking out as unauthorized user and register new account on the same page
    Given I go to the Checkout page
    When I select Register Account option
    And click Continue in Checkout Options
    And I enter credentials on Checkout Page
      | First Name       | Test                       |
      | Last Name        | Test                       |
      | E-Mail           | newtestmail102@testing.com |
      | Telephone        | 12345678                   |
      | Password         | test                       |
      | Password Confirm | test                       |
      | Company          | Test                       |
      | Address 1        | Test 1                     |
      | Address 2        | Test 2                     |
      | City             | Test                       |
      | Post Code        | 123                        |
      | Country          | United Kingdom             |
      | Region / State   | Greater London             |
    And click Continue in Account & Billing Details
    Then I see Payment Method section

  @TC-014
  Scenario: Checking out as unauthorized user and register new account using My Account bar
    Given I go to the Checkout page
    When I click Register through My Account in navigation bar
    And I enter credentials on Register Page
      | First Name       | Test                       |
      | Last Name        | Test                       |
      | E-Mail           | newtestmail021@testing.com |
      | Telephone        | 12345678                   |
      | Password         | test2Test$^*@fHDI          |
      | Password Confirm | test2Test$^*@fHDI          |
    And click Agree and Continue on Register Page
    And see the success message and click Continue again
    And click Checkout on Shopping Cart page
    And I enter New Address
      | First Name       | Test                       |
      | Last Name        | Test                       |
      | Company          | Test                       |
      | Address 1        | Test 1                     |
      | Address 2        | Test 2                     |
      | City             | Test                       |
      | Post Code        | 123                        |
      | Country          | United Kingdom             |
      | Region / State   | Greater London             |
    And click Continue in Billing Details
    Then I see Payment Method section

  @TC-015
  Scenario: Checking out as unauthorized user and login into an existing account using My Account bar
    When I click Login through My Account in navigation bar
    And I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    Then I go to the Checkout page
    And click Continue in Billing Details
    Then I see Payment Method section

  @TC-016
  Scenario: Checking out as unauthorized user and login into an existing account (right credentials)
    Given I go to the Checkout page
    And I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    And click Continue in Billing Details
    Then I see Payment Method section

  @TC-017
  Scenario: Checking out as unauthorized user and login into non-existent account (wrong credentials)
    Given I go to the Checkout page
    And I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 0000     |
    Then I see Warning message

  @TC-018
  Scenario: Checking out using user's delivery address from their account
    Given I go to the Checkout page
    Given I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    Then I see the saved addresses in the list
    And I choose "aaa aaa, AAA, AaAa, Clackmannanshire, United Kingdom" from the list
    And click Continue in Billing Details
    Then I see Payment Method section

  @TC-019
  Scenario: Checking out using new address from entering right data
    Given I go to the Checkout page
    Given I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    Then I select I want to use a new address
    And I enter New Address
      | First Name       | Kirils          |
      | Last Name        | Aleksejevs      |
      | Company          |                 |
      | Address 1        | Iela 1          |
      | Address 2        |                 |
      | City             | Daugavpils      |
      | Post Code        |                 |
      | Country          | Latvia          |
      | Region / State   | Daugavpils      |
    And click Continue in Billing Details
    Then I see Payment Method section

  @TC-020
  Scenario: Checking out using new address from leaving it empty
    Given I go to the Checkout page
    Given I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    Then I select I want to use a new address
    # Skipping data entering = leaving empty
    And click Continue in Billing Details
    Then I see several error messages

  @TC-021
  Scenario: While checking out change the address form the address list
    Given I go to the Checkout page
    Given I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    Then I see the saved addresses in the list
    And I choose "bbb bbb, nnn, NN, Funafuti, Tuvalu" from the list
    And click Continue in Billing Details
    Then I see Payment Method section

  @TC-022
  Scenario: While checking out change the payment currency and payment method
    Given I go to the Checkout page
    Given I enter right email and password and press Login
      | E-Mail                | Password |
      | veteki9559@ofular.com | 1234     |
    Then I see the saved addresses in the list
    And I choose "bbb bbb, nnn, NN, Funafuti, Tuvalu" from the list
    And click Continue in Billing Details
    Then I see Payment Method section
    And I choose Payment Method
    And I choose Currency
    Then I Confirm the order


