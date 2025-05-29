
 Feature: Category menu

   Background:
     Given I am on Home page

   Scenario: Categories is visible on all pages
     Then I should see category menu
     And I click on "About Us" link
     And I am on About us page
     Then I should see category menu


   Scenario: Categories is visible on all pages(for each page on website)
     When I click each link from header
     Then I should see category menu on each page
     And I click each link from footer
     Then I should see category menu on each page



   Scenario:Drop-down menu of categories is visible on all pages
     Then I should see category menu
     And I Hover over "Laptops & Notebooks" category
     And I should see that drop-down menu appears
     And I click on "Contact Us" link
     And I am on Contact Us page
     Then I should see category menu
     And I Hover over "Laptops & Notebooks" category
     And I should see that drop-down menu appears



   Scenario: Subcategory Mac button displays product count
     When I Hover over "Desktops" category
     And I should see that drop-down menu appears
     And I get the product count from "Mac" subcategory link
     And I click on subcategory "Mac"
     And I am on product category page
     Then I should see correct number of products for subcategory


   Scenario: Subcategory Monitors button displays product count
     When I Hover over "Components" category
     And I should see that drop-down menu appears
     And I get the product count from "Monitors" subcategory link
     And I click on subcategory "Monitors"
     And I am on product category page
     Then I should see correct number of products for subcategory


   Scenario:  Each product belong to one category 1
     When I should see category menu
     And I Hover over "Desktops" category
     And I click on subcategory "Show All Desktops"
     And I check products on page "Show All Desktops"
     Then I Hover over "Laptops & Notebooks" category
     And I click on subcategory "Show All Laptops & Notebooks"
     And I check products on page "Show All Laptops & Notebooks"
     And I see that each category has unique products



   Scenario:  Each product belong to one category 2
     When I should see category menu
     And I click on category "Cameras"
     And I check products on page "Cameras"
     And I click on category "Phones & PDAs"
     And I check products on page "Phones & PDAs"
     And I see that each category has unique products



   Scenario: Product belong to one subcategory
     When I should see category menu
     Then I Hover over "Desktops" category
     And I click on subcategory "Mac"
     And I check products on page "Mac"
     Then I Hover over "Desktops" category
     And I click on subcategory "PC"
     And I see that each category has unique products














