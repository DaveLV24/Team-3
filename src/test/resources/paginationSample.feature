Feature: check how pagination works

  Scenario: Navigate to next page of products (if pagination exists)
    Given I Open Desktop page
#    And I see the list of products shown
#    When pagination is available for next
#    And I click the "Next" button
#    Then I should see a different list of products

  Scenario: Navigate to previous page of products (if pagination exists)
    Given I Open Desktop page
#    And I see the list of products shown
#    When pagination is available for previous
#    And I click the "Previous" button
#    Then I should see a different list of products

  Scenario: Check Last Page
    Given I Open Desktop page
#    When I click the last pagination button
#    Then I should see a last list of products
#    And the "Next" button should not be clickable or not present

  Scenario: Check Specific page
    Given I Open Desktop page
#    When I click on page number 2
#    Then I should see a different list of products
#    And the current page indicator shows 2

