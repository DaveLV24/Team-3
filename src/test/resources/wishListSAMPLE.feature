Feature: Testing work of wish List

#email daniilstester@tester.com
#pasword 123456
  Scenario: Add to Wish List and check it as Logged in User
    Given I am logged in as a valid user
    And I open the Browse Home page
    When I click the "Add to Wish List" button for the first product
    Then the product should be visible in the Wish List
    And the product should be present in the Wish List
    And Remove from wish list

    # i need manuali remove from my wish list
  Scenario: Add to Wish List as Logged in User
    Given I am logged in as a valid user
    And I open the Browse Home page
    When I click the "Add to Wish List" button for the first product
    Then I should see the wish list count increased
    Then the product should be visible in the Wish List
    And Remove from wish list


  Scenario: Add to Wish List as Guest User
    Given I Open home page
    When I click the "Add to Wish List" button for the first product
    Then I should see the wish list count increased


