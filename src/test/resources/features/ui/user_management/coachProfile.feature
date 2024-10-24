Feature: Coach Profile Update

  Scenario Outline: Successfully update coach profile
    Given I log in to the application
    And I am on the coach profile update page
    When I enter "<name>" in the name field
    And I enter "<title>" in the title field
    And I enter "<about>" in the about field
    And I enter "<specialization>" in the specialization field
    And I upload a certificate file named "<certificate>"
    And I click on save changes button there
    Then I should see a success message "Profile updated successfully"

    Examples:
      | name     | title      | about                                                       | specialization | certificate      |
      | John Doe | Head Coach | Experienced coach in various fields like cardio and fitness | Fitness        | certificate1.pdf |


  Scenario Outline: Mandatory fields validation
    Given I log in to the application
    And I am on the coach profile update page
    When I enter "<name>" in the name field
    And I enter "<title>" in the title field
    And I enter "<about>" in the about field
    And I click on save changes button there
    Then I should see an error message for the empty field at "<errorMessageLocation>" as "<errorMessage>"

    Examples:
      | name   | title      | about             | errorMessageLocation | errorMessage                              |
      |        | Head Coach | Experienced coach | name                 | Name is required                          |
      | 123    | Head Coach | Experienced coach | name                 | Name can only contain letters and spaces  |
      | Halena |            | Experienced coach | title                | Title is required                         |
      | Halena | 123        | Experienced coach | title                | Title can only contain letters and spaces |
      | Halena | Head Coach |                   | about                | About is Required                         |



