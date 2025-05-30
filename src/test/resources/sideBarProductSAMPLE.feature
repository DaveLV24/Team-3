Feature: Side Bar Functionality

  Scenario: Verify left sidebar exists on the Desktop page
    Given I Open Desktop page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the Laptops & Notebooks page
    Given I Open Laptops & Notebooks page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the Components page
    Given I Open Components page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the I Open Tablets  page
    Given I Open Tablets page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the Software page
    Given I Open Software page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the Phones page
    Given I Open Phones page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the Cameras page
    Given I Open Cameras page
    Then The left sidebar should be visible

  Scenario: Verify left sidebar exists on the MP3 Player page
    Given I Open MP3 Player page
    Then The left sidebar should be visible
################################################################
  Scenario: Verify Category List Content
    Given I Open Desktop page
    Then The category list should contain:
      | Desktops             |
      | Laptops & Notebooks  |
      | Components           |
      | Tablets              |
      | Software             |
      | Phones & PDAs        |
      | Cameras              |
      | MP3 Players          |
########################################################################
  Scenario: Verify number of products under Desktops category
    Given I Open Desktop page
    Then A list of products should be displayed
    And The number of products shown should match the count

  Scenario: Verify number of products under Laptops & Notebooks category
    Given I Open Laptops & Notebooks page
    Then A list of products should be displayed
    And The number of products shown should match the count

  Scenario: Verify number of products under Tablets category
    Given I Open Tablets page
    Then A list of products should be displayed
    And The number of products shown should match the count

  Scenario: Verify number of products under Phones & PDAs category
    Given I Open Phones page
    Then A list of products should be displayed
    And The number of products shown should match the count
    And The number of products shown should match the count

  Scenario: Verify number of products under Cameras category
    Given I Open Cameras page
    Then A list of products should be displayed
    And The number of products shown should match the count

  Scenario: Verify number of products under MP3 Player category
    Given I Open MP3 Player page
    Then A list of products should be displayed
    And The number of products shown should match the count
###############################################################3
  Scenario: Check first Product Details in Phones Page
    Given I Open Phones page
    When I click on the first product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings

  Scenario: Check Second Product Details in Phones Page
    Given I Open Phones page
    When I click on the second product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings

  Scenario: Check third Product Details in Phones Page
    Given I Open Phones page
    When I click on the third product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings
##################################################################
  Scenario: Check first Product Details in Desktops Page
    Given I Open Desktop page
    When I click on the first product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings

  Scenario: Check eleven Product Details in Desktops Page
    Given I Open Desktop page
    When I click on the eleven product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings
#######################################################################
  Scenario: Check fourth Product Details in Laptops & Notebooks Page
    Given I Open Laptops & Notebooks page
    When I click on the fourth product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings

  Scenario: Check fifth Product Details in Laptops & Notebooks Page
    Given I Open Laptops & Notebooks page
    When I click on the fifth product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings
###########################################################################3
  Scenario: Check first Product Details in Cameras Page
    Given I Open Cameras page
    When I click on the first product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings

  Scenario: Check second Product Details in Cameras Page
    Given I Open Cameras page
    When I click on the second product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings
#######################################################################
  Scenario: Check second Product Details in MP3 Player Page
    Given I Open MP3 Player page
    When I click on the second product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings

  Scenario: Check fourth Product Details in MP3 Player Page
    Given I Open MP3 Player page
    When I click on the fourth product in the list
    Then I should see the product details page
    And The product should have a name, image, and price
    When I go back to the product list
    Then I am back on the Phones page with product listings


