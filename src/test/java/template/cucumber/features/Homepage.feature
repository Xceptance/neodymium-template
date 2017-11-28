Feature: Homepage

  Scenario Outline: Visting the homepage
    Given The browser "<browser>" is open
    And I am on the homepage of the project
    
    Then The homePage should have heading, carousel, services and the company button
    And The page title should be "Xceptance - The Software Testing Experts"

    Examples: 
      | browser         |
      | Chrome_1024x768 |
      | FF_1024x768     |
