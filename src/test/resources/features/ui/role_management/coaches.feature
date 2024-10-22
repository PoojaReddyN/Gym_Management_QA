Feature: Validate Coaches Page Functionality

  Scenario Outline: Verify coach information and book workout functionality
    Given I have logged in to the application
    When I view the list of coaches
    When I click the Book Workout button for coach "<name>"
    Then I log out from the application
    And I should see the toast message down as "<toastMessage>"

    Examples:
      | name      | toastMessage            |
      | Sushmitha | Logged out successfully |
      | Johnson   | Logged out successfully |

