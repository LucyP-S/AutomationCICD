@tag
  Feature: Purchase the order from Ecommerce Website
    I want to use this template for my feature file

    Background:
      Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with <username> and <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples:
      | username                   | password  | productName     |
      | lpalucka@gmail.com         | 123Robin  | ZARA COAT 3     |
      | lucytestowekonto@gmail.com | Robin1234 | ADIDAS ORIGINAL |

