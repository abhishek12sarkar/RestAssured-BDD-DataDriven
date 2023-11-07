@rewardsOrderProcessing @UnitedKingdom
Feature: UK individual eGift order placement

  @submitEgiftIndividual @Regression
  Scenario Outline: Verify successful order placement of UK individual eGift with available payment types
    Given An WebService user
    And User is assigned to a "UK" eGift client program with "<PaymentType>" FAID
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

    @BANK_TRANSFER
    Examples:
      | PaymentType   |
      | BANK_TRANSFER |

    @WIRE_TRANSFER
    Examples:
      | PaymentType   |
      | WIRE_TRANSFER |

    @ACH_CREDIT
    Examples:
      | PaymentType |
      | ACH_CREDIT  |