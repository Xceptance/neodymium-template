@Homepage
Feature: Home page

  Scenario Outline: Visting the home page
    Given The browser "<browser>" is open
    Then I navigate to the home page of the project
    
    Then The home page should have heading, carousel, services and the company button
    And The page title should be "Xceptance - The Software Testing Experts"

    Examples: 
      | browser         |
      | Chrome_1400x1000 |
