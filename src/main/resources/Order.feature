Feature: Place an Order Functionality
  In order to buy a product
  As a user
  I want to be able to place an order successfully


  Scenario Outline: Place an Order with an AMEX card successfully
    Given I am on the dashboard page of the DemoBlaze application
    When I place an order after entering user's <name>,<country>, <city>, <credit card number>,<month>, and <year>
    Then I should see my order details which should include <name>, order id, and amount
    Examples:
      |name  |country|city|credit card number|month|year|
      |"John Doe"|"Portugal"|"Lisbon"|"375567668884617"|"02"|"2030"|

  Scenario Outline: Place an Order with a VISA credit card successfully
    Given I am on the dashboard page of the DemoBlaze application
    When I place an order after entering user's <name>,<country>, <city>, <credit card number>,<month>, and <year>
    Then I should see my order details which should include <name>, order id, and amount
    Examples:
      |name  |country|city|credit card number|month|year|
      |"Percy Clayton"|"United States"|"Jacksonville"|"4411732769254916"|"04"|"2029"|

  Scenario Outline: Placing an Order without a card
    Given I am on the dashboard page of the DemoBlaze application
    When I place an order after entering user's <name>,<country>, <city>, <credit card number>,<month>, and <year>
    Then I should get a prompt message that credit card is required.
    Examples:
      |name  |country|city|credit card number|month|year|
      |"Nasif"|"Nigeria"|"Lagos"|""|"04"|"2029"|
