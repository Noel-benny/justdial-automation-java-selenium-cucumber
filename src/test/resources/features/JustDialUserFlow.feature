Feature: End-to-End JustDial User Journey

  Scenario Outline: Car Wash to Gym navigation on "<browser>"
    Given User launches browser "<browser>" and opens JustDial website
    When User handles car wash popups
    And User searches for car washing services
    And User applies rating filter
    And User prints top rated services
    Then User submits Free Listing with mobile number 1234567890 and retries with valid number
    And User returns to homepage
    And User navigates to Gym menu
    Then User prints gym submenu items
    And User closes browser

    Examples:
      | browser |
      | edge    |
      | chrome  |
