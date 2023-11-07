@rewardsOrderProcessing @submitEgiftIndividual @Australia
Feature: Australia individual eGift order placement

  @Regression
  Scenario Outline: Verify successful order placement of Australia individual eGift with available payment types
    Given An WebService user
    And User is assigned to a "AUS" eGift client program with "<PaymentType>" FAID
    And User provides merchant, load value and recipient details
    And User sets "API User" as email closing name
    And User selects method "NONE" as unsubscribe data
    When User places an individual eGift order via submitEgiftIndividual API
    Then Order should be successful
    And Order should be 100% complete
    And Transaction ID should not be empty
    And Order number is received

    @DRAW_DOWN
    Examples:
      | PaymentType |
      | DRAW_DOWN   |