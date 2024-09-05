Feature: Table
  Scenario: Check Sort Table
    Given The user opens the page
    When The user logins success
    And The user clicks on the table menu
    Then The user sees the table page
    When The user searches "java"
    Then The user sees correct result
