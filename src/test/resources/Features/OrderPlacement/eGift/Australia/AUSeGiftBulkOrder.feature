@rewardsOrderProcessing @submitEgiftBulk @Australia
Feature: Australia bulk eGift order placement

  @Regression
  Scenario Outline: Verify successful order placement of Australia bulk eGift with available payment types
    Given An WebService user
    And User is assigned to a "AUS" eGift client program with "<PaymentType>" FAID
    And User provides merchant, load value and quantity
    When User places an bulk eGift order via submitEgiftBulk API
    Then Order should be successful
    And Order should be 100% complete
    And Transaction ID should not be empty
    And Order number is received

    @DRAW_DOWN
    Examples:
      | PaymentType |
      | DRAW_DOWN   |