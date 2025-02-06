@regression @hepsiburada
Feature: Hepsiburada Web Browser Test

  @browser
  Scenario: User adds an Apple tablet with a 13.2-inch display to the cart
    Given I visit "https://www.hepsiburada.com/"
    When I navigate to "Elektronik" > "Bilgisayar/Tablet" > "Tablet"
    And I filter by "markalar" as "Apple"
    And I filter by "ekranboyutu" as "13,2 inç"
    And I select the highest priced product from the search results
    And I add to cart product on the product detail page
    Then I should see the product in my cart with the same price as the product detail page
