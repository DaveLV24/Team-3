Feature: Test how works Shopping Cart

  Scenario: I add to a cart and check quantity
    Given I Open home page
    When I add the first product to the cart
    Then the cart total should update

  Scenario: Add multiple quantities by changing quantity in cart by updating
    Given I Open home page
    When I add the first product to the cart
    And I click the Shopping Cart link
    And I update the quantity of the product to 3
    Then the cart should reflect quantity 3 for the product

  Scenario: I add to a cart and check quantity in shoping cart
    Given I Open home page
    When I add the first product to the cart
    And I click the Shopping Cart link
    Then the cart should reflect quantity 1 for the product
