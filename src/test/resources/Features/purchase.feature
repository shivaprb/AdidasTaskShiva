Feature: Adidas Order Placement Functionalities
  Agile Story: As a user, I should be able to place order from the Website

  Background: Go to home page
    Given User is on the Home Page
  @wip
  Scenario: Placing a successful order
    When User adds "Sony vaio i5" from "Laptops"
    And User adds "Dell i7 8gb" from "Laptops"
    And User adds "Samsung galaxy s6" from "Phones"
    And User removes "Samsung galaxy s6" from cart
    And User places order and captures and logs purchase ID and Amount
    Then User verifies purchase amount equals expected