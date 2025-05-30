Feature: Sort option Functionality check

  Scenario: Sort products by Price (High to Low)
    Given I Open Desktop page
    When I sort products by "Price (High > Low)"
    Then the products should be sorted by price in descending order

  Scenario: Sort products by Price (Low to High)
    Given I Open Desktop page
    When I sort products by "Price (Low > High)"
    Then the products should be sorted by price in ascending order

##############BUG#########################
  Scenario: Sort products by name A-Z
    Given I Open Desktop page
    When I select "Name (A - Z)" from the sort dropdown
    Then the products should be sorted by name A-Z

##############BUG#########################
  Scenario: Sort products by name Z-A
    Given I Open Desktop page
    When I select "Name (Z - A)" from the sort dropdown
    Then the products should be sorted by name Z-A

  Scenario: Sort products by model A-Z
    Given I Open Desktop page
    When I select "Model (A - Z)" from the sort dropdown
    Then the products should be sorted by model A-Z

  Scenario: Sort products by model Z-A
    Given I Open Desktop page
    When I select "Model (Z - A)" from the sort dropdown
    Then the products should be sorted by model Z-A

  Scenario: Sort products by Rating (Highest)
    Given I Open Desktop page
    When I select "Rating (Lowest)" from the sort dropdown
    Then the products should be sorted by Rating Lowest



