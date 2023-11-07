@rewardsOrderProcessing @orderInfo
Feature: Order Info

  @Regression
  Scenario: Verify order info API is returning 200 response for a correct Order ID
    Given A valid Order ID
    When Order ID is queried via orderInfo API
    Then Response should be 200

  Scenario: Verify order info API is returning 400 response for an invalid Order ID
    Given An invalid Order ID
    When Order ID is queried via orderInfo API
    Then Response should be 400