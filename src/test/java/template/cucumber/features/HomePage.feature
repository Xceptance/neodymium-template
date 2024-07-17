@Homepage @WebDriverSetUpViaBrowserProfileName
Feature: Home page

  @Chrome_1400x1000
  Scenario Outline: Visting the home page
    Given I navigate to the home page of the project
    
    Then The home page should have heading, carousel, services and the company button
    And The page title should be "Xceptance - The Software Testing Experts"
