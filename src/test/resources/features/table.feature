Feature: Table
  Scenario: Check Sort Table
    Given The user opens the page
    When The user logins success
    And The user clicks on the table menu
    Then The user sees the table page
    When The user searches "java"
    Then The user sees correct result
    Then The user sees correct search results in the table
      | Name           | Position                    | Office        | Age | Start Date | Salary   |
      | Acto Java      | Software Architect          | London        | 32  | 2010/04/20 | $105,000 |
      | Cedric Kelly   | Senior Javascript Developer | Edinburgh     | 22  | 2012/03/29 | $433,060 |
      | Colleen Hurst  | Javascript Developer        | San Francisco | 39  | 2009/09/15 | $205,500 |
      | Jennifer Acosta| Junior Javascript Developer | Edinburgh     | 43  | 2013/02/01 | $75,650  |
      | Michael Bruce  | Javascript Developer        | Singapore     | 29  | 2011/06/27 | $183,000 |



