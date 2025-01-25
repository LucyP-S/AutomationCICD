@tag
Feature: Error Validation
  I want to use this template for my feature file

  Background:
    Given I landed on Ecommerce Page

  @ErrorValidation
  Scenario Outline: Error message during login
    Given I landed on Ecommerce Page
    When Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username            | password |
      | lpalucka1@gmail.com | 123Robin |

